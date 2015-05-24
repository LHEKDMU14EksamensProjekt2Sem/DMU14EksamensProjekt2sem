package data.access;

import domain.EmployeeRole;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRoleAccess {
   void createEmployeeRoles(List<EmployeeRole> roles) throws SQLException;
}
