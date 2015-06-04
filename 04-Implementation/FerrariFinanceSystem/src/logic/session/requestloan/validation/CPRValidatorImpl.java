package logic.session.requestloan.validation;

import exceptions.InvalidCPRException;

public class CPRValidatorImpl implements CPRValidator {
   private static final String
           CPR = "(0[1-9]|[1-2][0-9]|3[0-1])(0[1-9]|1[0-2])\\d{2}-?\\d{4}";

   @Override
   public String validateCPR(String cpr) throws InvalidCPRException {
      if (cpr.matches(CPR))
         return cpr.replace("-", "");
      else
         throw new InvalidCPRException(cpr);
   }
}
