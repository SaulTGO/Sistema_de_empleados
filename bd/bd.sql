--
-- PostgreSQL database dump
--

\restrict gHX6ZfRT4r7hvYkowtdvKlsbuRvK0hcMJvHA3dzTxkavZnuDJnGkC9xQzXS4DPe

-- Dumped from database version 18.6 (Debian 18.6-1.pgdg13+2)
-- Dumped by pg_dump version 18.6 (Debian 18.6-1.pgdg13+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: empleado; Type: TABLE; Schema: public; Owner: saul
--

CREATE TABLE public.empleado (
    id bigint NOT NULL,
    nombre character varying(255),
    apellidos character varying(100),
    email character varying(255),
    apellido character varying(255)
);


ALTER TABLE public.empleado OWNER TO saul;

--
-- Name: empleado_id_seq; Type: SEQUENCE; Schema: public; Owner: saul
--

CREATE SEQUENCE public.empleado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.empleado_id_seq OWNER TO saul;

--
-- Name: empleado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: saul
--

ALTER SEQUENCE public.empleado_id_seq OWNED BY public.empleado.id;


--
-- Name: empleado id; Type: DEFAULT; Schema: public; Owner: saul
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id SET DEFAULT nextval('public.empleado_id_seq'::regclass);


--
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: saul
--

COPY public.empleado (id, nombre, apellidos, email, apellido) FROM stdin;
1	Juan	Perez	hola@hola.com	\N
2	Elsa	\N	algo@dominio.mx	Vavidas
\.


--
-- Name: empleado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: saul
--

SELECT pg_catalog.setval('public.empleado_id_seq', 2, true);


--
-- Name: empleado empleado_email_key; Type: CONSTRAINT; Schema: public; Owner: saul
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_email_key UNIQUE (email);


--
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: saul
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

\unrestrict gHX6ZfRT4r7hvYkowtdvKlsbuRvK0hcMJvHA3dzTxkavZnuDJnGkC9xQzXS4DPe

