package Testing;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class LocationGroup {
	WebDriver driver;
	String groupName = "Add new LG";
	String Abbreviation = "New";
	String Description = "Des";

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();	  
	  driver.get("http://10.10.30.34:18968/DCM_UI/login?r=/");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("p165114");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("user1234");
	  driver.findElement(By.xpath("//*[@id='loginBtn']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
  }
  @Test
  public void locationGroupCreate() throws Exception {
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[contains(text(),'Location Group')]")).click();
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[@href=\"/DCM_UI/create-location-group\" and text()='Create']")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id='addBasket']")).click();
	  //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id='basketName']")).sendKeys(groupName);
	  driver.findElement(By.xpath("//*[@id='abb']")).sendKeys(Abbreviation);
	  driver.findElement(By.xpath("//*[@id='description']")).sendKeys(Description);
	  driver.findElement(By.xpath("//*[@id='selectTable']/tbody/tr[3]/td/span")).click();
	  driver.findElement(By.xpath("//*[@id='arrow-move-right']")).click();	  
	  //Thread.sleep(5000);
	  Actions action = new Actions(driver);
	  WebElement scroolBar = driver.findElement(By.xpath("html"));
	  int numberOfPixel = 1000;
	  action.moveToElement(scroolBar).clickAndHold().moveByOffset(0, numberOfPixel).release().perform();
	  Thread.sleep(20000);
	  //driver.findElement(By.xpath("//*[@id='saveBasket']")).click();
	  Thread.sleep(30000);
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
