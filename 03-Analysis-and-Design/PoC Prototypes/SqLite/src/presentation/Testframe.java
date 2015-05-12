package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Testframe extends JFrame {
  JFrame f = new JFrame("FF: Login");
  
  public Testframe() throws HeadlessException {
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    Loginpanel();
    Sqlpanel();
    Helppanel();
    f.pack();
    f.setMinimumSize(f.getSize());
    f.setVisible(true);
    }
  
    JButton b_login = new JButton("Test DB access");
    
       
    public void Loginpanel() {
      
      JPanel p_login = new JPanel();
      p_login.setLayout(new FlowLayout());
      p_login.add(b_login);
      
      b_login.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) {
          Sqltest();
        }
      });
      
      f.add(p_login, BorderLayout.NORTH);   
    }
    
    public void Helppanel(){
      JPanel p_help = new JPanel();
      p_help.setLayout(new FlowLayout());
      JLabel l_help = new JLabel("Tryk på knappen for at gøre nedslag i databasen, og få udskrevet all data.");
      p_help.add(l_help);
      f.add(p_help, BorderLayout.SOUTH);
    }
    
    
    
    
    JTextArea printArea = new JTextArea(20, 40);
    public void Sqlpanel(){
     
      printArea.setName("printArea");
      printArea.setEditable(false);
      f.add(printArea, BorderLayout.CENTER);
    }
    
    
    
    public void Sqltest() {
      
       Connection con = null;
       Statement st = null;
       ResultSet rs = null;

         try {
           con = DriverManager.getConnection("jdbc:sqlite:sqlitePrototype.db");
           con.setAutoCommit(false);

           st = con.createStatement();
           rs = st.executeQuery( "SELECT * FROM SALGER;" );
           
           while ( rs.next() ) {
             
              int ID = rs.getInt("ID");
              String  Name = rs.getString("Name");
              String Password  = rs.getString("Password");
              int Niv = rs.getInt("Niv");
              
              printPaaKonsol( "ID = " + ID );
              printPaaKonsol( "NAME = " + Name );
              printPaaKonsol( "PASSWORD = " + Password );
              printPaaKonsol( "NIV = " + Niv );
              printPaaKonsol( " ");
           }
           rs.close();
           st.close();
           con.close();
           
         } catch ( Exception e ) {
           System.err.println( "WARNING! An Error occurred, please hide! Its about to explode!!!" );
         }
       }
    
    private void printPaaKonsol(String tekst) {
      printArea.append(tekst + "\n");
      printArea.setCaretPosition(printArea.getText().length()-1);
    }
}
