package Study;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TP06_iframe_WindowPopup {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  public void TC01_Iframe() {
	  driver.get("https://kyna.vn/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  WebElement iframeFacebook = driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com/kyna.vn')]"));
	  driver.switchTo().frame(iframeFacebook);
	  
	  WebElement facebookLike = driver.findElement(By.xpath("//div[@class='_1drq']"));
	  String facebookLike_number = facebookLike.getText();
	  Assert.assertEquals("168K likes", facebookLike_number);
	  //Back to parent node
	  driver.switchTo().defaultContent();
  }
  
  public void TC02_Iframe() {
	  driver.get("http://www.hdfcbank.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(lookingForIframe);
	  
	  WebElement lookingForText = driver.findElement(By.xpath("//*[@id='messageText']"));
	  Assert.assertEquals("What are you looking for?", lookingForText.getText());
  }
  
  public void TC03_Window() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  WebElement openNewWindow = driver.findElement(By.xpath("//a[text()='Click Here']"));
	  openNewWindow.click();
	  
	  String parentID = driver.getWindowHandle();
	  //System.out.println("Parent ID = "+parentID);	  
	  switchToWindowByTitle("Google");
	  String googleTitle = driver.getTitle();
	  Assert.assertEquals("Google", googleTitle);
	  closeAllWithoutParentWindows(parentID);
	  
	  String parentTitle = driver.getTitle();
	  Assert.assertEquals("SELENIUM WEBDRIVER FORM DEMO", parentTitle);
  }
  @Test
  public void TC04_OpenNewWindow() throws Exception {
	  driver.get("http://www.hdfcbank.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
	  String parentID = driver.getWindowHandle();
	  String newWindowtitle = "HDFC Bank Kisan Dhan Vikas e-Kendra";
	  WebElement agriPage = driver.findElement(By.xpath("//a[text()='Agri']"));
	  agriPage.click();	  
	  switchToWindowByTitle(newWindowtitle);
	  String agriTitle = driver.getTitle();
	  System.out.println("Title = "+agriTitle);	  
	  Assert.assertEquals("HDFC Bank Kisan Dhan Vikas e-Kendra", agriTitle);
	  
	  //Open Account Details page
	  driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	  switchToWindowByTitle("Welcome to HDFC Bank NetBankin");
	  String accountDetailsTitle = driver.getTitle();
	  System.out.println("Welcome to HDFC Bank NetBankin = "+accountDetailsTitle);
	  //Assert.assertEquals("Welcome to HDFC Bank NetBankin", accountDetailsTitle);
	  
	  //Click on Privacy Policy
	  driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  String privacyPolicyTitle = driver.getTitle();
	  Assert.assertEquals("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan", privacyPolicyTitle);
	  
	  //Click CSR link
	  driver.findElement(By.xpath("//a[text()='CSR']")).click();
	  switchToWindowByTitle("Corporate Social Responsibility - Parivartan by HDFC Bank");
	  String CSRTitle = driver.getTitle();
	  Assert.assertEquals("Corporate Social Responsibility - Parivartan by HDFC Bank", CSRTitle);
	  
	  //Back to main window
	  driver.switchTo().defaultContent();
	  Thread.sleep(10000);
	  
	  closeAllWithoutParentWindows(parentID);
	  Thread.sleep(5000);
	  
  }
  
  //Switch to child Windows (only 2 windows)
  public void switchToChildWindow(String parent) {
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindow : allWindows) {
                  if (!runWindow.equals(parent)) {
                              driver.switchTo().window(runWindow);
                              break;
                  }
      }
  }
  
  //Switch to child Windows (greater than 2 windows and title of the pages are unique)
  public void switchToWindowByTitle(String title) {
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindows : allWindows) {
                  driver.switchTo().window(runWindows);
                  String currentWin = driver.getTitle();
                  if (currentWin.equals(title)) {
                              break;
                  }
      }
  }
  
  //Close all windows without parent window
  public boolean closeAllWithoutParentWindows(String parentWindow) {
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindows : allWindows) {
                  if (!runWindows.equals(parentWindow)) {
                              driver.switchTo().window(runWindows);
                              driver.close();
                  }
      }
      driver.switchTo().window(parentWindow);
      if (driver.getWindowHandles().size() == 1)
                 return true;
      else
                 return false;
}
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
