DROp SCHEMA IF EXISTS payment CASCADE ;

CREATE SCHEMA payment;

CREATE EXTENSION IF NOT EXISTS  "uuid-ossp";

DROP TYPE IF EXISTS payment_status;

CREATE TYPE payment_status as ENUM ('COMPLETED', 'CANCELLED', 'FAILED');

DROP TABLE IF EXISTS "payment".payments CASCADE ;

CREATE TABLE "payment".payments
(
    id uuid NOT NULL ,
    customer_id uuid NOT NULL,
    order_id uuid NOT NULL ,
    price numeric(10,2) NOT NULL ,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL ,
    status payment_status NOT NULL ,
    CONSTRAINT payments_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "payment".credit_entry CASCADE ;
CREATE TABLE "payment".credit_entry
(
    id uuid NOT NULL ,
    customer_id uuid not null ,
    total_credit_amount numeric(10,2) not null ,
    CONSTRAINT credit_entry_pkey PRIMARY KEY (id)
);

DROP TYPE IF EXISTS transaction_type;

CREATE TYPE transaction_type as ENUM ('DEBIT', 'CREDIT' );

DROP TABLE IF EXISTS "payment".credit_history CASCADE ;

CREATE TABLE "payment".credit_history
(
    id uuid not null ,
    customer_id uuid not null ,
    amount numeric(10,2) not null ,
    type transaction_type not null ,
    CONSTRAINT credit_history_pkey PRIMARY KEY (id)
);

DROP TYPE IF EXISTS outbox_status;
CREATE TYPE outbox_status AS ENUM ('STARTED', 'COMPLETED', 'FAILED');

DROP TABLE IF EXISTS "payment".order_outbox CASCADE;
CREATE TABLE "payment".order_outbox
(
    id uuid not null ,
    saga_id uuid not null ,
    created_at timestamp with time zone not null ,
    processed_at timestamp with time zone,
    type character varying COLLATE pg_catalog."default" not null ,
    payload jsonb not null ,
    outbox_status outbox_status not null ,
    payment_status payment_status not null ,
    version integer not null ,
    CONSTRAINT order_outbox_pkey PRIMARY KEY (id)
);


CREATE INDEX "payment_order_outbox_saga_status"
    ON "payment".order_outbox
        (type, payment_status);


CREATE INDEX "payment_order_outbox_saga_id_payment_status_outbox_status"
    ON "payment".order_outbox
        (type, saga_id, payment_status, outbox_status);

