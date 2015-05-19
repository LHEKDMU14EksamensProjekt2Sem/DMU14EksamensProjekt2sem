CREATE TABLE car_component (
  id INTEGER NOT NULL PRIMARY KEY,
  type_id INTEGER NOT NULL REFERENCES car_component_type(id),
  name TEXT NOT NULL,
  description TEXT NOT NULL,
  base_price DECIMAL(8,2) NOT NULL
);
