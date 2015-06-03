package logic.format;

import util.finance.Money;

import java.text.ParseException;

public interface GeneralNumberFormat {
   Money parseAmount(String amount) throws ParseException;

   double parsePercent(String percent) throws ParseException;

   int parseInteger(String integer) throws ParseException;

   String formatAmount(Money amount);

   String formatPercent(double percent);

   String formatInteger(int integer);
}
