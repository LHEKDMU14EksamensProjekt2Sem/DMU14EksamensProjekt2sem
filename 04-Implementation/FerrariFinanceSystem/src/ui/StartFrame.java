package ui;

import java.awt.*;

import javax.swing.*;

public class StartFrame extends JFrame {
  
  public static JFrame frame;
  
  public StartFrame() {
    frame =new JFrame("Ferrari Finance System");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    JComponent newContentPane = new Centerpane();
    newContentPane.setOpaque(true); 
    
    frame.setContentPane(newContentPane);
    frame.setLocation( 200, 100 );   
    frame.setPreferredSize( new Dimension( 1024, 640 ) );
    
    ImageIcon LHEK = new ImageIcon("pic/LHEK.png");
    frame.setIconImage(LHEK.getImage());
    
    frame.setResizable( false );
    frame.pack();
    frame.setVisible(true);
  }
  
  
  public static void update(){
    frame.revalidate();
    frame.repaint();
  }
}
