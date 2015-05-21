package data.access;

import domain.LoanRequestStatus;

import java.sql.SQLException;
import java.util.List;

public interface LoanRequestStatusAccess {
   void createLoanRequestStatuses(List<LoanRequestStatus> statuses) throws SQLException;
}
