CREATE TABLE person (
  id INTEGER NOT NULL PRIMARY KEY REFERENCES cpr(id),
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  street TEXT NOT NULL,
  postal_code INTEGER NOT NULL REFERENCES postal_code(postal_code),
  phone INTEGER NOT NULL,
  email TEXT NOT NULL
);
