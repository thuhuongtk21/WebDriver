package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConnectToDB {
	WebDriver driver;
	Connection con;
	Statement stm;
	String dbURL = "jdbc:db2://RDZUT01.HEB.COM:446/DB2R";
	String UserName = "SVCT_DCM";
	String Pass = "p9rty28j";
	String SQLQuery = "select * from db2tst6.OFFER where OFFER_ID = 1250;";
	@BeforeClass
	  public void beforeClass() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");	
		con = DriverManager.getConnection(dbURL,UserName,Pass);
		//Create Statement Object
		
		
	  }
  @Test
  public void connectToDB() throws SQLException {
	  stm = con.createStatement();
	  ResultSet rs = stm.executeQuery(SQLQuery);
	  String OfferID = rs.getString(1);
	  System.out.println("Offer Id = " + OfferID);
  }


  @AfterClass
  public void afterClass() throws SQLException {
	  con.close();
  }

}
