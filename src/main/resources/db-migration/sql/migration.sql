--liquibase formatted sql

--changeset yevhenii:1
CREATE TABLE IF NOT EXISTS country
(
    id   SERIAL NOT NULL,
    code VARCHAR(255)                         NOT NULL,
    PRIMARY KEY (id)
);

--changeset yevhenii:2
CREATE TABLE IF NOT EXISTS language
(
    id           SERIAL NOT NULL,
    localization VARCHAR(255)                         NOT NULL,
    PRIMARY KEY (id)
);

--changeset yevhenii:3
CREATE TABLE IF NOT EXISTS country_language
(
    id          SERIAL NOT NULL,
    country_id  INT                                  NOT NULL,
    language_id INT                                  NOT NULL,
    translate   VARCHAR(255)                         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (country_id) REFERENCES country (id),
    FOREIGN KEY (language_id) REFERENCES language (id)
);
--changeset yevhenii:4 context:!test
INSERT INTO country(code)
VALUES ('001'),
       ('002');

--changeset yevhenii:5 context:!test
INSERT INTO language(localization)
VALUES ('UK'),
       ('RU'),
       ('EN');

--changeset yevhenii:6 context:!test
INSERT INTO country_language(translate, country_id, language_id)
VALUES ('Україна', 1, 1),
       ('Украина', 1, 2),
       ('Ukraine', 1, 3),
       ('Фінляндія', 2, 1),
       ('Финляндия', 2, 2),
       ('Finland', 2, 3);

--changeset yevhenii:7 context:!test
INSERT INTO country(code)
VALUES ('003');

--changeset yevhenii:8 context:!test
INSERT INTO language(localization)
VALUES ('LI');