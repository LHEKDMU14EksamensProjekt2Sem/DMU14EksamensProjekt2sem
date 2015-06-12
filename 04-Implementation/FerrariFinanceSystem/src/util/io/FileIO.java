package util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
   public static String read(InputStream in) throws IOException {
      try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
         StringBuilder sb = new StringBuilder();
         while (r.ready())
            sb.append(r.read());
         return sb.toString();
      }
   }

   public static String read(InputStream in, String linePattern) throws IOException {
      return String.join(System.lineSeparator(), readLines(in, linePattern));
   }

   public static List<String> readLines(InputStream in) throws IOException {
      try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
         List<String> lines = new ArrayList<>();
         while (r.ready()) {
            lines.add(r.readLine());
         }
         return lines;
      }
   }

   public static List<String> readLines(InputStream in, String linePattern) throws IOException {
      try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
         List<String> lines = new ArrayList<>();
         while (r.ready()) {
            String l = r.readLine();
            if (l.matches(linePattern))
               lines.add(l);
         }
         return lines;
      }
   }

   public static String readFile(String path) throws IOException {
      return read(new FileInputStream(path));
   }

   public static String readFile(String path, String linePattern) throws IOException {
      return read(new FileInputStream(path), linePattern);
   }

   public static void writeFile(File file, String data) throws IOException {
      try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
         w.write(data);
      }
   }

   public static void writeFile(String path, String data) throws IOException {
      writeFile(new File(path), data);
   }

   public static void writeFile(String path, List<String> lines, CharSequence delim) throws IOException {
      writeFile(path, String.join(delim, lines));
   }

   public static void writeFile(String path, List<String> lines) throws IOException {
      writeFile(path, lines, System.lineSeparator());
   }
}
