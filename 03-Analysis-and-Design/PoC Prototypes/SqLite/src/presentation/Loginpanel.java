package presentation;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Loginpanel extends JPanel{
  JButton b_login = new JButton("DB Test");
  
  
  public Loginpanel() {
    
    JPanel p_login = new JPanel();
    p_login.setLayout(new FlowLayout());

    p_login.add(b_login);
    
    
     
  }
}
