package logic.session.main;

import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;

public interface MainController {
   GeneralNumberFormat getGeneralNumberFormat();

   GeneralDateFormat getGeneralDateFormat();
}
