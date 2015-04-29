package presentation;

import java.awt.BorderLayout;

import javax.swing.*;

public class Testframe extends JFrame {

  private static String Title = "SqLite Prototype v0.01";
  JFrame f = new JFrame("Login");

  
    
  public Testframe() {
    super(Title);
    f.setLocation(200,100);
    f.setSize(1000,800);
    f.setDefaultCloseOperation( EXIT_ON_CLOSE );

    
    f.setLayout(new BorderLayout());
    f.add(new Loginpanel(), BorderLayout.NORTH);
    f.add(new Sqlpanel(), BorderLayout.CENTER);
    f.setVisible(true);
    }


}
