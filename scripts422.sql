-- scripts422.sql

-- Таблица "машина"
CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    price NUMERIC NOT NULL
);

-- Таблица "человек"
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT CHECK (age >= 0),
    has_license BOOLEAN NOT NULL,
    car_id INT,
    CONSTRAINT fk_person_car FOREIGN KEY (car_id) REFERENCES car(id)
);
