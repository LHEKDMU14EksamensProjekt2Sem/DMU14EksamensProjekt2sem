import csv.CSVField;
import csv.CSVFormatter;
import csv.CSVQuotedField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
   public static void main(String[] args) {
      CSVFormatter<Record> formatter = new CSVFormatter<>();

      formatter.addField(
              new CSVQuotedField<>("First name", Record::getFirstName));
      formatter.addField(
              new CSVQuotedField<>("Last name", Record::getLastName));
      formatter.addField(
              new CSVField<>("Year of birth", r -> String.valueOf(r.getYearOfBirth())));
      formatter.addField(
              new CSVField<>("Phone", r -> String.valueOf(r.getPhone())));
      formatter.addField(
              new CSVField<>("Email", Record::getEmail));
      formatter.addField(
              new CSVQuotedField<>("Note", Record::getNote));

      String csvData = formatter.format(getRecords());
      // Print CSV data
      System.out.println(csvData);

      // Write CSV data to file
      String filename = "export.csv";
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
         writer.write(csvData);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static List<Record> getRecords() {
      return Arrays.asList(
              new Record("Alpha", "Beta", 1712, 99887766, "alpha@mail.com", ""),
              new Record("John \"The Beast\"", "Doe", 1993, 12345678, "jdoe@outlook.com", "Lorem ipsum"),
              new Record("Peter", "Pan", 1972, 44000111, "pp@disney.com", "Testing \"double quotes\""),
              new Record("Cup of", "Java", 2014, 10252401, "java@oracle.com", ""),
              new Record("J.", "Titan", 1514, 23146778, "jtitan@yahoo.com", ""));
   }
}
