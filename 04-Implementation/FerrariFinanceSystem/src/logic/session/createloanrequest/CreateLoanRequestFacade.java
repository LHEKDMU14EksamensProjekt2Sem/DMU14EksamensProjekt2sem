package logic.session.createloanrequest;

import domain.Employee;
import domain.User;
import logic.format.GeneralNumberFormat;
import util.session.SessionFacade;

public interface CreateLoanRequestFacade extends
        SessionFacade<CreateLoanRequestViewToken>,
        CPRController,
        CustomerDetailsController,
        RequestDetailsController {
   GeneralNumberFormat getGeneralNumberFormat();

   User<Employee> getUser();
}
