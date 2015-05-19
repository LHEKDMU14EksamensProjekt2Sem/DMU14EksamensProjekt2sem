CREATE TABLE car_config (
  id INTEGER NOT NULL PRIMARY KEY,
  model_id INTEGER NOT NULL REFERENCES car_model(id),
  name TEXT NOT NULL,
  description TEXT NOT NULL
);
