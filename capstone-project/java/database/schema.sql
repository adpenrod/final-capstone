BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    user_id SERIAL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);


DROP TABLE IF EXISTS type CASCADE;
CREATE TABLE type (
    type_id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(50) NOT NULL UNIQUE
);


DROP TABLE IF EXISTS attraction CASCADE;
CREATE TABLE attraction (
    attraction_id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(200) NOT NULL,
    description CHARACTER VARYING(255),
    hours_of_operation CHARACTER VARYING(200),
    address CHARACTER VARYING(255),
    images CHARACTER VARYING(255),
    social_media CHARACTER VARYING(255),
    type_id INTEGER,
    latitude NUMERIC(12, 6),
    longitude NUMERIC(12, 6),
    CONSTRAINT FK_type FOREIGN KEY (type_id) REFERENCES type(type_id)
);


DROP TABLE IF EXISTS checkin CASCADE;
CREATE TABLE checkin (
    checkin_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    attraction_id INTEGER NOT NULL,
    checkin_time TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    notes CHARACTER VARYING(255),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_attraction FOREIGN KEY (attraction_id) REFERENCES attraction(attraction_id)
);


DROP TABLE IF EXISTS badge CASCADE;
CREATE TABLE badge (
    badge_id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(100) NOT NULL UNIQUE,
    description CHARACTER VARYING(255) NOT NULL,
    locked_image CHARACTER VARYING(255),
    unlocked_image CHARACTER VARYING(255),
    unlocked BOOLEAN,
    criteria CHARACTER VARYING(255)
);


DROP TABLE IF EXISTS user_badge CASCADE;
CREATE TABLE user_badge (
    user_id INTEGER NOT NULL,
    badge_id INTEGER NOT NULL,
    earned_time TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, badge_id),
    CONSTRAINT FK_user_badge FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_badge FOREIGN KEY (badge_id) REFERENCES badge(badge_id)
);


DROP TABLE IF EXISTS user_attraction CASCADE;
CREATE TABLE user_attraction (
    user_id INTEGER NOT NULL,
    attraction_id INTEGER NOT NULL,
    has_visited BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (user_id, attraction_id),
    CONSTRAINT FK_user_attraction_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_attraction_attraction FOREIGN KEY (attraction_id) REFERENCES attraction(attraction_id)
);

COMMIT TRANSACTION;