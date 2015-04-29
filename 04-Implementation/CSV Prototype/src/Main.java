import csv.CSVFormatter;
import csv.CSVSerializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
      CSVSerializer<Record> serializer = item -> {
         List<String> fields = new LinkedList<>();
         fields.add(item.getFirstName());
         fields.add(item.getLastName());
         fields.add(String.valueOf(item.getYearOfBirth()));
         fields.add(String.valueOf(item.getPhone()));
         fields.add(item.getEmail());
         return fields;
      };

      CSVFormatter<Record> formatter = new CSVFormatter<>(serializer);

      List<String> header = new LinkedList<>();
      header.add("First name");
      header.add("Last name");
      header.add("Year of birth");
      header.add("Phone");
      header.add("Email");

      List<Record> records = new LinkedList<>();
      records.add(new Record("Alpha", "Beta", 1712, 99887766, "alpha@mail.com"));
      records.add(new Record("John", "Doe", 1993, 12345678, "jdoe@outlook.com"));
      records.add(new Record("Peter", "Pan", 1972, 44000111, "pp@disney.com"));
      records.add(new Record("Cup of", "Java", 2014, 10252401, "java@oracle.com"));
      records.add(new Record("J.", "Titan", 1514, 23146778, "jtitan@yahoo.com"));

      formatter.setHeader(header);
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
