package logic.session.main;

import logic.format.GeneralNumberFormat;
import logic.format.GeneralNumberFormatImpl;

public class MainControllerImpl implements MainController {
   private final MainFacade facade;
   private final GeneralNumberFormat numberFormat;

   public MainControllerImpl(MainFacade facade) {
      this.facade = facade;
      numberFormat = new GeneralNumberFormatImpl();
   }

   @Override
   public GeneralNumberFormat getGeneralNumberFormat() {
      return numberFormat;
   }
}
