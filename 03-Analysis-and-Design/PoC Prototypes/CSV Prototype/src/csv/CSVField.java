package csv;

import java.util.function.Function;

public class CSVField<T> {
   private final Function<T, String> translator;
   private String header;

   public CSVField(Function<T, String> translator) {
      this(null, translator);
   }

   public CSVField(String header, Function<T, String> translator) {
      this.header = header;
      this.translator = translator;
   }

   public String getHeader() {
      return header;
   }

   public void setHeader(String header) {
      this.header = header;
   }

   public String valueOf(T record) {
      return translator.apply(record);
   }
}
