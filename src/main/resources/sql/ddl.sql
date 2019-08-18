-- CREATE DATABASE town_for_every_repository;
-- CREATE SCHEMA town_for_every;

CREATE TABLE town_for_every.town
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE town_for_every.town_information
(
    id          BIGSERIAL PRIMARY KEY,
    town_id     BIGINT REFERENCES town_for_every.town (id) ON DELETE CASCADE ON UPDATE CASCADE,
    description TEXT
);
