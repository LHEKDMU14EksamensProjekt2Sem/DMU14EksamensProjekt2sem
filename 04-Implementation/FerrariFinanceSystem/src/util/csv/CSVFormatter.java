package util.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSV formatter for formatting a list of records
 * according to the RFC 4180 standard
 *
 * @param <T>
 */
public class CSVFormatter<T> {
   private static final String
           LINE_DELIM = "\r\n";

   private CSVSeparator separator;

   private List<CSVField<T>> fields;

   public CSVFormatter() {
      this(CSVSeparator.COMMA);
   }

   public CSVFormatter(CSVSeparator separator) {
      this.separator = separator;
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
         sb.append(String.join(separator.toString(), getHeader()));
         sb.append(LINE_DELIM);
      }

      // Add records
      for (T record : records) {
         List<String> values = fields.stream().map(field -> field.valueOf(record).toString())
                 .collect(Collectors.toList());

         sb.append(String.join(separator.toString(), values));
         sb.append(LINE_DELIM);
      }

      // Remove last line delim according to RFC 4180
      sb.delete(sb.length() - LINE_DELIM.length(), sb.length());
      return sb.toString();
   }

   private List<String> getHeader() {
      return fields.stream().map(CSVField::getHeader)
              .collect(Collectors.toList());
   }
}
