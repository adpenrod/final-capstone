BEGIN TRANSACTION;

DROP TABLE IF EXISTS attraction;

CREATE TABLE IF NOT EXISTS public.attraction
(
    id SERIAL,
    name "char" NOT NULL,
    description "char" NOT NULL,
    hours_of_operation character varying NOT NULL,
    open_now boolean NOT NULL,
    address character varying NOT NULL,
    images character varying NOT NULL,
    social_media "char",
    type_id integer NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS badge;

CREATE TABLE IF NOT EXISTS public.badge
(
    id SERIAL,
    name "char" NOT NULL,
    description "char" NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS type;

CREATE TABLE IF NOT EXISTS public.type
(
    id SERIAL,
    name "char" NOT NULL,
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
