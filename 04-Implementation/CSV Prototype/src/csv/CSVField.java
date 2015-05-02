package csv;

public class CSVField {
   private String header;
   private boolean quoted;

   public CSVField() {
      this(null, false);
   }

   public CSVField(String header) {
      this(header, false);
   }

   public CSVField(boolean quoted) {
      this(null, quoted);
   }

   public CSVField(String header, boolean quoted) {
      this.header = header;
      this.quoted = quoted;
   }

   public String getHeader() {
      return header;
   }

   public void setHeader(String header) {
      this.header = header;
   }

   public boolean isQuoted() {
      return quoted;
   }

   public void setQuoted(boolean quoted) {
      this.quoted = quoted;
   }
}
