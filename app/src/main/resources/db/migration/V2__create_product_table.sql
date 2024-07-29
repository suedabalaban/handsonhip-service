CREATE TABLE IF NOT EXISTS product (
    productID SERIAL PRIMARY KEY,
    productname VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    imageUrl VARCHAR(255)
);