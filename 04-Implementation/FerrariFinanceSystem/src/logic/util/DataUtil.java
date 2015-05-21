package logic.util;

import data.access.EmployeeRoleAccessImpl;
import data.access.LoanRequestStatusAccessImpl;
import data.access.PostalCodeAccess;
import data.access.PostalCodeAccessImpl;
import domain.EmployeeRole;
import domain.LoanRequestStatus;
import util.jdbc.ConnectionHandler;
import util.jdbc.DDLDataAccess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;

public class DataUtil {
   private static final String
           DIR_PATH = "data",
           FILENAME = "ffs.db";

   private final ConnectionHandler con;

   public DataUtil(ConnectionHandler con) {
      this.con = con;
   }

   public static String getDatabasePath() {
      return DIR_PATH + "/" + FILENAME;
   }

   public static boolean databaseExists() {
      return Files.exists(
              Paths.get(getDatabasePath()));
   }

   public static void destroyDatabase() throws IOException {
      Files.deleteIfExists(
              Paths.get(getDatabasePath()));
   }

   /**
    * Creates a new database file and table structure
    *
    * @throws IOException
    * @throws SQLException
    */
   public void createDatabase() throws IOException, SQLException {
      // Make sure data dir exists
      Path dir = Paths.get(DIR_PATH);
      if (!Files.exists(dir))
         Files.createDirectories(dir);

      // Load and execute CREATE query
      // batch from SQL asset files
      new DDLDataAccess(con).executeBatch(
              AssetsUtil.loadSQLCreateStatements());
   }

   public void importPostalCodes() throws IOException, SQLException {
      PostalCodeAccess access = new PostalCodeAccessImpl(con);
      access.createPostalCodes(AssetsUtil.loadPostalCodes());
   }

   public void storeEmployeeRoles() throws SQLException {
      new EmployeeRoleAccessImpl(con).createEmployeeRoles(
              Arrays.asList(EmployeeRole.values()));
   }

   public void storeLoanRequestStatuses() throws SQLException {
      new LoanRequestStatusAccessImpl(con).createLoanRequestStatuses(
              Arrays.asList(LoanRequestStatus.values()));
   }
}
