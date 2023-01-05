CREATE TABLE addition
(
    id    INT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255) NULL,
    price DECIMAL NULL,
    CONSTRAINT pk_addition PRIMARY KEY (id)
);

CREATE TABLE base
(
    id    INT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255) NULL,
    price DECIMAL NULL,
    CONSTRAINT pk_base PRIMARY KEY (id)
);

CREATE TABLE bubble_tea
(
    id          INT AUTO_INCREMENT NOT NULL,
    kind_id     INT NULL,
    size_id     INT NULL,
    base_id     INT NULL,
    syrup_id    INT NULL,
    addition_id INT NULL,
    CONSTRAINT pk_bubble_tea PRIMARY KEY (id)
);

CREATE TABLE kind
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_kind PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id            INT AUTO_INCREMENT NOT NULL,
    number        INT NOT NULL,
    price         DECIMAL NULL,
    bubble_tea_id INT NULL,
    date          datetime NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE size
(
    id    INT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255) NOT NULL,
    price DECIMAL      NOT NULL,
    CONSTRAINT pk_size PRIMARY KEY (id)
);

CREATE TABLE syrup
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_syrup PRIMARY KEY (id)
);

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_ADDITION FOREIGN KEY (addition_id) REFERENCES addition (id);

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_BASE FOREIGN KEY (base_id) REFERENCES base (id);

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_KIND FOREIGN KEY (kind_id) REFERENCES kind (id);

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_SIZE FOREIGN KEY (size_id) REFERENCES size (id);

ALTER TABLE bubble_tea
    ADD CONSTRAINT FK_BUBBLE_TEA_ON_SYRUP FOREIGN KEY (syrup_id) REFERENCES syrup (id);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_BUBBLE_TEA FOREIGN KEY (bubble_tea_id) REFERENCES bubble_tea (id);