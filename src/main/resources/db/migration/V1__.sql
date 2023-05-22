CREATE TABLE addition
(
    id    INT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)       NOT NULL,
    price DECIMAL(10, 2)     NOT NULL,
    file_path VARCHAR(255)   NOT NULL,
    CONSTRAINT pk_addition PRIMARY KEY (id)
);

CREATE TABLE milk
(
    id    INT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)       NOT NULL,
    price DECIMAL(10, 2)     NOT NULL,
    color VARCHAR(255)       NOT NULL,
    file_path VARCHAR(255)   NOT NULL,
    CONSTRAINT pk_milk PRIMARY KEY (id)
);
CREATE TABLE bubble_tea
(
    id          INT AUTO_INCREMENT NOT NULL,
    kind_id     INT                NOT NULL,
    size_id     INT                NOT NULL,
    milk_id     INT                NOT NULL,
    syrup_id    INT                NOT NULL,
    addition_id INT                NULL,
    CONSTRAINT pk_bubble_tea PRIMARY KEY (id)
);

CREATE TABLE kind
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NOT NULL,
    file_path VARCHAR(255)   NOT NULL,
    CONSTRAINT pk_kind PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id            INT AUTO_INCREMENT NOT NULL,
    number        INT                NOT NULL,
    price         DECIMAL(10, 2)     NOT NULL,
    bubble_tea_id INT                NULL,
    date          datetime           NOT NULL,
    status        ENUM('Nowe', 'W przygotowaniu', 'Gotowe do odbioru', 'Odebrane'),
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE size
(
    id    INT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)       NOT NULL,
    price DECIMAL(10, 2)     NOT NULL,
    file_path VARCHAR(255)   NOT NULL,
    CONSTRAINT pk_size PRIMARY KEY (id)
);

CREATE TABLE syrup
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NOT NULL,
    color VARCHAR(255)       NOT NULL,
    file_path VARCHAR(255)   NOT NULL,
    CONSTRAINT pk_syrup PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(45)        NOT NULL,
    password VARCHAR(100)        NOT NULL,
    `role`   VARCHAR(45)        NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_ADDITION FOREIGN KEY (addition_id) REFERENCES addition (id) ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_MILK FOREIGN KEY (milk_id) REFERENCES milk (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_KIND FOREIGN KEY (kind_id) REFERENCES kind (id) ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_SIZE FOREIGN KEY (size_id) REFERENCES size (id) ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_SYRUP FOREIGN KEY (syrup_id) REFERENCES syrup (id) ON DELETE CASCADE
        ON UPDATE CASCADE;

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_BUBBLE_TEA FOREIGN KEY (bubble_tea_id) REFERENCES bubble_tea (id) ON DELETE SET NULL
        ON UPDATE SET NULL;