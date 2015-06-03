package logic.util;

import domain.PostalCode;
import util.io.FileIO;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.awt.RenderingHints.*;
import static java.awt.Transparency.*;

public class AssetsUtil {
   private static Image bgImage;

   private static ImageIcon loaderIcon, infoIcon, errorIcon;

   private static URL getURL(String path) {
      return ClassLoader.getSystemResource(path);
   }

   private static InputStream getStream(String path) {
      return ClassLoader.getSystemResourceAsStream(path);
   }

   public static List<String> loadSQLCreateStatements() throws IOException {
      // Table names to create in order.
      // Order matters due to FK constraints.
      String[] tables = new String[]{
              "cpr",
              "postal_code",
              "person",
              "employee_role",
              "employee",
              "user",
              "customer",
              "sale",
              "loan_request_status",
              "loan_request",
              "loan_offer",
              "loan_offer_payment",
              "car_model",
              "car_config",
              "car",
              "car_component_type",
              "car_component",
              "car_config_component"
      };

      // Ignore comment lines starting with "--";
      // will cause an error if included
      String linePattern = "\\s*(?!--).*";
      List<String> batch = new ArrayList<>();
      for (String t : tables) {
         String path = "assets/sql/" + t + ".sql";
         String sql = FileIO.read(getStream(path), linePattern);
         batch.add(sql);
      }

      return batch;
   }

   public static List<PostalCode> loadPostalCodes() throws IOException {
      String path = "assets/data/postal_codes.txt";
      List<PostalCode> postalCodes = new ArrayList<>();

      for (String line : FileIO.readLines(getStream(path))) {
         String[] parts = line.split(";");
         PostalCode pc = new PostalCode();
         pc.setPostalCode(Integer.parseInt(parts[0]));
         pc.setCity(parts[1]);
         postalCodes.add(pc);
      }

      return postalCodes;
   }

   public static ImageIcon loadLoaderIcon() throws IOException {
      if (loaderIcon == null)
         loaderIcon = new ImageIcon(getURL("assets/images/loader.gif"));

      return loaderIcon;
   }

   public static ImageIcon loadInfoIcon() throws IOException {
      if (infoIcon == null) {
         infoIcon = new ImageIcon(getURL("assets/images/info-icon.png"));
         infoIcon = translateIcon(infoIcon, 1);
      }

      return infoIcon;
   }

   public static ImageIcon loadErrorIcon() throws IOException {
      if (errorIcon == null) {
         errorIcon = new ImageIcon(getURL("assets/images/error-icon.png"));
         errorIcon = translateIcon(errorIcon, 1);
      }

      return errorIcon;
   }

   public static Image loadBackgroundImage() throws IOException {
      if (bgImage == null)
         bgImage = ImageIO.read(getStream("assets/images/bg3.png"));

      return bgImage;
   }

   private static ImageIcon translateIcon(ImageIcon icon, int topMargin) {
      BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight() + topMargin, TRANSLUCENT);
      Graphics2D g2 = bi.createGraphics();
      // Prefer quality
      RenderingHints hints = new RenderingHints(KEY_RENDERING, VALUE_RENDER_QUALITY);
      g2.addRenderingHints(hints);
      g2.drawImage(icon.getImage(), 0, topMargin, icon.getIconWidth(), icon.getIconHeight(), null);
      g2.dispose();
      return new ImageIcon(bi);
   }
}
