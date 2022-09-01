/* Base Tables */
/* =========== */
CREATE TABLE IF NOT EXISTS account(
    id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255), /* These two are nullible for admin accounts */
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone_no VARCHAR(15 /* Max Phone Number Length */),
    hashed_password VARCHAR(255) NOT NULL,
    type ENUM('R', 'D', 'C', 'S') NOT NULL, /* R: Restaurant Admin, D: Driver, C: Customer, S: Site Admin */
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS address(
    id INT AUTO_INCREMENT NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    zip_code INT NOT NULL,
    country VARCHAR(255) NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    house_number VARCHAR(5),
    unit_number VARCHAR(5),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS discount(
    id INT AUTO_INCREMENT NOT NULL,
    code VARCHAR(255) NOT NULL,
    percent DECIMAL NOT NULL,
    value DECIMAL NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS tags(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

/* Restaurant Tables */
/* ================= */
CREATE TABLE IF NOT EXISTS restaurant(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    address_id INT NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    website VARCHAR(255),
    open_time TIME NOT NULL,
    close_time Time NOT NULL,
    rating DECIMAL,
    rating_count INT,
    PRIMARY KEY(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS restaurant_tags(
    restaurant_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT pk_rtags PRIMARY KEY (restaurant_id, tag_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);

CREATE TABLE IF NOT EXISTS product(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    restaurant_id INT NOT NULL,
    price DECIMAL NOT NULL,
    description TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE IF NOT EXISTS product_tags(
    product_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT pk_ptags PRIMARY KEY (product_id, tag_id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);

/* Account Tables */
/* ============== */

CREATE TABLE IF NOT EXISTS restaurant_admin(
    account_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE IF NOT EXISTS driver(
    account_id INT NOT NULL,
    license_plate VARCHAR(255) NOT NULL,
    rating DECIMAL NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS customer(
    account_id INT NOT NULL,
    address_id INT NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS site_admin(
    account_id INT NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS payment_method(
    id INT AUTO_INCREMENT NOT NULL,
    customer_id INT NOT NULL,
    card_no VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(account_id)
);

/* Order Tables */
/* ============ */

CREATE TABLE IF NOT EXISTS orders(
    id INT AUTO_INCREMENT NOT NULL,
    driver_id INT NOT NULL,
    customer_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    discount_id INT,
    status INT NOT NULL,
    total_price DECIMAL NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (driver_id) REFERENCES driver(account_id),
    FOREIGN KEY (customer_id) REFERENCES customer(account_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (discount_id) REFERENCES discount(id)
);

CREATE TABLE IF NOT EXISTS order_item(
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL NOT NULL,
    additional_instructions TEXT,
    driver_rating INT,
    food_rating INT,
    CONSTRAINT pk_order_item PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_Id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS transaction(
    id INT AUTO_INCREMENT NOT NULL,
    order_id INT NOT NULL,
    payment_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (payment_id) REFERENCES payment_method(id)
);