package logic.session.requestloan;

import domain.Employee;
import domain.User;
import logic.format.GeneralNumberFormat;
import util.session.SessionFacade;

public interface RequestLoanFacade extends
        SessionFacade<RequestLoanViewToken>,
        CPRController,
        CustomerDetailsController,
        RequestDetailsController {
   GeneralNumberFormat getGeneralNumberFormat();

   User<Employee> getUser();
}
