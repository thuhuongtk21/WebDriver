package Practice;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class JSExcutor {
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor)driver;	

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.get("http://demo.guru99.com/V4/");
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
  }
  @Test
  public void Login() {
	  long start_time = System.currentTimeMillis();
	  js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
	  System.out.println("Passed time: "+(System.currentTimeMillis() - start_time));
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
