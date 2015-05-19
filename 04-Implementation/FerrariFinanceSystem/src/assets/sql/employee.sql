CREATE TABLE employee (
  id INTEGER NOT NULL PRIMARY KEY REFERENCES person(id),
  role_id INTEGER NOT NULL REFERENCES employee_role(id)
);
