-- No primary key required
CREATE TABLE loan_offer_repayment (
  offer_id INTEGER NOT NULL REFERENCES loan_offer(id),
  date DATE NOT NULL,
  amount NUMERIC NOT NULL
);
