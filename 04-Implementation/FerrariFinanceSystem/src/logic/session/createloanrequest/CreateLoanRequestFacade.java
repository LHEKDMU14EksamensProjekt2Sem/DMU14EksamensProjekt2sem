package logic.session.createloanrequest;

import logic.format.GeneralNumberFormat;
import util.session.SessionFacade;

public interface CreateLoanRequestFacade extends
        SessionFacade<CreateLoanRequestViewToken>,
        CPRController,
        CustomerDetailsController,
        RequestDetailsController {
   GeneralNumberFormat getGeneralNumberFormat();
}
