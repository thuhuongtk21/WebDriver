package Testing;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class BDC {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() throws Exception {
	  //Create Fifefox browser
	  driver = new FirefoxDriver();
	  //Enter server link
	  driver.get("http://10.10.30.34:18968/DCM_UI/browse-deals-and-costs1");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("v903023");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("user1234");
	  driver.findElement(By.xpath("//*[@id='loginBtn']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  Thread.sleep(30000);

  }

  public void getVendor_DropdownList() {
	  driver.findElement(By.xpath("//*[@id='search-form-left']/div[1]/div[1]/div")).click();
	  List <WebElement> items = driver.findElements(By.xpath("//*[contains(@id,'ui-id')]/a"));
	  System.out.println("List Items = "+items.size());
	 // WebElement item;
	  for(WebElement item:items) {
		  String text = item.getText();
		  System.out.println("Vendor = "+text);
	  }
  }

  private void selectVenAndSearch() throws Exception {
	  String option = "17349";
	  driver.findElement(By.xpath("//*[@id='search-form-left']/div[1]/div[1]/div")).click();
	  List <WebElement> items = driver.findElements(By.xpath("//*[contains(@id,'ui-id')]/a"));
	 //WebElement item;
	  for(WebElement item:items) {
		  if(item.getText().contains(option)) {
			  item.click();	  
		  }
		  
	  }
	  	  //throw new NoSuchElementException("Can't find + "+option+ " in dop-down list");
	  	Thread.sleep(10000);
	  	//driver.findElement(By.xpath("//*[@id='autocomplete-vendor']")).sendKeys(Keys.ENTER);
	  	//System.out.println("OK");
	  	driver.findElement(By.xpath("//*[@id='btn-search']")).click();
	  	Thread.sleep(30000);
	  	}
  @Test
  public void addVendorToList() throws Exception {
	  //Get Vendor list from drop-down
	  ArrayList<String> vendorList = new ArrayList<String>();
	  ArrayList<String> vendorSearchResultt = new ArrayList<String>();
	  driver.findElement(By.xpath("//*[@id='search-form-left']/div[1]/div[1]/div")).click();
	  List <WebElement> items = driver.findElements(By.xpath("//*[contains(@id,'ui-id')]/a"));
	  System.out.println("List Items = "+items.size());
	  for (WebElement item:items) {
		  String text= item.getText();
		  vendorList.add(text);		  
	  }
	  System.out.println("Vendor List: "+vendorList);
	  String option1 = "17276";
	  String option2 = "DSD";
	  for(WebElement item:items) {
		  if(item.getText().contains(option1) && item.getText().contains(option2)) {
			  item.click();	  
		  }		  
	  }
	  	  
	  	Thread.sleep(10000);
	  	driver.findElement(By.xpath("//*[@id='btn-search']")).click();
	  	Thread.sleep(30000);
		  //Get Vendor ID from search result	  	
	  	List <WebElement> page_table = driver.findElements(By.xpath("//*[@id='results-paginate-netcosts']/span/a"));
		  int count_page = page_table.size();
		  for(int page = 1; page <count_page; page++) {
			  driver.findElement(By.xpath("//*[@id='results-paginate-netcosts']/span/a["+page+"]")).click();
			  Thread.sleep(30000);
			  List <WebElement> Row_Table = driver.findElements(By.xpath("//*[@id='netcost-table']/tbody/tr/td[1]"));
			  int countRow = Row_Table.size();
			  System.out.println("Row = "+countRow);
			  for(int row = 0; row < countRow; row ++) {
				  String Vendor = Row_Table.get(row).getText();	
				  if(Vendor!= null) {
				  vendorSearchResultt.add(Vendor);
				  }
		      }
			  while(count_page == 5) {
				  driver.findElement(By.xpath("//*[@id='results-paginate-netcosts']/span/a[4]")).click();
			  }		  
		  }
		  System.out.println(vendorSearchResultt);
	  	}  

  @AfterClass
  public void afterClass() {
	 driver.close();
  }

}
