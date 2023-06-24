package com.example.lab1.service.Security;

import com.example.lab1.exception.Security.RoleNotFoundException;
import com.example.lab1.exception.Security.UserNotAuthorizedException;
import com.example.lab1.exception.Security.UserNotFoundException;
import com.example.lab1.exception.Security.UserProfileNotFoundException;
import com.example.lab1.model.Security.ERole;
import com.example.lab1.model.Security.Role;
import com.example.lab1.model.Security.User;
import com.example.lab1.model.Security.UserProfile;
import com.example.lab1.repository.GameUserRepository;
import com.example.lab1.repository.ItemRepository;
import com.example.lab1.repository.PlayerCharacterItemRepository;
import com.example.lab1.repository.PlayerCharacterRepository;
import com.example.lab1.repository.Security.RoleRepository;
import com.example.lab1.repository.Security.UserProfileRepository;
import com.example.lab1.repository.Security.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final GameUserRepository gameUserRepository;

    private final ItemRepository itemRepository;

    private final PlayerCharacterRepository playerCharacterRepository;

    private final PlayerCharacterItemRepository playerCharacterItemRepository;

    private final UserProfileRepository userProfileRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, GameUserRepository gameUserRepository, ItemRepository itemRepository, PlayerCharacterRepository playerCharacterRepository, PlayerCharacterItemRepository playerCharacterItemRepository, UserProfileRepository userProfileRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.gameUserRepository = gameUserRepository;
        this.itemRepository = itemRepository;
        this.playerCharacterRepository = playerCharacterRepository;
        this.playerCharacterItemRepository = playerCharacterItemRepository;
        this.userProfileRepository = userProfileRepository;
        this.roleRepository = roleRepository;
    }

    public UserProfile getUserProfileById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user.getUserProfile();
    }

    public UserProfile getUserProfileByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return user.getUserProfile();
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

//    public Integer getUserNumberOfAuthorsById(Long id) {
//        return authorRepository.findByUserId(id).size();
//    }
//
//    public Integer getUserNumberOfBooksById(Long id) {
//        return bookRepository.findByUserId(id).size();
//    }
//
//    public Integer getUserNumberOfLibrariesById(Long id) {
//        return libraryRepository.findByUserId(id).size();
//    }

//    public Integer getUserNumberOfLibraryBooksById(Long id) { return libraryBookRepository.findByUserId(id).size();}

    public List<User> searchUsersByUsername(String username) {
        return this.userRepository.findTop20BySearchTerm(username);
    }

    public UserProfile updateUserProfile(UserProfile newUserProfile, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        boolean isUser = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_USER || role.getName() == ERole.ROLE_ADMIN
        );
        if (!isUser) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }

        return userProfileRepository.findById(user.getUserProfile().getId())
                .map(userProfile -> {
                    userProfile.setBio(newUserProfile.getBio());
                    userProfile.setLocation(newUserProfile.getLocation());
                    userProfile.setGender(newUserProfile.getGender());
                    userProfile.setMaritalStatus(newUserProfile.getMaritalStatus());
                    userProfile.setBirthdate(newUserProfile.getBirthdate());
                    return userProfileRepository.save(userProfile);
                })
                .orElseThrow(() -> new UserProfileNotFoundException(id));
    }

    public User updateRolesUser(HashMap<String, Boolean> roles, Long id, Long userID) {
        User callerUser = this.userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));

        boolean isAdmin = callerUser.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );
        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(callerUser.getUsername()));
        }

        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        Set<Role> roleSet = new HashSet<>();
        if (roles.get("isUser")) {
            Role role = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_USER));
            roleSet.add(role);
        }
        if (roles.get("isModerator")){
            Role role = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_MODERATOR));
            roleSet.add(role);
        }
        if (roles.get("isAdmin")){
            Role role = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_ADMIN));
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        return userRepository.save(user);
    }
}