package Testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Login {
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
	  driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("p165114");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("user1234");
	  driver.findElement(By.xpath("//*[@id='loginBtn']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[text()='Offer']")).click();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[text()='Create']")).click();
	  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	  
	 
		  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
