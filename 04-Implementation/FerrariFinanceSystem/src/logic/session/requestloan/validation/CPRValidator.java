package logic.session.requestloan.validation;

public interface CPRValidator {
   /**
    * Validates a CPR number. Format can be either NNNNNNNNNN or NNNNNN-NNNN.
    *
    * @param cpr a CPR number
    * @return <code>true</code> if <code>cpr</code> is a valid CPR number; <code>false</code> otherwise
    */
   boolean validateCPR(String cpr);
}
