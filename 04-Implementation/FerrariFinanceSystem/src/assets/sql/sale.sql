CREATE TABLE sale (
  id INTEGER NOT NULL PRIMARY KEY,
  seller_id INTEGER NOT NULL REFERENCES employee(id),
  customer_id INTEGER NOT NULL REFERENCES customer(id),
  car_id INTEGER NOT NULL REFERENCES car(id),
  base_price NUMERIC NOT NULL,
  selling_price NUMERIC NOT NULL
);
