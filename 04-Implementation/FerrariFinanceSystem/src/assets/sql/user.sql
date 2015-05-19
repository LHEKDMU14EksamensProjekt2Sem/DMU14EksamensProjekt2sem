CREATE TABLE user (
  username TEXT PRIMARY KEY,
  password TEXT NOT NULL,
  salt BLOB NOT NULL,
  employee_id INTEGER NOT NULL REFERENCES employee(id)
) WITHOUT ROWID;
