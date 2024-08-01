--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Debian 16.3-1.pgdg120+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: marketing; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.marketing (
    marketingid bigint NOT NULL,
    productid bigint NOT NULL,
    title character varying(255),
    description text,
    platforms character varying(255),
    suggestedprice numeric(10,2)
);


ALTER TABLE public.marketing OWNER TO myuser;

--
-- Name: marketing_marketingid_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.marketing_marketingid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.marketing_marketingid_seq OWNER TO myuser;

--
-- Name: marketing_marketingid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myuser
--

ALTER SEQUENCE public.marketing_marketingid_seq OWNED BY public.marketing.marketingid;


--
-- Name: member; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.member (
    memberid bigint NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE public.member OWNER TO myuser;

--
-- Name: member_memberid_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.member_memberid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.member_memberid_seq OWNER TO myuser;

--
-- Name: member_memberid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myuser
--

ALTER SEQUENCE public.member_memberid_seq OWNED BY public.member.memberid;


--
-- Name: product; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.product (
    productid bigint NOT NULL,
    productname character varying(255) NOT NULL,
    description text NOT NULL,
    price double precision NOT NULL,
    imageurl character varying(255)
);


ALTER TABLE public.product OWNER TO myuser;

--
-- Name: product_productid_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.product_productid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_productid_seq OWNER TO myuser;

--
-- Name: product_productid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myuser
--

ALTER SEQUENCE public.product_productid_seq OWNED BY public.product.productid;


--
-- Name: session; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.session (
    id bigint NOT NULL,
    memberid bigint NOT NULL,
    logintime timestamp without time zone NOT NULL
);


ALTER TABLE public.session OWNER TO myuser;

--
-- Name: session_id_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.session_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.session_id_seq OWNER TO myuser;

--
-- Name: session_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: myuser
--

ALTER SEQUENCE public.session_id_seq OWNED BY public.session.id;


--
-- Name: marketing marketingid; Type: DEFAULT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.marketing ALTER COLUMN marketingid SET DEFAULT nextval('public.marketing_marketingid_seq'::regclass);


--
-- Name: member memberid; Type: DEFAULT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.member ALTER COLUMN memberid SET DEFAULT nextval('public.member_memberid_seq'::regclass);


--
-- Name: product productid; Type: DEFAULT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.product ALTER COLUMN productid SET DEFAULT nextval('public.product_productid_seq'::regclass);


--
-- Name: session id; Type: DEFAULT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.session ALTER COLUMN id SET DEFAULT nextval('public.session_id_seq'::regclass);


--
-- Name: marketing marketing_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.marketing
    ADD CONSTRAINT marketing_pkey PRIMARY KEY (marketingid);


--
-- Name: member member_email_key; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_email_key UNIQUE (email);


--
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (memberid);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (productid);


--
-- Name: session session_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.session
    ADD CONSTRAINT session_pkey PRIMARY KEY (id);


--
-- Name: session fk_member; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.session
    ADD CONSTRAINT fk_member FOREIGN KEY (memberid) REFERENCES public.member(memberid);


--
-- Name: marketing fk_product; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.marketing
    ADD CONSTRAINT fk_product FOREIGN KEY (productid) REFERENCES public.product(productid);


--
-- PostgreSQL database dump complete
--

