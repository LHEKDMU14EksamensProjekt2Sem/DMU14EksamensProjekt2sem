package csv;

import java.util.List;

public class CSVFormatter<T> {
   private static final String
           SEPARATOR = ",",
           EOL = "\r\n";

   private CSVSerializer<T> serializer;
   private List<String> header;

   public CSVFormatter(CSVSerializer<T> serializer) {
      this.serializer = serializer;
   }

   public List<String> getHeader() {
      return header;
   }

   public void setHeader(List<String> header) {
      this.header = header;
   }

   public String format(List<T> items) {
      StringBuilder sb = new StringBuilder();

      // Add header if non-empty
      if (header != null && !header.isEmpty()) {
         sb.append(String.join(SEPARATOR, header));
         sb.append(EOL);
      }

      // Add rows
      for (T item : items) {
         sb.append(String.join(SEPARATOR, serializer.apply(item)));
         sb.append(EOL);
      }

      // Remove last EOL
      sb.delete(sb.length() - EOL.length(), sb.length());

      return sb.toString();
   }
}
