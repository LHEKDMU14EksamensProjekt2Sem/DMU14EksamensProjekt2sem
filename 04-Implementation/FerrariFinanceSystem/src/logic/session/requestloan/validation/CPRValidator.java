package logic.session.requestloan.validation;

import exceptions.InvalidCPRException;

public interface CPRValidator {
   /**
    * Validates a CPR number. Format can be either NNNNNNNNNN or NNNNNN-NNNN.
    *
    * @param cpr a CPR number
    */
   String validateCPR(String cpr) throws InvalidCPRException;

   String getPartialCPRPattern();
}
