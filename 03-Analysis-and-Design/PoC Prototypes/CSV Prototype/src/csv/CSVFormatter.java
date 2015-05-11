package csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVFormatter<T> {
   private static final String
           SEPARATOR = ",",
           EOL = "\r\n";

   private List<CSVField<T>> fields;

   public CSVFormatter() {
      this.fields = new ArrayList<>();
   }

   public Iterator<CSVField<T>> getFields() {
      return fields.iterator();
   }

   public void addField(CSVField<T> field) {
      fields.add(field);
   }

   public String format(List<T> records) {
      StringBuilder sb = new StringBuilder();

      // Add header if fields are added and
      // the first field has a header
      if (!fields.isEmpty() && fields.get(0).getHeader() != null) {
         sb.append(String.join(SEPARATOR, getHeader()));
         sb.append(EOL);
      }

      // Add records
      for (T record : records) {
         List<String> values = new ArrayList<>();
         for (CSVField<T> field : fields)
            values.add(field.valueOf(record));

         sb.append(String.join(SEPARATOR, values));
         sb.append(EOL);
      }

      // Remove last EOL
      sb.delete(sb.length() - EOL.length(), sb.length());
      return sb.toString();
   }

   private List<String> getHeader() {
      List<String> header = new ArrayList<>();
      for (CSVField field : fields)
         header.add(field.getHeader());
      return header;
   }
}
