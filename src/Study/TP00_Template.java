package Study;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class TP00_Template {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  
	  //Chrome
	  System.setProperty("webdriver.chrome.driver", ".Driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
	  //Firefox (for version > 47
	  System.setProperty("webdriver.geckodriver.driver", ".\\Driver\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  
	  //IE
	  System.setProperty("webdriver.chrome.driver", ".Driver\\IEDriverServer.exe");
	  driver = new InternetExplorerDriver();
  }
  @Test
  public void f() {
  }
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
