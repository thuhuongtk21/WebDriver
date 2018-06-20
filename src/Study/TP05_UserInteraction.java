package Study;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TP05_UserInteraction {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	public void TC01_moveMouseToElement() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));

		Actions action = new Actions(driver);
		action.moveToElement(hoverText).perform();

		WebElement hoorayText = driver.findElement(By.xpath("//div[@class='tooltip-inner']"));
		Assert.assertEquals("Hooray!", hoorayText.getText());
	}

	public void TC02_HoverLogin() throws Exception {
		driver.get("http://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement hoverLogin = driver
				.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-user']"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverLogin).perform();

		driver.findElement(By.xpath("//a[text()='login']")).click();

		Thread.sleep(5000);
	}

	public void TC03_ClickAndHold() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> listNumber = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.clickAndHold(listNumber.get(0)).clickAndHold(listNumber.get(3)).click().perform();
		Thread.sleep(5000);

		List<WebElement> selectedNumber = driver
				.findElements(By.xpath("/*[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(4, selectedNumber.size());
	}

	public void TC04_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement doubleClickMe = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));

		Actions action = new Actions(driver);
		action.doubleClick(doubleClickMe).perform();

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals("The Button was double-clicked", alertText);
		alert.accept();
	}

	
	public void TC05_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement rightClickMeButton = driver.findElement(By.xpath("//span[text()='right click me']"));

		Actions action = new Actions(driver);
		action.contextClick(rightClickMeButton).perform();

		WebElement quitVisible = driver
				.findElement(By.xpath("//li[@class ='context-menu-item context-menu-icon context-menu-icon-quit']"));
		// Actions action = new Actions(driver);
		action.moveToElement(quitVisible).perform();

		WebElement quitHover = driver.findElement(By.xpath(
				"//li[@class ='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']"));

		Assert.assertTrue(quitHover.isDisplayed());
		quitHover.click();
		action.release();
	}
	
	public void TC06_DragAndDrop() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebElement source = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//*[@id='droptarget']"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
		
		String textChange = target.getText();
		Assert.assertEquals("You did great!", textChange);
	}
	@Test
	public void TC07_DragAndDrop() {
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebElement source = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//*[@id='droppable']"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
		
		String textChange = driver.findElement(By.xpath("//*[@id='droppable']/p")).getText();
		Assert.assertEquals("Dropped!", textChange);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
