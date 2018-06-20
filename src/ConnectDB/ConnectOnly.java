package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConnectOnly {
	   private Connection connection;
	   private static java.sql.Statement statement;
	   private static ResultSet rs;

  @BeforeClass
  public void beforeClass() {
	  String databaseURL = "jdbc:mysql://localhost:3306/easy";
      String user = "root";
      String password = "root";
      connection = null;
      try {
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Connecting to Database...");
          connection = DriverManager.getConnection(databaseURL, user, password);
          if (connection != null) {
              System.out.println("Connected to the Database...");
          }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      catch (ClassNotFoundException ex) {
         ex.printStackTrace();
      }
  }
  @Test
  public void f() {
	  try {
          String query = "select * from employee";
          statement = connection.createStatement();
          rs = statement.executeQuery(query);

          while(rs.next()){
              int EmpId= rs.getInt("EmpId");
              String EmpName= rs.getString("EmpName");
              String EmpAddress=rs.getString(3);
              String EmpDept=rs.getString("EmpDept");
              Double EmpSal= rs.getDouble(5);
              System.out.println(EmpId+"\t"+EmpName+"\t"+EmpAddress+"\t"+EmpSal+"\t"+EmpDept);
          }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
  }
  @AfterClass
  public void afterClass() {
	  if (connection != null) {
          try {
              System.out.println("Closing Database Connection...");
              connection.close();
          } catch (SQLException ex) {
              ex.printStackTrace();
          }
      }
}
}

