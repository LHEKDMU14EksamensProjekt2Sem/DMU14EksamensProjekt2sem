package ui;

import java.awt.Container;

import ui.customer.Customer_Panel;
import ui.login.*;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Centerpane extends JPanel {
  
    private static JLayeredPane layeredpane;
    private static JPanel p_login;
    private static JPanel p_title;
    private static JPanel p_bil;
    private static JPanel p_bg;
    private static JPanel p_menu;
    private static JPanel p_Kundepane;
    private static JPanel p_kundeinfo;

  public Centerpane() { 
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    layeredpane = new JLayeredPane();
    layeredpane.setBounds( 0, 0 , 1024, 640 );   
    
    p_bg = new Background();
    layeredpane.add( p_bg, new Integer( 1 ) , 0 );
    
    p_title = new Login_Title();
    layeredpane.add( p_title, new Integer( 1 ), 0 );   
    
    p_login = new Login_Login();
    layeredpane.add( p_login , new Integer( 2 ), 0 );
////    
    p_bil = new Login_car();
    layeredpane.add( p_bil , new Integer( 1 ), 0 );
    
//    p_menu = new Menu();
//    layeredpane.add( p_menu, new Integer( 1 ), 0  );
    
//    p_Kundepane = new Kunde_Panel();
//    layeredpane.add( p_Kundepane, new Integer( 1 ), 0 );
    
    add(layeredpane);
  }
  
  public static void removeLogin() {
    layeredpane.remove( p_bil );
    layeredpane.remove( p_login );
    MainFrame.getInstance().update();  
  }
  
  public static void addmenu() {
    p_menu = new Menu();
    layeredpane.add( p_menu, new Integer( 1 ), 0  );
    MainFrame.getInstance().update();
  }
  
  public static void removeMenu(){
    layeredpane.remove(p_menu);
    MainFrame.getInstance().update();
  }
  
  public static void addKundePanel(){
    p_Kundepane = new Customer_Panel();
    layeredpane.add( p_Kundepane, new Integer( 1 ), 0 );
    MainFrame.getInstance().update();
  }
}