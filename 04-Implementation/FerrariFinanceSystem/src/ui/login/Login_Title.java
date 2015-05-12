package ui.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login_Title extends JPanel {

  public Login_Title() {
    setBounds(0, 20, 1024, 100);
    setBackground( null );
    setOpaque(false);

    JLabel l_title = new JLabel("Ferrari Finance System");
    Font font = new Font("Ferro Rosso", Font.TRUETYPE_FONT, 64);
    l_title.setFont( font );
    l_title.setForeground( Color.BLACK);    
    add( l_title );
  }
}
