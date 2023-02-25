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
    create_at TIMESTAMP WITH TIME ZONE NOT NULL ,
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



