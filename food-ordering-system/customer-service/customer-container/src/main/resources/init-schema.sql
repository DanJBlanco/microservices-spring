DROP SCHEMA IF EXISTS customer CASCADE ;

CREATE SCHEMA customer;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "customer".customers
(
    id uuid not null ,
    username character varying COLLATE pg_catalog."default" NOT NULL ,
    first_name character varying COLLATE pg_catalog."default" NOT NULL ,
    last_name character varying COLLATE pg_catalog."default" NOT NULL ,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

DROP MATERIALIZED VIEW IF EXISTS customer.order_customer_m_view;

CREATE MATERIALIZED VIEW customer.order_customer_m_view
TABLESPACE pg_default
as
    SELECT id,
           username,
           first_name,
           last_name
FROM customer.customers
WITH DATA ;

refresh materialized view customer.order_customer_m_view;

CREATE OR REPLACE function customer.refresh_order_customer_m_view()
returns trigger
AS '
    BEGIN
        refresh materialized view customer.order_customer_m_view;
        return null;
end;
' LANGUAGE plpgsql;

DROP Trigger if exists refresh_order_customer_m_view ON customer.customers;

CREATE TRIGGER refresh_order_customer_m_view
    AFTER INSERT OR UPDATE OR DELETE OR TRUNCATE
    ON customer.customers FOR Each statement
    EXECUTE PROCEDURE customer.refresh_order_customer_m_view();