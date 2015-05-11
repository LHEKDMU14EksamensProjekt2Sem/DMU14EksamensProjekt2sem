package ui;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
  
  private static MainFrame instance;
  
  private MainFrame() {
    setTitle( "Ferrari Finance System" );
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    JComponent newContentPane = new Centerpane();
    newContentPane.setOpaque(true); 
    
    setContentPane(newContentPane);
    setLocation( 200, 100 );   
    setPreferredSize( new Dimension( 1024, 640 ) );
    
    ImageIcon LHEK = new ImageIcon("pic/LHEK.png");
    setIconImage(LHEK.getImage());
    
    setResizable( false );
    pack();
    setVisible(true);
  }
  
  
  public void update(){
    revalidate();
    repaint();
  }


  public static MainFrame getInstance() {
    if ( instance == null ) {
      instance = new MainFrame();
    } 
    
    return instance;
    
    
  }


 
  

  
    
  
}
