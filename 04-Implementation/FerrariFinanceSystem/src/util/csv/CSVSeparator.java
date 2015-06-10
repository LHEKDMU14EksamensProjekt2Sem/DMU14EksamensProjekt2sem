package util.csv;

public enum CSVSeparator {
   COMMA(','),
   SEMICOLON(';');

   private char value;

   CSVSeparator(char value) {
      this.value = value;
   }

   @Override
   public String toString() {
      return String.valueOf(value);
   }
}
