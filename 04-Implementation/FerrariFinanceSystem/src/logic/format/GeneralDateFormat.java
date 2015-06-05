package logic.format;

import java.time.LocalDate;

public interface GeneralDateFormat {
   String formatShortDate(LocalDate date);

   String formatLongDate(LocalDate date);
}
