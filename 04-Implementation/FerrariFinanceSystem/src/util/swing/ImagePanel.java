package util.swing;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class ImagePanel extends JPanel {
   private Image img;
   private Point loc;

   public ImagePanel(Image image) {
      this(image, new Point(0, 0));
   }

   public ImagePanel(Image image, Point location) {
      img = image;
      loc = location;
   }

   /**
    * Paints the background image
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(img, loc.x, loc.y, this);
   }
}
