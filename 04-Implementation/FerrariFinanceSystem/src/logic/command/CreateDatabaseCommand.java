package logic.command;

import data.DBPath;
import util.function.Command;
import util.io.FileIO;
import util.jdbc.ConnectionHandler;
import util.jdbc.DDLDataAccess;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateDatabaseCommand implements Command {
   private final ConnectionHandler con;

   public CreateDatabaseCommand(ConnectionHandler con) {
      this.con = con;
   }

   /**
    * Creates a new database file and table structure
    *
    * @throws IOException
    * @throws SQLException
    */
   @Override
   public void execute() throws IOException, SQLException {
      // Make sure data dir exists
      Path dir = Paths.get(DBPath.getDirectoryPath());
      if (!Files.exists(dir))
         Files.createDirectories(dir);

      // Table names to create in order.
      // Order matters due to FK constraints.
      String[] tables = new String[]{
              "postal_code",
              "person",
              "cpr",
              "employee_role",
              "employee",
              "user",
              "customer",
              "sale",
              "loan_request_status",
              "loan_request",
              "loan_offer",
              "loan_offer_repayment",
              "car_model",
              "car_config",
              "car",
              "car_component_type",
              "car_component",
              "car_config_component"
      };

      // Collect query batch from .sql files.
      // Ignore comment lines starting with "--";
      // will cause an error if included
      String linePattern = "(?!--).*";
      List<String> ddlBatch = new ArrayList<>();
      for (String t : tables) {
         String path = "assets/sql/" + t + ".sql";
         InputStream in = ClassLoader.getSystemResourceAsStream(path);
         String sql = FileIO.read(in, linePattern);
         ddlBatch.add(sql);
      }

      // Execute queries
      new DDLDataAccess(con).executeBatch(ddlBatch);
   }
}
