CREATE DATABASE demotivators WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
Alter database demotivators owner to postgres;

\connect demotivators

CREATE TABLE public.users
(
    "id"            bigserial primary key,
    "login"         varchar(40) NOT NULL unique,
    "creation_date" date        NOT NULL default current_date,
    "password"      varchar(40) NOT NULL
);


ALTER TABLE public.users
    OWNER TO postgres;