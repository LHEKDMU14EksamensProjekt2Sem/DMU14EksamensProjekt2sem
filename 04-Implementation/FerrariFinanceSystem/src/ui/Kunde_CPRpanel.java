package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class Kunde_CPRpanel extends JPanel{

  private static JLabel l_cpr;
  private static JTextField txt_cpr;
  private static JLabel l_credit1;
  private static JLabel l_credit2;
  
  protected Kunde_CPRpanel() {
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("CPR: "),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
    GridBagConstraints c = new GridBagConstraints();
    
    l_cpr = new JLabel("CPR nummer: ");
    txt_cpr = new JTextField(31);  
    l_credit1 = new JLabel("Kredit Vaerdighed: ");
    l_credit2 = new JLabel("D");
    l_credit2.setForeground( Color.RED );
    l_credit2.setBorder( BorderFactory.createLineBorder(Color.BLACK, 1 ));
    
    c.anchor = GridBagConstraints.WEST;
    c.ipady = 0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 0;
    add(l_cpr, c);
    
    c.insets = new Insets(5,0,0,0);
    c.ipady = 0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    add(l_credit1, c);
    
    c.insets = new Insets(0,5,0,0);
    c.gridwidth = 3;
    c.gridx= 1;
    c.gridy= 0;
    add(txt_cpr, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 3;
    c.gridx = 1;
    c.gridy= 1;
    add(l_credit2, c);
   
   
  }
}
