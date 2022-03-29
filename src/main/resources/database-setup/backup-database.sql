--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

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

ALTER TABLE ONLY public.device_service DROP CONSTRAINT fkq8sei3s1ar2ou7pja6kotrr4n;
ALTER TABLE ONLY public.device DROP CONSTRAINT fko9oabhnk3f79y77ifapu6yp7t;
ALTER TABLE ONLY public.device_service DROP CONSTRAINT fknp3nuya38hgmdaqugbbj2gxln;
ALTER TABLE ONLY public.device_service DROP CONSTRAINT fk6h3nlyhe7l4shjpljtmy8c5pw;
ALTER TABLE ONLY public.service_catalog DROP CONSTRAINT uc_servicecatalog_name;
ALTER TABLE ONLY public.device_service DROP CONSTRAINT uc_deviceservice_device_id;
ALTER TABLE ONLY public.service_catalog DROP CONSTRAINT service_catalog_pkey;
ALTER TABLE ONLY public.device_type DROP CONSTRAINT device_type_pkey;
ALTER TABLE ONLY public.device_service DROP CONSTRAINT device_service_pkey;
ALTER TABLE ONLY public.device DROP CONSTRAINT device_pkey;
ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
DROP TABLE public.service_catalog;
DROP TABLE public.device_type;
DROP TABLE public.device_service;
DROP TABLE public.device;
DROP TABLE public.customer;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id uuid NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    national_document_id character varying(255)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: device; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.device (
    id uuid NOT NULL,
    device_type_id uuid NOT NULL,
    has_antivirus boolean NOT NULL,
    system_name character varying(255)
);


ALTER TABLE public.device OWNER TO postgres;

--
-- Name: device_service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.device_service (
    id uuid NOT NULL,
    customer_id uuid NOT NULL,
    device_id uuid NOT NULL,
    service_catalog_id uuid
);


ALTER TABLE public.device_service OWNER TO postgres;

--
-- Name: device_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.device_type (
    id uuid NOT NULL,
    antivirus_cost double precision,
    device_management_cost double precision,
    name character varying(255)
);


ALTER TABLE public.device_type OWNER TO postgres;

--
-- Name: service_catalog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.service_catalog (
    id uuid NOT NULL,
    cost double precision,
    name character varying(255)
);


ALTER TABLE public.service_catalog OWNER TO postgres;

--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (id, first_name, last_name, national_document_id) FROM stdin;
40e8d8e3-7eff-4f97-aed0-42d28c965dff	Christian	Barahona	1002556148
59209b77-4e32-4bf0-9ded-c886d2fe0199	Christian	Barahona	1002556148
37078504-f017-4290-bd05-56972fca1668	Christian	Barahona	1002556148
073c1ecf-4851-423b-874c-398ddb1ea1a3	Christian	Barahona	1002556148
ab17cd96-07d4-46f1-ae10-b46b7a8d9188	Christian	Barahona	1002556148
c3765f68-ffe4-4438-bb97-a7ce226ca2cb	Christian	Barahona	1002556148
453b2b11-27bc-4fce-b634-b7168ac706ae	Christian	Barahona	1002556148
a5bf2bd0-ef47-4876-a86f-b06079cd37dc	Christian	Barahona	1002556148
\.


--
-- Data for Name: device; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.device (id, device_type_id, has_antivirus, system_name) FROM stdin;
08b833e3-3386-410f-b54f-6bbeaae5f7e4	c22d07ae-7e3a-4cb6-b6b2-b333dd961c4b	t	Windows 0
85607d47-a345-4fe0-b6d4-f3701acbbd01	c22d07ae-7e3a-4cb6-b6b2-b333dd961c4b	t	Windows 1
e5080fe5-ba75-409b-ac52-68809ce6a60b	76125bf9-db8d-4003-9309-3c38c2edb223	t	Mac 0
0ba60d2e-c4d0-4bf1-8265-6212fd27d4cd	76125bf9-db8d-4003-9309-3c38c2edb223	t	Mac 1
eba32964-4796-45b1-87cb-3c39681ec4ce	76125bf9-db8d-4003-9309-3c38c2edb223	t	Mac 2
4f2eac5b-deca-4aa1-98dc-1354ffe36195	967373fd-60d3-4881-a83c-453bc092a668	f	WinSer01
07bd3291-254d-4c32-8b83-3c4a6a9ac658	58efc693-ad71-4004-9185-9fcfc904f7e0	f	WinSer01
56f910e4-af15-43d8-a0b0-70158af035be	41086c1c-3d36-484e-b6f2-1ee25014b0c6	t	Windows 0
0099b91a-5ccf-4357-be61-ca7c551c63e3	41086c1c-3d36-484e-b6f2-1ee25014b0c6	t	Windows 1
a7b9efd9-72c1-452c-8261-e0ed146e68b3	07846ac4-b99a-457e-8eea-0a217c8bb94a	t	Mac 0
f013febf-4643-443b-a52e-92ca53a3a5a3	07846ac4-b99a-457e-8eea-0a217c8bb94a	t	Mac 1
e44ba3bf-5ed3-4cac-a2f8-30fa5e9a645a	07846ac4-b99a-457e-8eea-0a217c8bb94a	t	Mac 2
bf4e00ea-a16e-4ec8-8552-5282c8810614	51a9cf8c-6905-456c-9955-03ffbf218833	f	WinSer01
7391c99e-ca52-4053-8b58-b374e4e519ed	ecf5a1bf-1c20-4992-9672-f605c9446fd7	f	WinSer01
a7b9af6e-031a-4a57-b184-1c2c43dd446a	ee71ffcf-d8e9-4081-93a2-13d33f451d86	t	Windows 0
f0cd5903-dbc2-4446-aa9c-6fe32fac0fd8	ee71ffcf-d8e9-4081-93a2-13d33f451d86	t	Windows 1
8638183f-4335-49fd-8557-41eac7c1526e	6ad6ab90-8371-4b78-a435-9c5b742587e4	t	Mac 0
e3fc76d3-6e63-4ec0-8510-0ad98bcdc67d	6ad6ab90-8371-4b78-a435-9c5b742587e4	t	Mac 1
37c72e7b-d37e-42df-b49c-f923c974c3ba	6ad6ab90-8371-4b78-a435-9c5b742587e4	t	Mac 2
993d6924-1378-4822-a781-2b263bb6b2d0	8896e8fb-4582-4235-a0a2-9c83805dacdb	t	Windows 0
c9cd1f33-2f4f-485a-8d78-4530a60732f2	8896e8fb-4582-4235-a0a2-9c83805dacdb	t	Windows 1
8efe7d7e-2286-4aef-acdf-fcedcca8984a	cc651ddc-b76a-4ef2-a07b-4fbca3fe17e1	t	Mac 0
1d971206-9bcd-4a5c-8e51-29d6e1ba044b	cc651ddc-b76a-4ef2-a07b-4fbca3fe17e1	t	Mac 1
a8a03fa2-08d5-41d4-b22b-f9a979ce6cfb	cc651ddc-b76a-4ef2-a07b-4fbca3fe17e1	t	Mac 2
05aac66d-0549-40e4-a3c5-bd9f1c82f25b	cc651ddc-b76a-4ef2-a07b-4fbca3fe17e1	t	Mac 3
\.


--
-- Data for Name: device_service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.device_service (id, customer_id, device_id, service_catalog_id) FROM stdin;
638f0e8e-dcd9-4ad0-ba9c-256b0be0197f	40e8d8e3-7eff-4f97-aed0-42d28c965dff	08b833e3-3386-410f-b54f-6bbeaae5f7e4	89dac0ab-b4c0-4008-84f2-7aff8f100138
2ec4c3b6-8b4a-4ce3-b5f6-9ad3a556387e	40e8d8e3-7eff-4f97-aed0-42d28c965dff	08b833e3-3386-410f-b54f-6bbeaae5f7e4	06e2b2eb-7ab7-47b2-bdf1-20465f6667f6
cdd595fc-3492-4681-9b19-e6acbf1b52e2	40e8d8e3-7eff-4f97-aed0-42d28c965dff	08b833e3-3386-410f-b54f-6bbeaae5f7e4	765d4cd2-3f9b-4c04-b5df-a1ac2a2a53f8
690e3130-0d16-44e6-a3f5-85690c090cdb	40e8d8e3-7eff-4f97-aed0-42d28c965dff	85607d47-a345-4fe0-b6d4-f3701acbbd01	89dac0ab-b4c0-4008-84f2-7aff8f100138
04a130dd-7255-48ea-bcfa-9a1a2e7e2405	40e8d8e3-7eff-4f97-aed0-42d28c965dff	85607d47-a345-4fe0-b6d4-f3701acbbd01	06e2b2eb-7ab7-47b2-bdf1-20465f6667f6
91ab028a-d6ad-494e-ae3a-4f47a17e9cab	40e8d8e3-7eff-4f97-aed0-42d28c965dff	85607d47-a345-4fe0-b6d4-f3701acbbd01	765d4cd2-3f9b-4c04-b5df-a1ac2a2a53f8
3e87379e-7314-43dd-ae74-3bdc8a62e676	40e8d8e3-7eff-4f97-aed0-42d28c965dff	e5080fe5-ba75-409b-ac52-68809ce6a60b	89dac0ab-b4c0-4008-84f2-7aff8f100138
151caf72-f91b-49ca-8789-0f733c0a8b88	40e8d8e3-7eff-4f97-aed0-42d28c965dff	e5080fe5-ba75-409b-ac52-68809ce6a60b	06e2b2eb-7ab7-47b2-bdf1-20465f6667f6
d3f70979-b2bc-426c-a5a3-b84921b3838c	40e8d8e3-7eff-4f97-aed0-42d28c965dff	e5080fe5-ba75-409b-ac52-68809ce6a60b	765d4cd2-3f9b-4c04-b5df-a1ac2a2a53f8
25145124-00d7-4bff-b39c-a0bc66c5db4b	40e8d8e3-7eff-4f97-aed0-42d28c965dff	0ba60d2e-c4d0-4bf1-8265-6212fd27d4cd	89dac0ab-b4c0-4008-84f2-7aff8f100138
b1907276-16b6-4581-b696-2a5d3d2a6c58	40e8d8e3-7eff-4f97-aed0-42d28c965dff	0ba60d2e-c4d0-4bf1-8265-6212fd27d4cd	06e2b2eb-7ab7-47b2-bdf1-20465f6667f6
7256e044-c329-4ab0-b7d9-5e4700a6991b	40e8d8e3-7eff-4f97-aed0-42d28c965dff	0ba60d2e-c4d0-4bf1-8265-6212fd27d4cd	765d4cd2-3f9b-4c04-b5df-a1ac2a2a53f8
e6bf79be-ac79-4034-bb05-b41595c43d03	40e8d8e3-7eff-4f97-aed0-42d28c965dff	eba32964-4796-45b1-87cb-3c39681ec4ce	89dac0ab-b4c0-4008-84f2-7aff8f100138
eba16915-a575-43b1-af16-3d5eb60b177f	40e8d8e3-7eff-4f97-aed0-42d28c965dff	eba32964-4796-45b1-87cb-3c39681ec4ce	06e2b2eb-7ab7-47b2-bdf1-20465f6667f6
629ebdfd-b129-4e10-bc5d-3a9765812cc9	40e8d8e3-7eff-4f97-aed0-42d28c965dff	eba32964-4796-45b1-87cb-3c39681ec4ce	765d4cd2-3f9b-4c04-b5df-a1ac2a2a53f8
67968ad4-1140-4dd8-add5-a99de20c790e	37078504-f017-4290-bd05-56972fca1668	07bd3291-254d-4c32-8b83-3c4a6a9ac658	0c996594-d7fb-40f8-938c-b8dce415b355
ce79a765-2a94-4c34-937f-e4fc0a416479	37078504-f017-4290-bd05-56972fca1668	07bd3291-254d-4c32-8b83-3c4a6a9ac658	e10b9644-3ebe-47f0-9c56-efa2607fac9c
720a5811-5d4d-4355-8137-e962d50a186d	37078504-f017-4290-bd05-56972fca1668	07bd3291-254d-4c32-8b83-3c4a6a9ac658	1e0cab9e-5e97-42d3-986f-92f98a5fcf08
c4874efa-bef7-47b6-8096-2ebc7b52c7da	073c1ecf-4851-423b-874c-398ddb1ea1a3	56f910e4-af15-43d8-a0b0-70158af035be	89dac0ab-b4c0-4008-84f2-7aff8f100138
21536a82-c53b-4abb-9e30-dcd9abec1ee5	073c1ecf-4851-423b-874c-398ddb1ea1a3	56f910e4-af15-43d8-a0b0-70158af035be	26906364-b132-480a-a0c3-34f88ddfe1e7
80eb10b9-e057-45af-8e9e-875e3ae78daa	073c1ecf-4851-423b-874c-398ddb1ea1a3	56f910e4-af15-43d8-a0b0-70158af035be	a1b78eea-0ad2-49c2-8131-e651d5e9fafa
7e1fe934-6d68-4ec8-af54-aef23b46d090	073c1ecf-4851-423b-874c-398ddb1ea1a3	0099b91a-5ccf-4357-be61-ca7c551c63e3	89dac0ab-b4c0-4008-84f2-7aff8f100138
56e52173-ba18-47fc-882d-9b1f02899d7f	073c1ecf-4851-423b-874c-398ddb1ea1a3	0099b91a-5ccf-4357-be61-ca7c551c63e3	26906364-b132-480a-a0c3-34f88ddfe1e7
152ee3b3-ef18-4e81-9c55-9484d7f3a96d	073c1ecf-4851-423b-874c-398ddb1ea1a3	0099b91a-5ccf-4357-be61-ca7c551c63e3	a1b78eea-0ad2-49c2-8131-e651d5e9fafa
45affb58-5f62-4f4a-b663-26ff844dae5e	073c1ecf-4851-423b-874c-398ddb1ea1a3	a7b9efd9-72c1-452c-8261-e0ed146e68b3	89dac0ab-b4c0-4008-84f2-7aff8f100138
f70f1bf4-f088-4374-b10b-4e97de0fb027	073c1ecf-4851-423b-874c-398ddb1ea1a3	a7b9efd9-72c1-452c-8261-e0ed146e68b3	26906364-b132-480a-a0c3-34f88ddfe1e7
8c584d26-1bc9-4403-9f17-e8f8455b161d	073c1ecf-4851-423b-874c-398ddb1ea1a3	a7b9efd9-72c1-452c-8261-e0ed146e68b3	a1b78eea-0ad2-49c2-8131-e651d5e9fafa
b1ba84a3-7986-4325-93de-96d40e10f339	073c1ecf-4851-423b-874c-398ddb1ea1a3	f013febf-4643-443b-a52e-92ca53a3a5a3	89dac0ab-b4c0-4008-84f2-7aff8f100138
4787c508-e51b-4b5c-a954-55a3b4a9dc57	073c1ecf-4851-423b-874c-398ddb1ea1a3	f013febf-4643-443b-a52e-92ca53a3a5a3	26906364-b132-480a-a0c3-34f88ddfe1e7
708ec07f-8aa4-445a-a72a-f462bee110f3	073c1ecf-4851-423b-874c-398ddb1ea1a3	f013febf-4643-443b-a52e-92ca53a3a5a3	a1b78eea-0ad2-49c2-8131-e651d5e9fafa
73ad62d1-4cb8-44cc-87b3-743f975d2675	073c1ecf-4851-423b-874c-398ddb1ea1a3	e44ba3bf-5ed3-4cac-a2f8-30fa5e9a645a	89dac0ab-b4c0-4008-84f2-7aff8f100138
de4c8800-76f1-4497-b47e-19f6474d3a45	073c1ecf-4851-423b-874c-398ddb1ea1a3	e44ba3bf-5ed3-4cac-a2f8-30fa5e9a645a	26906364-b132-480a-a0c3-34f88ddfe1e7
1773463a-dca2-4cfb-aa90-48a0974c34cd	073c1ecf-4851-423b-874c-398ddb1ea1a3	e44ba3bf-5ed3-4cac-a2f8-30fa5e9a645a	a1b78eea-0ad2-49c2-8131-e651d5e9fafa
fefbb75d-d759-4d5d-97b5-3bb9480f24a6	c3765f68-ffe4-4438-bb97-a7ce226ca2cb	7391c99e-ca52-4053-8b58-b374e4e519ed	caae7e82-38c0-4ed0-8eb6-33dd5e2320c4
bfc1282f-9027-4dc4-849a-85210c9af6a8	c3765f68-ffe4-4438-bb97-a7ce226ca2cb	7391c99e-ca52-4053-8b58-b374e4e519ed	6085cf31-7909-4250-95bb-35c185f7d71d
4c290d6e-f947-4cee-b558-b3f9d5a1acba	c3765f68-ffe4-4438-bb97-a7ce226ca2cb	7391c99e-ca52-4053-8b58-b374e4e519ed	282e066d-0031-449a-98f6-957cab270289
888a6c58-990a-4a2b-af13-bd07cb069ec4	453b2b11-27bc-4fce-b634-b7168ac706ae	a7b9af6e-031a-4a57-b184-1c2c43dd446a	89dac0ab-b4c0-4008-84f2-7aff8f100138
d00963eb-c5f4-4068-a8de-1fb6b3efe970	453b2b11-27bc-4fce-b634-b7168ac706ae	a7b9af6e-031a-4a57-b184-1c2c43dd446a	2dc7e060-8822-4019-9bc5-e2f9b19b9895
f09e229a-ba37-4cc3-8407-72b8e3ed0466	453b2b11-27bc-4fce-b634-b7168ac706ae	a7b9af6e-031a-4a57-b184-1c2c43dd446a	81494526-cba7-4068-a6f3-9b89f990d93c
53db1cd7-d740-4675-9e6f-1183fb21a755	453b2b11-27bc-4fce-b634-b7168ac706ae	f0cd5903-dbc2-4446-aa9c-6fe32fac0fd8	89dac0ab-b4c0-4008-84f2-7aff8f100138
4199158d-c441-4743-8317-9eb2111954ec	453b2b11-27bc-4fce-b634-b7168ac706ae	f0cd5903-dbc2-4446-aa9c-6fe32fac0fd8	2dc7e060-8822-4019-9bc5-e2f9b19b9895
f2bfa4eb-54f2-464e-bb07-c13e375bdf5f	453b2b11-27bc-4fce-b634-b7168ac706ae	f0cd5903-dbc2-4446-aa9c-6fe32fac0fd8	81494526-cba7-4068-a6f3-9b89f990d93c
5fbfe97a-7ea9-47eb-973f-dd11ea2f5a9d	453b2b11-27bc-4fce-b634-b7168ac706ae	8638183f-4335-49fd-8557-41eac7c1526e	89dac0ab-b4c0-4008-84f2-7aff8f100138
96e7ae32-6416-4c4c-9a3c-9c66f55a3ad4	453b2b11-27bc-4fce-b634-b7168ac706ae	8638183f-4335-49fd-8557-41eac7c1526e	2dc7e060-8822-4019-9bc5-e2f9b19b9895
c8093946-4359-4bab-9ad8-0a40f1e34191	453b2b11-27bc-4fce-b634-b7168ac706ae	8638183f-4335-49fd-8557-41eac7c1526e	81494526-cba7-4068-a6f3-9b89f990d93c
76695494-2a26-43c3-9756-08b5160d67f4	453b2b11-27bc-4fce-b634-b7168ac706ae	e3fc76d3-6e63-4ec0-8510-0ad98bcdc67d	89dac0ab-b4c0-4008-84f2-7aff8f100138
d13ad243-00bc-4880-9f2f-f2e529abec81	453b2b11-27bc-4fce-b634-b7168ac706ae	e3fc76d3-6e63-4ec0-8510-0ad98bcdc67d	2dc7e060-8822-4019-9bc5-e2f9b19b9895
2d053cca-0787-4c5f-98d9-4b519c779f2e	453b2b11-27bc-4fce-b634-b7168ac706ae	e3fc76d3-6e63-4ec0-8510-0ad98bcdc67d	81494526-cba7-4068-a6f3-9b89f990d93c
b2b0b6d3-cad7-4720-997b-1f8f246ec75b	453b2b11-27bc-4fce-b634-b7168ac706ae	37c72e7b-d37e-42df-b49c-f923c974c3ba	89dac0ab-b4c0-4008-84f2-7aff8f100138
a399ff88-fad4-47b0-8134-4e85ce44da0d	453b2b11-27bc-4fce-b634-b7168ac706ae	37c72e7b-d37e-42df-b49c-f923c974c3ba	2dc7e060-8822-4019-9bc5-e2f9b19b9895
a6804f4d-ec89-4330-bf98-2b9474b2c8f2	453b2b11-27bc-4fce-b634-b7168ac706ae	37c72e7b-d37e-42df-b49c-f923c974c3ba	81494526-cba7-4068-a6f3-9b89f990d93c
bdef4ccf-9b3f-4fea-b6c3-c1567991eb2b	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	993d6924-1378-4822-a781-2b263bb6b2d0	89dac0ab-b4c0-4008-84f2-7aff8f100138
4deeeb48-278c-43f5-836c-205e2c17a729	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	993d6924-1378-4822-a781-2b263bb6b2d0	e451a787-e07e-4f45-9c8a-356156511d1c
17e99f87-bd02-44fa-b079-2251443ca25f	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	993d6924-1378-4822-a781-2b263bb6b2d0	df81a1a5-da46-4d13-a6ec-a1dedb2a23bd
8cfee8c4-1d5b-41e8-b197-34a79eba1e2e	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	c9cd1f33-2f4f-485a-8d78-4530a60732f2	89dac0ab-b4c0-4008-84f2-7aff8f100138
75d9a3bf-d21a-422b-a94f-b4b9897e868e	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	c9cd1f33-2f4f-485a-8d78-4530a60732f2	e451a787-e07e-4f45-9c8a-356156511d1c
c8c079a4-f61b-4694-be28-9d6f58a0f973	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	c9cd1f33-2f4f-485a-8d78-4530a60732f2	df81a1a5-da46-4d13-a6ec-a1dedb2a23bd
2925fc15-2a91-4bb3-8179-20db50a7fa8f	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	8efe7d7e-2286-4aef-acdf-fcedcca8984a	89dac0ab-b4c0-4008-84f2-7aff8f100138
c1cd1bda-6842-4122-82fe-3b9fa5def783	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	8efe7d7e-2286-4aef-acdf-fcedcca8984a	e451a787-e07e-4f45-9c8a-356156511d1c
d0ecec75-880f-4f91-b6a9-893106862036	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	8efe7d7e-2286-4aef-acdf-fcedcca8984a	df81a1a5-da46-4d13-a6ec-a1dedb2a23bd
346fc205-5a6e-483a-97ca-efeb02a05f8e	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	1d971206-9bcd-4a5c-8e51-29d6e1ba044b	89dac0ab-b4c0-4008-84f2-7aff8f100138
4743312d-4398-4493-978f-2d3d9a11b60d	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	1d971206-9bcd-4a5c-8e51-29d6e1ba044b	e451a787-e07e-4f45-9c8a-356156511d1c
94491267-6a3e-4182-9a61-e43d6b0c4879	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	1d971206-9bcd-4a5c-8e51-29d6e1ba044b	df81a1a5-da46-4d13-a6ec-a1dedb2a23bd
795327ce-e7ff-473c-aca3-1ddd7c2a1e3a	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	a8a03fa2-08d5-41d4-b22b-f9a979ce6cfb	89dac0ab-b4c0-4008-84f2-7aff8f100138
695ecb36-bb23-433c-9c21-c6bb2c98280d	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	a8a03fa2-08d5-41d4-b22b-f9a979ce6cfb	e451a787-e07e-4f45-9c8a-356156511d1c
03a81064-b4c5-4e48-935f-4095c4b11165	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	a8a03fa2-08d5-41d4-b22b-f9a979ce6cfb	df81a1a5-da46-4d13-a6ec-a1dedb2a23bd
e3d00507-5098-49ac-b478-dc4913acc319	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	05aac66d-0549-40e4-a3c5-bd9f1c82f25b	89dac0ab-b4c0-4008-84f2-7aff8f100138
4c168845-ded2-4411-a2e4-771bc6ea9cd0	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	05aac66d-0549-40e4-a3c5-bd9f1c82f25b	e451a787-e07e-4f45-9c8a-356156511d1c
04dcbded-0cfa-490b-8f47-c8e70273df1e	a5bf2bd0-ef47-4876-a86f-b06079cd37dc	05aac66d-0549-40e4-a3c5-bd9f1c82f25b	df81a1a5-da46-4d13-a6ec-a1dedb2a23bd
\.


--
-- Data for Name: device_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.device_type (id, antivirus_cost, device_management_cost, name) FROM stdin;
c22d07ae-7e3a-4cb6-b6b2-b333dd961c4b	5	4	Windows Workstation
76125bf9-db8d-4003-9309-3c38c2edb223	7	4	Windows Workstation
cd68498d-5cd8-4cab-917f-fda98cc7312f	5	4	Windows Server
967373fd-60d3-4881-a83c-453bc092a668	5	4	Windows Server
58efc693-ad71-4004-9185-9fcfc904f7e0	5	4	Windows Server
41086c1c-3d36-484e-b6f2-1ee25014b0c6	5	4	Windows Workstation
07846ac4-b99a-457e-8eea-0a217c8bb94a	7	4	Windows Workstation
cc7c2b29-d535-45fe-a80b-4eef8babc176	7	4	Mac
51a9cf8c-6905-456c-9955-03ffbf218833	5	4	Windows Server
ecf5a1bf-1c20-4992-9672-f605c9446fd7	5	4	Windows Server
ee71ffcf-d8e9-4081-93a2-13d33f451d86	5	4	Windows Workstation
6ad6ab90-8371-4b78-a435-9c5b742587e4	7	4	Windows Workstation
8896e8fb-4582-4235-a0a2-9c83805dacdb	5	4	Windows Workstation
cc651ddc-b76a-4ef2-a07b-4fbca3fe17e1	7	4	Windows Workstation
\.


--
-- Data for Name: service_catalog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.service_catalog (id, cost, name) FROM stdin;
06e2b2eb-7ab7-47b2-bdf1-20465f6667f6	1	Team Viewer 46
765d4cd2-3f9b-4c04-b5df-a1ac2a2a53f8	3	Cloudberry 2
89dac0ab-b4c0-4008-84f2-7aff8f100138	4	Monitoring and management service
32035252-79bd-475d-9111-b2cb6296940a	3	Cloudberry backup 4
0c996594-d7fb-40f8-938c-b8dce415b355	3	Cloudberry backup 5
e10b9644-3ebe-47f0-9c56-efa2607fac9c	2	PSA
1e0cab9e-5e97-42d3-986f-92f98a5fcf08	1	Cloudberry backup 6
26906364-b132-480a-a0c3-34f88ddfe1e7	1	Team Viewer 0
a1b78eea-0ad2-49c2-8131-e651d5e9fafa	3	Cloudberry 8
7180259e-8e3b-4623-b885-998ec48f455d	3	Cloudberry backup 1
caae7e82-38c0-4ed0-8eb6-33dd5e2320c4	3	Cloudberry backup 2
6085cf31-7909-4250-95bb-35c185f7d71d	2	PSA 1
282e066d-0031-449a-98f6-957cab270289	1	Cloudberry backup 3
2dc7e060-8822-4019-9bc5-e2f9b19b9895	1	Team Viewer 82
81494526-cba7-4068-a6f3-9b89f990d93c	3	Cloudberry 84
e451a787-e07e-4f45-9c8a-356156511d1c	1	Team Viewer 73
df81a1a5-da46-4d13-a6ec-a1dedb2a23bd	3	Cloudberry 41
\.


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: device device_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);


--
-- Name: device_service device_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device_service
    ADD CONSTRAINT device_service_pkey PRIMARY KEY (id);


--
-- Name: device_type device_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device_type
    ADD CONSTRAINT device_type_pkey PRIMARY KEY (id);


--
-- Name: service_catalog service_catalog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service_catalog
    ADD CONSTRAINT service_catalog_pkey PRIMARY KEY (id);


--
-- Name: device_service uc_deviceservice_device_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device_service
    ADD CONSTRAINT uc_deviceservice_device_id UNIQUE (device_id, service_catalog_id, customer_id);


--
-- Name: service_catalog uc_servicecatalog_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service_catalog
    ADD CONSTRAINT uc_servicecatalog_name UNIQUE (name);


--
-- Name: device_service fk6h3nlyhe7l4shjpljtmy8c5pw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device_service
    ADD CONSTRAINT fk6h3nlyhe7l4shjpljtmy8c5pw FOREIGN KEY (device_id) REFERENCES public.device(id);


--
-- Name: device_service fknp3nuya38hgmdaqugbbj2gxln; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device_service
    ADD CONSTRAINT fknp3nuya38hgmdaqugbbj2gxln FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- Name: device fko9oabhnk3f79y77ifapu6yp7t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT fko9oabhnk3f79y77ifapu6yp7t FOREIGN KEY (device_type_id) REFERENCES public.device_type(id);


--
-- Name: device_service fkq8sei3s1ar2ou7pja6kotrr4n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device_service
    ADD CONSTRAINT fkq8sei3s1ar2ou7pja6kotrr4n FOREIGN KEY (service_catalog_id) REFERENCES public.service_catalog(id);


--
-- PostgreSQL database dump complete
--

