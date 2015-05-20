package logic.command;

import data.access.EmployeeRoleAccess;
import data.access.LoanRequestStatusAccess;
import data.access.PostalCodeAccess;
import domain.PostalCode;
import domain.EmployeeRole;
import domain.LoanRequestStatus;
import util.function.Command;
import util.io.FileIO;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreInitialDataCommand implements Command {
   private final ConnectionHandler con;

   public StoreInitialDataCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void execute() throws IOException, SQLException {
      storePostalCodes(loadPostalCodes(), con);
      storeEmployeeRoleValues(con);
      storeLoanRequestStatusValues(con);
   }

   private List<PostalCode> loadPostalCodes() throws IOException {
      String path = "assets/data/postal_codes.txt";
      InputStream in = ClassLoader.getSystemResourceAsStream(path);
      List<PostalCode> postalCodes = new ArrayList<>();

      for (String line : FileIO.readLines(in)) {
         String[] parts = line.split(";");
         PostalCode pc = new PostalCode();
         pc.setPostalCode(Integer.parseInt(parts[0]));
         pc.setCity(parts[1]);
         postalCodes.add(pc);
      }

      return postalCodes;
   }

   private void storePostalCodes(List<PostalCode> postalCodes, ConnectionHandler con) throws SQLException {
      PostalCodeAccess access = new PostalCodeAccess(con);
      for (PostalCode pc : postalCodes) {
         access.createPostalCode(pc);
      }
   }

   private void storeEmployeeRoleValues(ConnectionHandler con) throws SQLException {
      EmployeeRoleAccess access = new EmployeeRoleAccess(con);
      for (EmployeeRole role : EmployeeRole.values()) {
         access.createEmployeeRole(role);
      }
   }

   private void storeLoanRequestStatusValues(ConnectionHandler con) throws SQLException {
      LoanRequestStatusAccess access = new LoanRequestStatusAccess(con);
      for (LoanRequestStatus status : LoanRequestStatus.values()) {
         access.createLoanRequestStatus(status.toString());
      }
   }
}
