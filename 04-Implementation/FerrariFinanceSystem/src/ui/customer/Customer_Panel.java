package ui.customer;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.Component;

  public class Customer_Panel extends JPanel {
  
    
    private static JPanel p_cpr;
    private static JPanel p_info;
    private static JPanel p_buttons;
    
    
    public Customer_Panel() {
      setBounds( 262, 100 , 500, 400 );
      setOpaque( true );
//      setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
      
      p_cpr = new Customer_CPRpanel();
      add(p_cpr);
      
      p_info = new Customer_Informationpanel();
      add(p_info);
      
      p_buttons = new Customer_Buttonpanel();
      add(p_buttons, BorderLayout.PAGE_END);
    }
}
