package Testing;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class TradeAllowance_Deal {
	WebDriver driver;
	String vendorInput = "17349";
	String vendorID = "17349";
	String vendorType = "DSD";
	String Desc = "Description";
	String dealType_Add = "";
	String level_Add ="";
	String product = "2188810031";

  @BeforeClass
  public void beforeClass() throws Exception {
	  driver = new FirefoxDriver();	  
	  driver.get("http://10.10.30.34:18968/DCM_UI/login?r=/");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("p165114");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("user1234");
	  driver.findElement(By.xpath("//*[@id='loginBtn']")).click();	  
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[text()='Offer']")).click();
	  driver.findElement(By.xpath("//*[@id='example-navbar-collapse']//a[@href='/DCM_UI/offer-create' and text()='Create']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	  
	  Thread.sleep(5000);
  }
  @Test
  public void f() throws Exception {
	  //1. Add an Offer
	  driver.findElement(By.xpath("//*[@id='vendorId']")).sendKeys(vendorInput);
	  List <WebElement> vendorList = driver.findElements(By.xpath("//*[contains(@id,'ui-id')]/a"));
	  for(WebElement vendor:vendorList) {
		  if(vendor.getText().contains(vendorID) && vendor.getText().contains(vendorType)) {
			  vendor.click();
		  }
	  }
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//*[@id='descriptionId']")).sendKeys(Desc);
	  //Select Assigned To User Type
	  driver.findElement(By.xpath("//*[@id='createOfferForm']/div[11]/div[2]/div/button")).click();
	  List <WebElement> AssignToList = driver.findElements(By.xpath("//*[@id='createOfferForm']//a"));
	  for(WebElement AssignTo:AssignToList) {
		  if(AssignTo.getText().contains("HEB")) {
			  AssignTo.click();			 
		  }
	  }
	  driver.findElement(By.xpath("//*[@id='nextOffer']")).click();
	  Thread.sleep(60000);
	  //2. Add Trade Allowance Deal --> Item level
	  driver.findElement(By.xpath("//*[@id='newDealMenu']/button[@class='btn btn-default btn-sm btn-caret']")).click();
	  //driver.findElement(By.xpath("//*[@id='newDealMenu_option']/li[@class='dropdown-submenu parent-child' and @data-id='TPR']/a")).click();
	  //driver.findElement(By.xpath("//*[@id='newDealMenu_option']/li[1]/ul/li/a")).click();
	  //driver.findElement(By.xpath("//*[@id='newDealMenu_option']/li[1]/ul/li/ul/li[1]/a")).click();
	  driver.findElement(By.xpath("//*[@id='newDealMenu_option']//li[@class='parent-child last-li' and @data-id ='PO_TPR_Item']//a[text()='Item level']")).click();
	  Thread.sleep(30000);
	  driver.findElement(By.xpath("//*[@id='searchTextField']")).sendKeys(product);
	  Thread.sleep(5000);
	  WebElement doubleClick_Product = driver.findElement(By.xpath("//*[@id='membersView']/div/div[2]/div/ul/li"));
	  Actions action = new Actions(driver);
	  action.doubleClick(doubleClick_Product).perform();
	  Thread.sleep(30000);
	  //Add Store to Deal
	  driver.findElement(By.xpath("//*[@id='selectionPaneStoresButton']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//span[@class='basket-item-text' and text()='All Stores']")).click();
	  Thread.sleep(2000);
	  WebElement doubleClick_store = driver.findElement(By.xpath("//*[@id='membersView']/div/div[2]/div/ul/li[8]"));
	  action.doubleClick(doubleClick_store).perform();
	  Thread.sleep(30000);
	  //Add LOB to Deal
	  driver.findElement(By.xpath("//*[@id='selectionPaneLobsButton']")).click();
	  Thread.sleep(1000);
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
