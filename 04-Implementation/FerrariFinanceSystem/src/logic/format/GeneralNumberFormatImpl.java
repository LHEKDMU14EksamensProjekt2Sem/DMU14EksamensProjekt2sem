package logic.format;

import util.finance.Money;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class GeneralNumberFormatImpl implements GeneralNumberFormat {
   private NumberFormat moneyFormat, percentFormat, integerFormat;

   public GeneralNumberFormatImpl() {
      initMoneyFormat();
      initPercentFormat();
      initIntegerFormat();
   }

   private void initMoneyFormat() {
      moneyFormat = NumberFormat.getInstance();
      moneyFormat.setMinimumFractionDigits(2);
      moneyFormat.setMaximumFractionDigits(2);
      if (moneyFormat instanceof DecimalFormat)
         ((DecimalFormat) moneyFormat).setParseBigDecimal(true);
   }

   private void initPercentFormat() {
      percentFormat = NumberFormat.getInstance();
      percentFormat.setMinimumFractionDigits(3);
      percentFormat.setMaximumFractionDigits(3);
   }

   private void initIntegerFormat() {
      integerFormat = NumberFormat.getIntegerInstance();
   }

   @Override
   public Money parseAmount(String amount) throws ParseException {
      return new Money((BigDecimal) moneyFormat.parse(amount));
   }

   @Override
   public double parsePercent(String percent) throws ParseException {
      return (percentFormat.parse(percent).doubleValue() / 100);
   }

   @Override
   public int parseInteger(String integer) throws ParseException {
      return integerFormat.parse(integer).intValue();
   }

   @Override
   public String formatAmount(Money amount) {
      return moneyFormat.format(amount.asBigDecimal());
   }

   @Override
   public String formatPercent(double percent) {
      return percentFormat.format(percent * 100);
   }

   @Override
   public String formatInteger(int integer) {
      return integerFormat.format(integer);
   }
}
