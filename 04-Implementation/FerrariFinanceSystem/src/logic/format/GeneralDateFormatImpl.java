package logic.format;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class GeneralDateFormatImpl implements GeneralDateFormat {
   private final DateTimeFormatter shortFormat, longFormat;

   public GeneralDateFormatImpl() {
      shortFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
      longFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
   }

   @Override
   public String formatShortDate(LocalDate date) {
      return date.format(shortFormat);
   }

   @Override
   public String formatLongDate(LocalDate date) {
      return date.format(longFormat);
   }
}
