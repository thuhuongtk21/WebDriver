package Testing;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class addNewOffer {
	WebDriver driver;
	String vendorInput = "17349";
	String vendorID = "17349";
	String vendorType = "DSD";
	String Desc = "Description";
	String dealType_Add = "";
	String level_Add ="";

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
  public void TC01_AddNewOffer() throws Exception {
	  //1. Add Offer on Offer Create screen
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
	  Thread.sleep(30000);
	  
	  //2. On Deal Builder screen
	  driver.findElement(By.xpath("//*[@id='newDealMenu']/button[@class='btn btn-default btn-sm btn-caret']")).click();
	  List <WebElement> dealTypeList = driver.findElements(By.xpath("//*[@id='newDealMenu_option']/li/a"));	
	  System.out.println("Number of Deal type = "+dealTypeList.size());
	  for(WebElement dealTypeValue:dealTypeList) {
		  dealTypeValue.click();
		  List <WebElement> dealTypeSub1 = driver.findElements(By.xpath("//*[@id='newDealMenu_option']/li/ul/li/a"));
		  for(WebElement dealTypeSub1_value:dealTypeSub1) {
			  String dealTypeSub1_getValue = dealTypeSub1_value.getText();
			  if(dealTypeSub1_getValue!="Item level") {
				  List <WebElement> dealTypeSub2 = driver.findElements(By.xpath("//*[@id='newDealMenu_option']/li/ul/li/ul/li/a"));
				  for(WebElement dealTypeSub2_value:dealTypeSub2) {
					  System.out.println("Sub2 = "+dealTypeSub2_value.getText());
				  }
			  }
		  }
	  }
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
