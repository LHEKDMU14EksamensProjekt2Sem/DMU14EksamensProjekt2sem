package logic.util;

import domain.PostalCode;
import util.io.FileIO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AssetsUtil {
   private static InputStream get(String path) {
      return ClassLoader.getSystemResourceAsStream(path);
   }

   public static List<String> loadSQLCreateStatements() throws IOException {
      // Table names to create in order.
      // Order matters due to FK constraints.
      String[] tables = new String[]{
              "cpr",
              "postal_code",
              "person",
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

      // Ignore comment lines starting with "--";
      // will cause an error if included
      String linePattern = "\\s*(?!--).*";
      List<String> batch = new ArrayList<>();
      for (String t : tables) {
         String path = "assets/sql/" + t + ".sql";
         String sql = FileIO.read(get(path), linePattern);
         batch.add(sql);
      }

      return batch;
   }

   public static List<PostalCode> loadPostalCodes() throws IOException {
      String path = "assets/data/postal_codes.txt";
      List<PostalCode> postalCodes = new ArrayList<>();

      for (String line : FileIO.readLines(get(path))) {
         String[] parts = line.split(";");
         PostalCode pc = new PostalCode();
         pc.setPostalCode(Integer.parseInt(parts[0]));
         pc.setCity(parts[1]);
         postalCodes.add(pc);
      }

      return postalCodes;
   }
}
