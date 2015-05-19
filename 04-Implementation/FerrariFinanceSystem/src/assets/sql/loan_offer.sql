CREATE TABLE loan_offer (
  id INTEGER NOT NULL PRIMARY KEY REFERENCES loan_request(id),
  date DATE NOT NULL,
  principal DECIMAL(11,2) NOT NULL,
  interest_rate DOUBLE NOT NULL
);
