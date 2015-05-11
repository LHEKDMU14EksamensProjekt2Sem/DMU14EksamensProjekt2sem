package ui.Login;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login_Logo {

  public static JPanel Logo(){
    JPanel logo = new JPanel();
    ImageIcon image2 = new ImageIcon("pic/logo3.png");
    JLabel l_logo = new JLabel(image2);
    logo.setBounds(10, 10, 154, 100);
    logo.setBackground( null );
    logo.setBorder( null );
    logo.setOpaque(false);
    logo.add(l_logo);
    return logo;
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
