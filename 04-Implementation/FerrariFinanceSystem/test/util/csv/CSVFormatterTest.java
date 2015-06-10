package util.csv;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CSVFormatterTest {
   static final String LINE_DELIM = "\r\n";

   CSVFormatter<Record> formatter;
   List<Record> records;

   @Before
   public void setUp() {
      formatter = new CSVFormatter<>();
      formatter.addField(new CSVField<>("Id", r -> String.valueOf(r.id)));
      formatter.addField(new CSVQuotedField<>("Name", r -> r.name));

      records = Arrays.asList(
              new Record(1, "Alice"),
              new Record(2, "Bob \"da Bass\" Johnson"));
   }

   @Test
   public void testHeader() {
      String expected = "Id,Name";
      String actual = formatter.format(records).split(LINE_DELIM)[0];
      assertEquals(expected, actual);
   }

   @Test
   public void testSimpleValues() {
      String expected = "1,\"Alice\"";
      String actual = formatter.format(records).split(LINE_DELIM)[1];
      assertEquals(expected, actual);
   }

   @Test
   public void testEscapedValues() {
      String expected = "2,\"Bob \"\"da Bass\"\" Johnson\"";
      String actual = formatter.format(records).split(LINE_DELIM)[2];
      assertEquals(expected, actual);
   }

   static class Record {
      int id;
      String name;

      Record(int id, String name) {
         this.id = id;
         this.name = name;
      }
   }
}
