package presentation;

import java.awt.*;


import javax.swing.*;

public class StartFrame extends JFrame {
  private JFrame frame;
  
  public StartFrame(){
    
    //Creating the frame with BorderLayout etc.
    frame = new JFrame("Ferrari Finance System");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.setLocation( 120, 40 );   
    frame.setPreferredSize( new Dimension( 1024, 640 ) );
    
    centerpanel();    

    frame.pack();
    frame.setMinimumSize(frame.getSize());
    frame.setVisible(true);  
    
  }
  
  
  

  private JPanel bg = new JPanel();
  private JPanel title = new JPanel();
  private JLabel l_bg;
  private JPanel login = new JPanel();
    
  private void centerpanel(){
    // Set the JLayeredPane that the different panels will be added to/on.
    JLayeredPane centerpane = new JLayeredPane();
    centerpane.setBounds( 0, 0, 1024, 640 );
    
    // Adding the bg(Background) panel, and the label with the background image. 
    ImageIcon image = new ImageIcon("pic/bg2.jpg");
    l_bg = new JLabel(image);
    bg.setBackground( Color.RED );
    bg.setBounds(0, 0, 1024, 640);
    bg.setOpaque(true);
    l_bg.setSize( 1024, 640 );
    bg.add( l_bg );
    centerpane.add( bg, new Integer( 0 ) , 0 );    

    //Adding the title panel, with the .. yes, you guessed right: The Title! :D
    JLabel l_title = new JLabel("Ferrari Finance");
    Font font = new Font("Ferro Rosso", Font.TRUETYPE_FONT, 80);
    l_title.setFont( font );
    l_title.setForeground( Color.RED);    
    title.setBounds(200, 50, 630, 100);
    title.setBackground( null );
    title.setOpaque(false);
    title.add( l_title );
    centerpane.add( title, new Integer( 1 ), 0 );
    
    //Adding the login panel, with a GridBoxLayout to get the different components to be in the right places.
//    login.setBackground(Color.RED);
    login.setBounds(340, 440, 340, 120);
    login.setOpaque(true);
    JLabel l_username = new JLabel("Brugernavn: ");
    JLabel l_password = new JLabel("Adgangskode: ");
    JTextField txt_username = new JTextField(20);
    JPasswordField psw_password = new JPasswordField(20);
    JButton btn_login = new JButton("Login");
   
    login.setLayout(new GridBagLayout());
    login.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Login"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.ipady = 0;
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 0;
    login.add(l_username, c);
    
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    login.add(l_password, c);
    
    c.insets = new Insets(4,0,0,0);
    c.gridwidth = 3;
    c.gridx= 1;
    c.gridy= 0;
    login.add(txt_username, c);
    
    c.gridwidth = 3;
    c.gridx = 1;
    c.gridy= 1;
    login.add(psw_password, c);
    
    c.insets = new Insets(10,0,0,0);
    c.gridwidth = 1;
    c.gridx = 3;
    c.gridy= 3;
    c.anchor = GridBagConstraints.EAST;
    login.add(btn_login, c);
    centerpane.add( login, new Integer( 1 ), 0 ); 
    
    //Adding the panel to the center of the BorderLayout,
    frame.add( centerpane, BorderLayout.CENTER );
  } 
}
