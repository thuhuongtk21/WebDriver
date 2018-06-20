package ConnectDB;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDemo {
    // Connection object
    static Connection con = null;
    // Statement object
    private static Statement stmt;
    // Constant for Database URL
    public static String DB_URL = "jdbc:mysql://RDZUT01.HEB.COM:446/DB2R";   
    // Constant for Database Username
    public static String DB_USER = "SVCT_DCM";
    // Constant for Database Password
    public static String DB_PASSWORD = "p9rty28j";

  @BeforeClass
  public void beforeClass() {
      try{
          // Make the database connection
          String dbClass = "com.mysql.jdbc.Driver";
          Class.forName(dbClass).newInstance();
          // Get connection to DB
          Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
          // Statement object to send the SQL statement to the Database
          stmt = con.createStatement();
          }
          catch (Exception e)
          {
                e.printStackTrace();
          }
  }
  @Test
  public void ConnectDbDemo() {
      try{
      String query = "select * from db2tst6.OFFER where OFFER_ID = 1250";
      // Get the contents of userinfo table from DB
      ResultSet res = stmt.executeQuery(query);
      System.out.println("Offer ID = " + res.getString(1));
      System.out.println(query);
      // Print the result untill all the records are printed
      // res.next() returns true if there is any next record else returns false
      //while (res.next())
      //{
            // System.out.print("Offer ID = " + res.getString(1));
      //System.out.print("\t" + res.getString(2));
     // System.out.print("\t" + res.getString(3));
      //System.out.println("\t" + res.getString(4));
    //  }
      }
      catch(Exception e)
      {
             e.printStackTrace();
      } 
  }
  @AfterClass
  public void afterClass() throws Exception {
	// Close DB connection
      if (con != null) {
      con.close();
      }
  }

}
