package ui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login_Logo2 {

  protected static JPanel Logo(){
    JPanel logo2 = new JPanel();
    ImageIcon image2 = new ImageIcon("pic/logo3.png");
    JLabel l_logo = new JLabel(image2);
    logo2.setBounds(10, 10, 154, 100);
    logo2.setBackground( null );
    logo2.setBorder( null );
    logo2.setOpaque(false);
    logo2.add(l_logo);
    return logo2;
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
