CREATE TABLE IF NOT EXISTS user_type
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100),
    description VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS store
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);


CREATE TABLE IF NOT EXISTS cashier
(
    id       BIGSERIAL PRIMARY KEY,
    id_store BIGSERIAL NOT NULL, --CLEAR
    name     VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS invoice
(
    id         BIGSERIAL PRIMARY KEY,
    date       TIMESTAMP    NOT NULL,
    store      VARCHAR(250) NOT NULL,
    id_cashier BIGSERIAL    NOT NULL,-- CLEAR
    total      INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(250),
    description VARCHAR(250),
    id_category BIGSERIAL --clear
);

CREATE TABLE IF NOT EXISTS detail_invoice
(
    id         BIGSERIAL PRIMARY KEY,
    id_invoice BIGSERIAL, --CLEAR
    id_product BIGSERIAL, --CLEAR
    count      INTEGER,
    UNIQUE (id_product, id_invoice)
);
CREATE TABLE IF NOT EXISTS category_product
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(250)  NOT NULL,
    description VARCHAR(1000) NOT NULL
);
CREATE TABLE stock
(
    id    BIGSERIAL PRIMARY KEY, --CLEAR
    count INTEGER NOT NULL
);
CREATE TABLE price
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGSERIAL UNIQUE REFERENCES product (id), --CLEAR
    price      INTEGER NOT NULL
);


INSERT INTO user_type (name, description)
VALUES ('normal', 'normal user');

ALTER TABLE users
    ADD COLUMN IF NOT EXISTS id_user_type BIGSERIAL NOT NULL;

UPDATE users
SET id_user_type = 1
WHERE id_user_type != 1;

ALTER TABLE users
    ADD CONSTRAINT fk_user_type_user FOREIGN KEY (id_user_type) REFERENCES user_type (id);

ALTER TABLE cashier
    ADD CONSTRAINT fk_store_cashier FOREIGN KEY (id_store) REFERENCES store (id);

ALTER TABLE detail_invoice
    ADD CONSTRAINT fk_invoice_detail_invoce FOREIGN KEY (id_invoice) REFERENCES invoice (id);

ALTER TABLE detail_invoice
    ADD CONSTRAINT fk_product_detail_invoice FOREIGN KEY (id_product) REFERENCES product (id);

ALTER TABLE product
    ADD CONSTRAINT fk_category_product_products FOREIGN KEY (id_category) REFERENCES category_product (id);

ALTER TABLE stock
    ADD CONSTRAINT fk_stock_product FOREIGN KEY (id) REFERENCES product (id);

ALTER TABLE price
    ADD CONSTRAINT fk_price_product FOREIGN KEY (id) REFERENCES product (id);