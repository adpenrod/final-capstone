BEGIN TRANSACTION;

DROP TABLE IF EXISTS checkin;

CREATE TABLE IF NOT EXISTS public.checkin
(
    id SERIAL,
    user_id integer NOT NULL,
    attraction_id integer NOT NULL,
    checkin_time timestamp without time zone,
    notes character varying,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS attraction;

CREATE TABLE IF NOT EXISTS public.attraction
(
    id SERIAL,
    name character varying,
    description character varying,
    hours_of_operation character varying,
    address character varying,
    images character varying,
    social_media character varying,
    type_id integer,
    latitude numeric (12,6),
    longitude numeric (12,6),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS badge;

CREATE TABLE IF NOT EXISTS public.badge
(
    id SERIAL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    image character varying,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS type;

CREATE TABLE IF NOT EXISTS public.type
(
    id SERIAL,
    name character varying NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS public.users
(
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL,
   user_id SERIAL,
   email varchar(50) NOT NULL,
   birthday date,
   CONSTRAINT PK_user PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS user_attraction;

CREATE TABLE IF NOT EXISTS public.user_attraction
(
    user_id integer NOT NULL,
    attraction_id integer NOT NULL,
    has_visited boolean NOT NULL
);


DROP TABLE IF EXISTS user_badge;

CREATE TABLE IF NOT EXISTS public.user_badge
(
    user_id integer NOT NULL,
    badge_id integer NOT NULL
);

ALTER TABLE public.user_attraction
    ADD FOREIGN KEY (attraction_id)
    REFERENCES public.attraction (id)
    NOT VALID;


ALTER TABLE public.user_attraction
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (user_id)
    NOT VALID;


ALTER TABLE public.user_badge
    ADD FOREIGN KEY (badge_id)
    REFERENCES public.badge (id)
    NOT VALID;


ALTER TABLE public.user_badge
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (user_id)
    NOT VALID;


ALTER TABLE public.attraction
    ADD FOREIGN KEY (type_id)
    REFERENCES public.type (id)
    NOT VALID;


COMMIT TRANSACTION;
