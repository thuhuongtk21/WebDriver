package Testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Practice_001 {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  //Create browser
	  driver = new FirefoxDriver();
	  driver.get("http://10.10.30.34:18968/DCM_UI/login?r=/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }
  @Test
  public void TC_001() {
	  String getHomeTitle = driver.getTitle();
	  Assert.assertEquals("Login - Cost and Deals", getHomeTitle);
	 
		  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
