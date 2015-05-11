package ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Background extends JPanel {
 
  protected Background(){
    ImageIcon image = new ImageIcon("pic/redBG2.jpg");
    JLabel l_bg = new JLabel( image );
//    bg.setBackground( Color.RED );
    setBounds(0, -5, 1024, 640);
    setOpaque( true );
    add( l_bg );
  }
}
