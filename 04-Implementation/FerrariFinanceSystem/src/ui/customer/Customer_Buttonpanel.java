package ui.customer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.Component;

public class Customer_Buttonpanel extends JPanel {

private static JButton btn_reset;
private static JButton btn_save;
  
  public Customer_Buttonpanel() {
    FlowLayout layout = new FlowLayout(FlowLayout.TRAILING);
    setLayout(layout);
    setPreferredSize( new Dimension(500 , 50));
    
    btn_reset = new JButton("Nulstil");
    btn_reset.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    } );
    add(btn_reset);
    
    btn_save = new JButton("Gem & Naeste");
    btn_save.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent e ) {
        // TODO Auto-generated method stub
        
      }
    } );
    add(btn_save);
  }
}
