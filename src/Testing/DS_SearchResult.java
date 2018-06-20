package Testing;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DS_SearchResult {
	WebDriver driver;
	String DealDesc = "adv";

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
	  Thread.sleep(10000);
	  driver.findElement(By.xpath("//*[@id='dealDesc']")).sendKeys(DealDesc);
	  //Thread.sleep(30000);
	  //Click on Search button
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//*[@id='searchIcon']")).click();
	  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);	
	  Thread.sleep(30000);
  }

  public void TC001_countSear() throws Exception {

	  List <WebElement> col_table = driver.findElements(By.xpath("//*[@id='tbl-results']/tbody/tr[1]/td"));	  
	  int col_num = col_table.size();
	  System.out.println("Number of column = "+ col_num);
	  List <WebElement> row_table = driver.findElements(By.xpath("//*[@id='tbl-results']/tbody/tr/td[1]"));	  
	  int row_num = row_table.size();
	  System.out.println("Number of row = "+row_num);
	  //Count number of page
	  List <WebElement> page_table = driver.findElements(By.xpath("//*[@id='results_paginate']/span/a"));
	  //System.out.println("Number of page = "+page.size());
	  int page_num = page_table.size();
	  System.out.println("Number of page = "+page_num);
	  for (int page = 1;page<=page_num;page++) {
		  driver.findElement(By.xpath("//*[@id='results_paginate']/span/a["+page+"]")).click();
		  Thread.sleep(30000);
	  }
	  
  }

  public void TC_002(){ //Get All value on page 1
	  WebElement myTable = driver.findElement(By.xpath("//*[@id='tbl-results']/tbody"));
	  List <WebElement> countRow_Table = myTable.findElements(By.tagName("tr"));
	  int RowCount = countRow_Table.size();
	  for(int Row = 0; Row < RowCount; Row++) {
		  List <WebElement> countColumn_Table = countRow_Table.get(Row).findElements(By.tagName("td"));
		  int ColumnCount = countColumn_Table.size();
		  System.out.println("Number of cell in Row "+ Row + " are " + ColumnCount);
		  for (int Column = 0; Column < ColumnCount; Column++) {
			  String cellText = countRow_Table.get(Column).getText();
			  System.out.println("Cell value of row number "+Row + " and column number "+ Column + " is "+cellText);
		  }
		  System.out.println("--------------------");
	  }
	  
  }
  public void TC003_PrintVendor() {
	  List <WebElement> Row_Table = driver.findElements(By.xpath("//*[@id='tbl-results']/tbody/tr/td[1]"));
	  int countRow = Row_Table.size();
	  for(int row = 1; row <= countRow; row ++) {
		  String Vendor = Row_Table.get(row).getText();
		  System.out.println("Vendor ID " + row + " = " + Vendor);
	  }
  }
  @Test
  public void TC004_PrintVendor_AllPages() throws Exception {
	  List <WebElement> page_table = driver.findElements(By.xpath("//*[@id='results_paginate']/span/a"));
	  int count_page = page_table.size();
	  for(int page = 1; page <=count_page; page++) {
		  driver.findElement(By.xpath("//*[@id='results_paginate']/span/a["+page+"]")).click();
		  Thread.sleep(30000);
		  List <WebElement> Row_Table = driver.findElements(By.xpath("//*[@id='tbl-results']/tbody/tr/td[1]"));
		  int countRow = Row_Table.size();
		  System.out.println("Row on page "+page +" = " + countRow);
		  System.out.println("xpath = "+ page_table);
		  for(int row = 0; row < countRow; row ++) {
			  String Vendor = Row_Table.get(row).getText();
			  System.out.println("Vendor ID " + row + " = " + Vendor);			  
	  }
	  
	  
	  }
  }
	  
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
