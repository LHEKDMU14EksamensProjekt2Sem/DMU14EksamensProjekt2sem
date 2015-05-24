package ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {

  protected Menu(){
    setLayout(new GridBagLayout());
    setBounds(282, 240, 430, 180);
    setOpaque(true);
    
    Dimension dim1 = new Dimension( 200, 60 );
    
    JButton btn_låneAnmodning = new JButton("Opret LåneAnmodning");
    btn_låneAnmodning.setPreferredSize( dim1 );
    btn_låneAnmodning.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    } );
    
    JButton btn_seAnmodning = new JButton("Se LåneAnmodninger");
    btn_seAnmodning.setPreferredSize( dim1 );
    btn_seAnmodning.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    } );
    
    JButton btn_låneTildbud = new JButton("Opret og Godkend Lånetilbud");
    btn_låneTildbud.setPreferredSize( dim1 );
    btn_låneTildbud.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    } );

    JButton btn_seLåneTilbud = new JButton("Se LåneTilbud");
    btn_seLåneTilbud.setPreferredSize( dim1 );
    btn_seLåneTilbud.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    });
    
    

    setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Menu"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
    GridBagConstraints c = new GridBagConstraints();
   
    
    
    c.anchor = GridBagConstraints.WEST;
    
    c.ipady = 0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 0;
    add(btn_låneAnmodning, c);

    
    c.insets = new Insets(10,0,0,0);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    add(btn_seAnmodning, c);

    c.insets = new Insets(0,10,0,0);
    c.gridwidth = 3;
    c.gridx= 1;
    c.gridy= 0;
    add(btn_låneTildbud, c);
 
    c.insets = new Insets(10,10,0,0);
    c.gridwidth = 3;
    c.gridx = 1;
    c.gridy= 1;
    add(btn_seLåneTilbud, c);
  
  
  
  
  }
}
