--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgresA WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE postgresA OWNER TO postgres;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
--Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgresA IS 'default administrative connection database';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: audition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.audition (
    "User_id" bigint,
    "Date" "char",
    "Action_type" "char",
    "Before" "char",
    "After" "char"
);


ALTER TABLE public.audition OWNER TO postgres;

--
-- Name: collections; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.collections (
    "Collection_id" bigint NOT NULL,
    "Creator_id" bigint NOT NULL,
    "Name" text,
    "Description" text,
    "Image" character(60),
    "IsPrivate" boolean
);


ALTER TABLE public.collections OWNER TO postgres;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    "Comment_id" bigint NOT NULL,
    "Mem_id" bigint NOT NULL,
    "User_id" bigint NOT NULL,
    "Date" date,
    "Text" text NOT NULL,
    "Likes" bigint,
    "Dislikes" bigint
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- Name: downloads; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.downloads (
    "UserId" bigint,
    "CollectionId" bigint,
    "CreationDate" date,
    "UpdateDate" date,
    "Process" bigint
);


ALTER TABLE public.downloads OWNER TO postgres;

--
-- Name: friendshipRequests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."friendshipRequests" (
    "ToUserId" bigint NOT NULL,
    "FromUserId" bigint NOT NULL,
    "RequestId" bigint NOT NULL,
    "Text" text,
    "Create_date" date,
    "Update_date" date,
    "Status_isWatched" boolean,
    "Status_isApproved" boolean
);


ALTER TABLE public."friendshipRequests" OWNER TO postgres;

--
-- Name: memToCollection; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."memToCollection" (
    "Mem_id" bigint NOT NULL,
    "Collection_id" bigint NOT NULL
);


ALTER TABLE public."memToCollection" OWNER TO postgres;

--
-- Name: mem_patterns; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mem_patterns (
    "Name" "char",
    "Picture" "char",
    "Tags" "char",
    "User_id" bigint,
    "Creation_date" "char"
);


ALTER TABLE public.mem_patterns OWNER TO postgres;

--
-- Name: memes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.memes (
    "Mem_id" bigint NOT NULL,
    "User_id" bigint NOT NULL,
    "Picture" character(60),
    "CreationDate" date,
    "UpdateDate" date,
    "CommentsAllowed" boolean,
    "Description" text,
    "Tags" text
);


ALTER TABLE public.memes OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    "id" serial primary key,
    "login" varchar(40) NOT NULL unique,
    "creation_date" date NOT NULL default current_date,
    "password" varchar(40) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;


--
-- Name: friendshipRequests FriendshipRequests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."friendshipRequests"
    ADD CONSTRAINT "FriendshipRequests_pkey" PRIMARY KEY ("RequestId");


--
-- Name: collections collections_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collections
    ADD CONSTRAINT collections_pkey PRIMARY KEY ("Collection_id");


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY ("Comment_id");


--
-- Name: memes memes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.memes
    ADD CONSTRAINT memes_pkey PRIMARY KEY ("Mem_id");

--
-- PostgreSQL database dump complete
--

