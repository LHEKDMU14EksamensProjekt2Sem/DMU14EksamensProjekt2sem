package data;

public class DBPath {
   private static final String
           DIR_PATH = "data",
           FILENAME = "ffs.db";

   public static String getPath() {
      return DIR_PATH + "/" + FILENAME;
   }

   public static String getDirectoryPath() {
      return DIR_PATH;
   }
}
