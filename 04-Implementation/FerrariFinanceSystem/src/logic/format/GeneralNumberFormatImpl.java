package logic.format;

import util.finance.Money;

import javax.swing.text.NumberFormatter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class GeneralNumberFormatImpl implements GeneralNumberFormat {
   private final NumberFormatter moneyFormat, percentFormat, integerFormat;

   public GeneralNumberFormatImpl() {
      DecimalFormat df;

      df = new DecimalFormat();
      df.setParseBigDecimal(true);
      df.setMinimumFractionDigits(2);
      df.setMaximumFractionDigits(2);
      moneyFormat = new NumberFormatter(df);
      moneyFormat.setValueClass(BigDecimal.class);

      df = new DecimalFormat();
      df.setMinimumFractionDigits(3);
      df.setMaximumFractionDigits(3);
      percentFormat = new NumberFormatter(df);
      percentFormat.setValueClass(Double.class);

      df = new DecimalFormat();
      df.setParseIntegerOnly(true);
      integerFormat = new NumberFormatter(df);
      integerFormat.setValueClass(Integer.class);
   }

   @Override
   public Money parseAmount(String amount) throws ParseException {
      return new Money((BigDecimal) moneyFormat.stringToValue(amount));
   }

   @Override
   public double parsePercent(String percent) throws ParseException {
      return ((double) percentFormat.stringToValue(percent) / 100);
   }

   @Override
   public int parseInteger(String integer) throws ParseException {
      return (int) integerFormat.stringToValue(integer);
   }

   @Override
   public String formatAmount(Money amount) {
      return valueToString(amount.asBigDecimal(), moneyFormat);
   }

   @Override
   public String formatPercent(double percent) {
      return valueToString(percent * 100, percentFormat);
   }

   @Override
   public String formatInteger(int integer) {
      return valueToString(integer, integerFormat);
   }

   private String valueToString(Object value, NumberFormatter format) {
      try {
         return format.valueToString(value);
      } catch (ParseException e) {
         // Should never happen
         e.printStackTrace();
         return null;
      }
   }
}
