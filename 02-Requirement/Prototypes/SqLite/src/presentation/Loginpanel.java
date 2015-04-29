package presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Loginpanel extends JPanel{
  JLabel l_username = new JLabel("Brugernavn:");
  JLabel l_password = new JLabel("Adgangskode:");
  JTextField t_username = new JTextField(15);
  JTextField t_password = new JTextField(15);
  JButton b_login = new JButton("Login");
  
  
  public Loginpanel() {
    
    JPanel p_login = new JPanel();
    p_login.add(l_username);
    p_login.add(t_username);
    p_login.add(l_password);
    p_login.add(t_password);
    p_login.add(b_login);
     
  }
}
