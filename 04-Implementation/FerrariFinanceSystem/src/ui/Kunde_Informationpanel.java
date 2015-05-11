package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Kunde_Informationpanel extends JPanel {
  
  private static JLabel l_note;
  private static JTextArea txta_note;
  private static JLabel l_name;
  private static JTextField txt_firstname;
  private static JTextField txt_lastname;
  private static JLabel l_adress;
  private static JTextField txt_adress;
  private static JLabel l_postalcode;
  private static JTextField txt_postalcode;
  private static JTextField txt_city;
  private static JLabel l_phone;
  private static JTextField txt_phone;
  private static JLabel l_email;
  private static JTextField txt_email;
  
  protected Kunde_Informationpanel() {


    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Kunde Information"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
    GridBagConstraints c = new GridBagConstraints();
    
    l_note = new JLabel("Bemaerkninger: ");
    txta_note = new JTextArea( 2, 30);
    l_name = new JLabel("Navn: ");
    txt_firstname = new JTextField(10);
    txt_lastname = new JTextField(19);
    l_adress = new JLabel("Adresse: ");
    txt_adress = new JTextField(30);
    l_postalcode = new JLabel("Postnummer & By: ");
    txt_postalcode = new JTextField(10);
    txt_city = new JTextField(19);
    l_phone = new JLabel("Telefon: ");
    txt_phone = new JTextField(30);
    l_email = new JLabel("Email: ");
    txt_email = new JTextField(30);
    
    
    

    c.anchor = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 0;
    add(l_note, c);
    
    c.insets = new Insets(5,5,0,0); 
    c.gridwidth = 5;
    c.gridx = 1;
    c.gridy = 0;
    add(txta_note, c);
    
    c.gridwidth = 1;
    c.gridx= 0;
    c.gridy= 1;
    add(l_name, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 1;
    c.gridx = 1;
    c.gridy = 1;
    add(txt_firstname, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 1;
    add(txt_lastname, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 2;
    add(l_adress, c);
    
    c.gridwidth = 2;
    c.gridx = 1;
    c.gridy = 2;
    add(txt_adress, c);
    
    c.gridwidth = 1;
    c.gridx= 0;
    c.gridy= 3;
    add(l_postalcode, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 1;
    c.gridx = 1;
    c.gridy = 3;
    add(txt_postalcode, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 3;
    add(txt_city, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 4;
    add(l_phone, c);
    
    c.gridwidth = 2;
    c.gridx = 1;
    c.gridy = 4;
    add(txt_phone, c);
    
    c.insets = new Insets(5,5,0,0);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 5;
    add(l_email, c);
    
    c.gridwidth = 2;
    c.gridx = 1;
    c.gridy = 5;
    add(txt_email, c);
  }

}
