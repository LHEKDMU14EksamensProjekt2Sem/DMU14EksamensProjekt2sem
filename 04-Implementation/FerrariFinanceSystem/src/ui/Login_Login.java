package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_Login extends JPanel{


  protected Login_Login(){
    setBounds(342, 450, 340, 120);
    JLabel l_username = new JLabel("Brugernavn: ");
    JLabel l_password = new JLabel("Adgangskode: ");
    JTextField txt_username = new JTextField(20);
    JPasswordField psw_password = new JPasswordField(20);
    JButton btn_login = new JButton("Login");
    btn_login.addActionListener( new ActionListener() {
      
      @Override
      public void actionPerformed( ActionEvent e ) {
        Centerpane.removeLogin();
      }
    } );
    
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Login"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.ipady = 0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 0;
    add(l_username, c);
    
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    add(l_password, c);
    
    c.insets = new Insets(4,0,0,0);
    c.gridwidth = 3;
    c.gridx= 1;
    c.gridy= 0;
    add(txt_username, c);
    
    c.gridwidth = 3;
    c.gridx = 1;
    c.gridy= 1;
    add(psw_password, c);
    
    c.insets = new Insets(10,0,0,0);
    c.gridwidth = 1;
    c.gridx = 3;
    c.gridy= 3;
    c.anchor = GridBagConstraints.EAST;
    add(btn_login, c);
    
  }
}
