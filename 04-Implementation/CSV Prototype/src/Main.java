import csv.CSVField;
import csv.CSVFormatter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
      CSVFormatter<Record> formatter = new CSVFormatter<>(r ->
              new String[]{
                      r.getFirstName(),
                      r.getLastName(),
                      String.valueOf(r.getYearOfBirth()),
                      String.valueOf(r.getPhone()),
                      r.getEmail(),
                      r.getNote()
              });

      formatter.addField(new CSVField("First name"));
      formatter.addField(new CSVField("Last name"));
      formatter.addField(new CSVField("Year of birth"));
      formatter.addField(new CSVField("Phone"));
      formatter.addField(new CSVField("Email"));
      formatter.addField(new CSVField("Note", true));

      List<Record> records = new ArrayList<>();
      records.add(new Record("Alpha", "Beta", 1712, 99887766, "alpha@mail.com", ""));
      records.add(new Record("John", "Doe", 1993, 12345678, "jdoe@outlook.com", "Lorem ipsum"));
      records.add(new Record("Peter", "Pan", 1972, 44000111, "pp@disney.com", "Testing \"double quotes\""));
      records.add(new Record("Cup of", "Java", 2014, 10252401, "java@oracle.com", ""));
      records.add(new Record("J.", "Titan", 1514, 23146778, "jtitan@yahoo.com", ""));

      System.out.println(formatter.format(records));

      // Write to file
      String filename = "export.csv";
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
         writer.write(formatter.format(records));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
