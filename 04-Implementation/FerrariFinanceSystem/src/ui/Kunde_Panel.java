package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

  public class Kunde_Panel extends JPanel {
  
    
    private static JPanel p_cpr;
    private static JPanel p_info;
    private static JPanel p_buttons;
    
    
    public Kunde_Panel() {
      setBounds( 132, 100 , 800, 500 );
      setOpaque( false );
//      setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
      
      p_cpr = new Kunde_CPRpanel();
      add(p_cpr);
      
      p_info = new Kunde_Informationpanel();
      add(p_info);
      
      p_buttons = new Kunde_Buttonpanel();
      add(p_buttons);
    }
}
