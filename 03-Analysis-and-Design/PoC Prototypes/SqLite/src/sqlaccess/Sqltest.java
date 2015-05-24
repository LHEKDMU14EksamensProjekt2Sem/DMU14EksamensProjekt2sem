package sqlaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Sqltest {

  public Sqltest() {
  
 // connection, preb.statement og resultset oprettes, og sættes til null.
  Connection con = null;
  Statement st = null;
  ResultSet rs = null;

    try {
      // Åbner databasen/Opretter forbindelse til databasen..
      con = DriverManager.getConnection("jdbc:sqlite:sqlitePrototype.db");
      con.setAutoCommit(false);

      // Diffinerer statement og resulset(Vis alt fra tabellen "salger").        
      st = con.createStatement();
      rs = st.executeQuery( "SELECT * FROM SALGER;" );
      
      //Præsenterer resultater, sålænge der er en "next" i resultsettet.        
      while ( rs.next() ) {
        
         int ID = rs.getInt("ID");
         String  Name = rs.getString("Name");
         String Password  = rs.getString("Password");
         int Niv = rs.getInt("Niv");
         
         System.out.println( "ID = " + ID );
         System.out.println( "NAME = " + Name );
         System.out.println( "PASSWORD = " + Password );
         System.out.println( "NIV = " + Niv );
         System.out.println();
      }
      // Lukker connection, statements og resultset. 
      rs.close();
      st.close();
      con.close();
      
    } catch ( Exception e ) {
      System.err.println( "WARNING! An Error occurred, please hide! Its about to explode!!!" );
    }
  }
    
  }