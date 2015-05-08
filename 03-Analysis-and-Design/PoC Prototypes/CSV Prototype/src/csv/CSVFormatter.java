package csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class CSVFormatter<T> {
   private static final String
           SEPARATOR = ",",
           EOL = "\r\n";

   private Function<T, String[]> serializer;
   private List<CSVField> fields;

   public CSVFormatter(Function<T, String[]> serializer) {
      this.serializer = serializer;
      this.fields = new ArrayList<>();
   }

   /**
    * Returns a string enclosed with double quotes. Any
    * double quote character within the string is escaped
    * by preceding it with another double quote.
    *
    * @param s string to enclose with double quotes
    * @return string enclosed with double quotes
    */
   private static String quote(String s) {
      return '"' + s.replace("\"", "\"\"") + '"';
   }

   public Iterator<CSVField> getFields() {
      return fields.iterator();
   }

   public void addField(CSVField field) {
      fields.add(field);
   }

   public String format(List<T> items) {
      StringBuilder sb = new StringBuilder();

      // Add header if fields are added and
      // the first field has a header
      if (!fields.isEmpty() && fields.get(0).getHeader() != null) {
         List<String> header = new ArrayList<>();
         for (CSVField field : fields)
            header.add(field.getHeader());

         sb.append(String.join(SEPARATOR, header));
         sb.append(EOL);
      }

      // Add records, quoting values if required
      for (T item : items) {
         String[] values = serializer.apply(item);
         for (int i = 0; i < values.length; i++) {
            if (i < fields.size() && fields.get(i).isQuoted())
               values[i] = quote(values[i]);
         }

         sb.append(String.join(SEPARATOR, values));
         sb.append(EOL);
      }

      // Remove last EOL
      sb.delete(sb.length() - EOL.length(), sb.length());

      return sb.toString();
   }
}
