package Testing;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class OpenPage_JS {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  @Test
  public void TC01() throws Exception {
	  driver.get("http://10.10.30.34:18968/DCM_UI/login?r=/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("p165114");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("user1234");
	  WebElement loginButton = driver.findElement(By.xpath("//*[@id='loginBtn']"));
	  clickForWebElement(loginButton);
	  Thread.sleep(30000);
	  
	  String domainName = (String) executeForBrowser("return document.domain");
	  Assert.assertEquals("10.10.30.34", domainName);
	  
	  String titlePage = (String) executeForBrowser("return document.title");
	  Assert.assertEquals("Home - Cost and Deals", titlePage);
  }
  
  public Object executeForBrowser(String javaSript) {
      try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript(javaSript);
      } catch (Exception e) {
                  e.getMessage();
                  return null;
      }
}
  
  public Object clickForWebElement(WebElement element) {
      try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript("arguments[0].click();", element);
      } catch (Exception e) {
                  e.getMessage();
                  return null;
      }
}
  
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}

