CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    salt VARCHAR(255)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    price NUMERIC(10, 2)
);
