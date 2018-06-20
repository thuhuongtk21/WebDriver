package Study;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TP04_Button_Radiobutton_Checkbox_Alert {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }

  public void TC01_Button() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  WebElement enableButton_01 = driver.findElement(By.xpath("//*[@id='button-enabled']"));
	  enableButton_01.click();
	  
	  driver.navigate().back();
	  
	  WebElement enableButton_02 = driver.findElement(By.xpath("//*[@id='button-enabled']"));
	  clickForWebElement(enableButton_02);
	  
  }

  public void TC02_Checkbox() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  WebElement checkBox = driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
	  clickForWebElement(checkBox);
	  Assert.assertTrue(checkBox.isSelected());
	  clickForWebElement(checkBox);
	  Assert.assertFalse(checkBox.isSelected());
	  
	  if(!checkBox.isSelected()) {
		  clickForWebElement(checkBox);
		  
	  }
  }
  
  public void TC03_RadioButton() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  WebElement radioButton = driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input"));
	  clickForWebElement(radioButton);
	  
	  if(!radioButton.isSelected()) {
		  clickForWebElement(radioButton);
	  }
  }
  
  public void TC04_Alert_Accept() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  WebElement acceptAlert = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]"));
	  acceptAlert.click();
	  
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals("I am a JS Alert", alert.getText());
	  
	  alert.accept();
	  
	  WebElement resultAlert = driver.findElement(By.xpath("//*[@id='result']"));
	  Assert.assertEquals("You clicked an alert successfully", resultAlert.getText());
  }
  
  public void TC05_Alert_Cancel() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  WebElement alertCancel = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]"));
	  alertCancel.click();
	  
	  Alert alert = driver.switchTo().alert();
	  alert.dismiss();
	  WebElement resultAlert = driver.findElement(By.xpath("//*[@id='result']"));
	  Assert.assertEquals("You clicked: Cancel", resultAlert.getText());
  }
  
  public void TC06_enterAlert() throws Exception {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  WebElement enterAlert = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]"));
	  enterAlert.click();
	  
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals("I am a JS prompt", alert.getText());
	  String enterText = "Huong Nguyen";
	  alert.sendKeys(enterText);
	  
	  alert.accept();
	  WebElement alertResult = driver.findElement(By.xpath("//*[@id='result']"));
	  Assert.assertEquals("You entered: "+enterText, alertResult.getText());

	  
  }
  @Test
  public void TC07_AuthenAlert() {
	  //Enter user & pass into URL
	  driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
	  String afterLogin = driver.findElement(By.xpath("//*[@id='content']/div/p")).getText();
	  Assert.assertEquals("Congratulations! You must have the proper credentials.", afterLogin);
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
