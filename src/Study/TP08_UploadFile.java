package Study;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TP08_UploadFile {
	WebDriver driver;
	String filePath = "D:\\Document\\Automation\\Study\\Upload\\download.jpg";
	String fileName = "download.jpg";

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  public void TC01_SendKeys() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  WebElement uploadFile = driver.findElement(By.xpath("//input[@type ='file']"));
	  uploadFile.sendKeys(filePath);
	  
	  WebElement imageSelected = driver.findElement(By.xpath("//p[@class='name' and contains(text(),'" + fileName + "')]"));
	  Assert.assertTrue(imageSelected.isDisplayed());
	  
	  driver.findElement(By.xpath("//span[text()='Start']")).click();
	  Thread.sleep(10000);
	  
	  WebElement imageUploaded = driver.findElement(By.xpath("//img[contains(@src,'\" + fileName + \"')]"));
	  Assert.assertTrue(checkAnyImageLoaded(imageUploaded));
  }
  @Test
  public void TC02_autoIT() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  WebElement uploadFile = driver.findElement(By.xpath("//input[@type ='file']"));
	  
	  driver.findElement(By.xpath("//span[contains(text(),'Add files...')]")).click();	  
	  Runtime.getRuntime().exec(new String[] { ".\\upload\\ie.exe", filePath });
	  
  }
  public boolean checkAnyImageLoaded(WebElement image) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (boolean) js.executeScript("return arguments[0].complete &&" + "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
	}
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
