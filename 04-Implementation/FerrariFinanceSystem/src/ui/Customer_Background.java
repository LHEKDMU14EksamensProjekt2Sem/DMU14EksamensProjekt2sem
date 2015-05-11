package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Customer_Background {
 
  protected static JPanel Background(){
    JPanel bg = new JPanel();
    ImageIcon image = new ImageIcon("pic/bg3.jpg");
    JLabel l_bg = new JLabel(image);
    bg.setBackground( Color.RED );
    bg.setBounds(0, 0, 1024, 640);
    bg.setOpaque(true);
    l_bg.setSize( 1024, 640 );
    bg.add(l_bg);
    return bg;    
  }
}
