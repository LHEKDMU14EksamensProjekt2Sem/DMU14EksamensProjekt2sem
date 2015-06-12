package logic.session.viewloanrequests;

import domain.Employee;
import domain.User;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;
import util.session.SessionFacade;

public interface ViewLoanRequestsFacade extends
        SessionFacade<ViewLoanRequestsViewToken>,
        LoanRequestsController {
   User<Employee> getUser();

   GeneralNumberFormat getGeneralNumberFormat();

   GeneralDateFormat getGeneralDateFormat();
}
