package logic.session.requestloan;

import domain.Employee;
import domain.User;
import util.session.SessionFacade;

public interface RequestLoanFacade extends
        SessionFacade<RequestLoanView>,
        CPRController,
        CustomerDetailsController,
        RequestDetailsController {
   User<Employee> getUser();
}
