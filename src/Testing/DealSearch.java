package Testing;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class DealSearch {
	WebDriver driver;
	String vendID;
	//String count;

  @BeforeClass
  public void beforeClass() throws Exception {
	  //Create Fifefox browser
	  driver = new FirefoxDriver();
	  //Enter server link
	  driver.get("http://10.10.30.34:18968/DCM_UI/deals-search");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("p165114");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("user1234");
	  driver.findElement(By.xpath("//*[@id='loginBtn']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  Thread.sleep(5000);

  }
  @Test
  public void contextMenu() throws Exception {
	  //Input search criteria
	  driver.findElement(By.xpath("//*[@id='dealDesc']")).sendKeys("Test");
	  //Click on Search button
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//*[@id='searchIcon']")).click();
	  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);	
	  Thread.sleep(30000);
	  //Get Deal ID
	  String DealID = driver.findElement(By.xpath("//*[@id='tbl-results']/tbody/tr[1]/td[7]/div")).getText();
	  System.out.println(DealID);
	  //Context menu
	  //driver.findElement(By.xpath("//*[@id='tbl-results']/tbody/tr[1]/td[9]//u")).click(); --> Click on Offer ID
	  WebElement rightClick = driver.findElement(By.xpath("//*[@id='tbl-results']/tbody/tr[1]/td[2]"));
	  Actions action = new Actions(driver);
	  action.contextClick(rightClick).perform();
	  Thread.sleep(5000);
	  //Check Deal Maintain option is visible
	  WebElement dealMainVisible = driver.findElement(By.xpath("//*[@id='dcm-contextmenu']/li/a"));
	  action.moveToElement(dealMainVisible).perform();
	  Assert.assertTrue(dealMainVisible.isDisplayed());
	  dealMainVisible.click();
	  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);	
	  //Verify application navigates to Deal Maintain successfully
	  String dealMain = driver.getTitle();
	  Assert.assertEquals(dealMain, "Deal Maintenance - Cost and Deals");
	  //Verify Deal ID on Deal Maintenance
	  String DealIdMain = driver.findElement(By.xpath("//*[@id='deal-search-input']")).getAttribute("text");  //--> Issue: get Deal ID = NULL
	  System.out.println("Deal ID on Deal Maintain = " + DealIdMain);
	  //Assert.assertEquals(DealIdMain, DealID);
	  Thread.sleep(5000);
	  
	 
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
