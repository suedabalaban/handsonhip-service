CREATE TABLE IF NOT EXISTS marketing (
    marketingID SERIAL PRIMARY KEY,
    productID INTEGER NOT NULL,
    title VARCHAR(255),
    description TEXT,
    platforms VARCHAR(255),
    suggestedPrice NUMERIC(10, 2),
    CONSTRAINT fk_product FOREIGN KEY(productID) REFERENCES product(productID)
);