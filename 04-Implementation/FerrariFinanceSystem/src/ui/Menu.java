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
    
    JButton btn_loanRequest = new JButton("Opret LaaneAnmodning");
    btn_loanRequest.setPreferredSize( dim1 );
    btn_loanRequest.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
       Centerpane.removeMenu();
       Centerpane.addKundePanel();
      }
    } );
    
    JButton btn_viewRequest = new JButton("Se LaaneAnmodninger");
    btn_viewRequest.setPreferredSize( dim1 );
    btn_viewRequest.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    } );
    
    JButton btn_loanOffer = new JButton("Opret og Godkend Laanetilbud");
    btn_loanOffer.setPreferredSize( dim1 );
    btn_loanOffer.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    } );

    JButton btn_viewLoanOffer = new JButton("Se LaaneTilbud");
    btn_viewLoanOffer.setPreferredSize( dim1 );
    btn_viewLoanOffer.addActionListener( new ActionListener() {
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
    add(btn_loanRequest, c);

    
    c.insets = new Insets(10,0,0,0);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    add(btn_viewRequest, c);

    c.insets = new Insets(0,10,0,0);
    c.gridwidth = 3;
    c.gridx= 1;
    c.gridy= 0;
    add(btn_loanOffer, c);
 
    c.insets = new Insets(10,10,0,0);
    c.gridwidth = 3;
    c.gridx = 1;
    c.gridy= 1;
    add(btn_viewLoanOffer, c);
  }
}
