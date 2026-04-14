CREATE TABLE portfolio (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    fund_id INT REFERENCES funds(id),
    units DECIMAL(18,8),
    invested_amount DECIMAL(18,2)
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    fund_id INT REFERENCES funds(id),
    type VARCHAR(10),
    units DECIMAL(18,8),
    amount DECIMAL(18,2),
    transaction_time TIMESTAMP,
    idempotency_key VARCHAR(100) UNIQUE
);