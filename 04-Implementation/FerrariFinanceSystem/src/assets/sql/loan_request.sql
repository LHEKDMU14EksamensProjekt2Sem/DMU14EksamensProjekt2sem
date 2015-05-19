CREATE TABLE loan_request (
  id INTEGER NOT NULL PRIMARY KEY REFERENCES sale(id),
  status_id INTEGER NOT NULL REFERENCES loan_request_status(id),
  status_by_employee_id INTEGER NOT NULL REFERENCES employee(id),
  date DATE NOT NULL,
  loan_amount DECIMAL(11,2) NOT NULL,
  pref_repayment DECIMAL(8,2),
  pref_term INTEGER
);
