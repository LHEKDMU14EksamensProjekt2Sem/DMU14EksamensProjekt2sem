package util.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.*;
import static java.awt.image.BufferedImage.*;

public class Images {
   public static Image resize(Image image, int width, int height) {
      BufferedImage bi = new BufferedImage(width, height, TRANSLUCENT);
      Graphics2D g2 = bi.createGraphics();
      // Prefer quality
      RenderingHints hints = new RenderingHints(KEY_RENDERING, VALUE_RENDER_QUALITY);
      g2.addRenderingHints(hints);
      g2.drawImage(image, 0, 0, width, height, null);
      g2.dispose();
      return bi;
   }
}
