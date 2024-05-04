CREATE TABLE state_invoice
(
    id          BIGSERIAL PRIMARY KEY NOT NULL ,
    name        VARCHAR(250) NOT NULL ,
    description VARCHAR(250) NOT NULL
);

ALTER TABLE invoice
    ADD COLUMN id_state_invoice BIGSERIAL NOT NULL;

ALTER TABLE invoice
    ADD COLUMN id_user BIGSERIAL NOT NULL;

ALTER TABLE invoice
    ADD COLUMN token BIGSERIAL NOT NULL;

ALTER TABLE invoice
    ADD CONSTRAINT fk_user_invoice FOREIGN KEY (id_user) REFERENCES users (id);

ALTER TABLE invoice
    ADD CONSTRAINT fk_state_invoice FOREIGN KEY (id_state_invoice) REFERENCES state_invoice (id);