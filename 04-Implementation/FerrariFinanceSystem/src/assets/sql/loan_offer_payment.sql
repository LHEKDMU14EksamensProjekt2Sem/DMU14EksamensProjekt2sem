-- No primary key required (uses SQLite rowid)
CREATE TABLE loan_offer_payment (
  offer_id INTEGER NOT NULL REFERENCES loan_offer(id),
  date DATE NOT NULL,
  balance NUMERIC NOT NULL,
  amount NUMERIC NOT NULL,
  repayment NUMERIC NOT NULL,
  interest NUMERIC NOT NULL
);
