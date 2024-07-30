CREATE TABLE IF NOT EXISTS marketing (
    marketingID SERIAL PRIMARY KEY,
    productID INTEGER NOT NULL,
    title VARCHAR(255),
    description TEXT,
    platforms VARCHAR(255),
    suggested_Price FLOAT, -- Changed to FLOAT for consistency
    CONSTRAINT fk_product FOREIGN KEY(productID) REFERENCES product(productID)
);