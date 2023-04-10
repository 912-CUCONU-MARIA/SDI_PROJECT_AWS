CREATE TABLE IF NOT EXISTS public.item (
    id bigint NOT NULL,
    item_effect character varying(255),
    item_level bigint,
    item_name character varying(255),
    item_rarity character varying(255),
    item_type character varying(255)
);