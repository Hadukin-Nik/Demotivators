CREATE DATABASE demotivators WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
Alter database demotivators owner to postgres;

\connect demotivators

CREATE TABLE public.users
(
    "id"            bigserial primary key,
    "login"         varchar(40) NOT NULL unique,
    "creation_date" date        NOT NULL default current_date,
    "password"      varchar(40) NOT NULL,
    "token"        varchar(40) NOT NULL unique
);

ALTER TABLE public.users
    OWNER TO postgres;


CREATE TABLE public.meme
(
    "id"            bigserial primary key,
    "name"          varchar(60) NOT NULL,
    "image"         varchar(60) NOT NULL unique,
    "creation_date" date        NOT NULL default current_date
);

ALTER TABLE public.meme
    OWNER TO postgres;
