package com.example.lab1.controller.Security;

import com.example.lab1.exception.Security.UserNotAuthorizedException;
import com.example.lab1.model.Security.ERole;
import com.example.lab1.model.Security.User;
import com.example.lab1.model.dto.Security.SQLRunResponseDTO;
import com.example.lab1.security.JwtUtils;
import com.example.lab1.service.Security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8080", "http://localhost:4200", "http://localhost:8080/swagger-ui.html"})
@RestController
@RequestMapping("/api")
@Validated
public class SQLController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final JwtUtils jwtUtils;

    private final UserService userService;

    public SQLController(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/run-delete-authors-script")
    ResponseEntity<?> deleteAllAuthors(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }
        try {
            String currentDir = System.getProperty("user.dir");
            String sql = Files.readString(Paths.get(currentDir + "/../delete_authors.sql"));
            jdbcTemplate.update(sql);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully deleted all authors"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong"));
        }
    }

    @PostMapping("/run-delete-books-script")
    ResponseEntity<?> deleteAllBooks(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }
        try {
            String currentDir = System.getProperty("user.dir");
            String sql = Files.readString(Paths.get(currentDir + "/../delete_books.sql"));
            jdbcTemplate.update(sql);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully deleted all books"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong (make sure you deleted librarybooks first)"));
        }
    }

    @PostMapping("/run-delete-libraries-script")
    ResponseEntity<?> deleteAllLibraries(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }
        try {
            String currentDir = System.getProperty("user.dir");
            String sql = Files.readString(Paths.get(currentDir + "/../delete_libraries.sql"));
            jdbcTemplate.update(sql);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully deleted all libraries"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong (make sure you deleted librarybooks first)"));
        }
    }

    @PostMapping("/run-delete-librarybooks-script")
    ResponseEntity<?> deleteAllLibraryBooks(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }
        try {
            String currentDir = System.getProperty("user.dir");
            String sql = Files.readString(Paths.get(currentDir + "/../delete_librarybooks.sql"));
            jdbcTemplate.update(sql);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully deleted all librarybooks"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong"));
        }
    }

    @PostMapping("/run-insert-authors-script")
    ResponseEntity<?> insertAllAuthors(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }

        try {
            String currentDir = System.getProperty("user.dir");
            String fullPath = currentDir + "/../insert_authors.sql";

            String fileContent = new String(Files.readAllBytes(Paths.get(fullPath)));

            String[] statements = fileContent.split(";");

            for (String statement : statements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    jdbcTemplate.execute(statement);
                }
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully inserted all authors"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong"));
        }
    }



    @PostMapping("/run-insert-books-script")
    public ResponseEntity<?> insertAllBooks(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }

        try {
            String currentDir = System.getProperty("user.dir");
            String fullPath = currentDir + "/../insert_books.sql";

            String fileContent = new String(Files.readAllBytes(Paths.get(fullPath)));

            // Split the SQL script by the delimiter "--"
            String[] statements = fileContent.split("--");

            // Execute each statement
            for (String statement : statements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    // Execute the statement using Spring JDBC
                    jdbcTemplate.execute(statement);
                }
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully inserted all books"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong"));
        }
    }

    @PostMapping("/run-insert-libraries-script")
    ResponseEntity<?> insertAllLibraries(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }

        try {
            String currentDir = System.getProperty("user.dir");
            String fullPath = currentDir + "/../insert_libraries.sql";

            String fileContent = new String(Files.readAllBytes(Paths.get(fullPath)));

            String[] statements = fileContent.split(";");

            for (String statement : statements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    jdbcTemplate.execute(statement);
                }
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully inserted all libraries"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong"));
        }
    }

    @PostMapping("/run-insert-librarybooks-script")
    ResponseEntity<?> insertAllLibraryBooks(@RequestHeader("Authorization") String token) {
        String username = this.jwtUtils.getUserNameFromJwtToken(token);
        User user = this.userService.getUserByUsername(username);

        boolean isAdmin = user.getRoles().stream().anyMatch((role) ->
                role.getName() == ERole.ROLE_ADMIN
        );

        if (!isAdmin) {
            throw new UserNotAuthorizedException(String.format(user.getUsername()));
        }

        try {
            String currentDir = System.getProperty("user.dir");
            String fullPath = currentDir + "/../insert_librarybooks.sql";

            String fileContent = new String(Files.readAllBytes(Paths.get(fullPath)));

            String[] statements = fileContent.split(";");

            for (String statement : statements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    jdbcTemplate.execute(statement);
                }
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Successfully inserted all librarybooks"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SQLRunResponseDTO("Error: something went wrong"));
        }
    }
}
