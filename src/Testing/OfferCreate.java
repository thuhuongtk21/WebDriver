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

public class OfferCreate {
	WebDriver driver;

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
  public void hoverNotifyEmail() throws Exception {
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[text()='Offer']")).click();
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[@href='/DCM_UI/offer-create' and text()='Create']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	  
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id='alternateIdInfoIcon']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//*[@id='wrap']/div[3]/div[2]/div[2]/div[1]/div/i")).click();
	  WebElement hoverNotiEmail = driver.findElement(By.xpath("//*[@id='alternateIdInfoIcon']"));
	  Actions action = new Actions(driver);
	  action.moveToElement(hoverNotiEmail).perform();
	  Thread.sleep(5000);
	  String hoverEmailText = driver.findElement(By.xpath("//*[@id='alternateIdInfoIcon']")).getAttribute("title");
	  System.out.println("Hover Email Text = " + hoverEmailText);
  }
  @AfterClass
  public void afterClass() {
	  //test3424234
	  driver.quit();
  }

}
