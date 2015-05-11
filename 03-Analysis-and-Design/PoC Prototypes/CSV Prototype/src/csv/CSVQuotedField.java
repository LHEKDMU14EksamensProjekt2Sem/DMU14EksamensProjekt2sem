package csv;

import java.util.function.Function;

public class CSVQuotedField<T> extends CSVField<T> {
   public CSVQuotedField(Function<T, String> translator) {
      this(null, translator);
   }

   public CSVQuotedField(String header, Function<T, String> translator) {
      super(header, translator);
   }

   /**
    * Returns the string value of this field for the given
    * record. The string is enclosed with double quotes. Any
    * double quote character within the string is escaped
    * by preceding it with another double quote.
    *
    * @param record the record whose field to return
    * @return the value string enclosed with double quotes
    */
   @Override
   public String valueOf(T record) {
      return '"' + super.valueOf(record).replace("\"", "\"\"") + '"';
   }
}
