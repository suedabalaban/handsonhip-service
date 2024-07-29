CREATE TABLE IF NOT EXISTS product (
    productID SERIAL PRIMARY KEY,
    productname VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price FLOAT NOT NULL,
    imageUrl VARCHAR(255)
);