CREATE TABLE car_config_component (
  config_id INTEGER REFERENCES car_config(id) ON DELETE CASCADE,
  component_id INTEGER REFERENCES car_component(id) ON DELETE CASCADE,
  PRIMARY KEY (config_id, component_id)
) WITHOUT ROWID;
