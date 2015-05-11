package ui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login_car extends JPanel {
  


  protected Login_car(){

    File sourceimage = new File("pic/FerrariDino.jpg");
    BufferedImage image = null;
    try {
      image = ImageIO.read(sourceimage);
    }
    catch ( IOException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    BufferedImage resizedImage = resize( image , 500 , 312 );
    ImageIcon image2 = new ImageIcon(resizedImage);
    JLabel l_logo = new JLabel(image2);
    setBounds(262, 120, 500, 312);
    setBackground( null );
    setOpaque(false);
    add(l_logo);
    
  }
  
  public static BufferedImage resize(BufferedImage image, int width, int height) {
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = (Graphics2D) bi.createGraphics();
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(image, 0, 0, width, height, null);
    g2d.dispose();
    return bi;
}
  
}
