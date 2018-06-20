package Study;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TP09_JavascriptExecutor {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}


	public void TC01() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String domainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals("live.guru99.com", domainName);

		String url = (String) executeForBrowser("return document.URL");
		Assert.assertEquals("http://live.guru99.com/", url);

		WebElement mobileTab = driver.findElement(By.xpath("//*/a[text()='Mobile']"));
		hightlightElement(mobileTab);
		clickForWebElement(mobileTab);

		WebElement samsungAddToCartButton = driver.findElement(
				By.xpath("//h2[a[text()='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		clickForWebElement(samsungAddToCartButton);
		Thread.sleep(5000);

		String addedText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(addedText.contains("Samsung Galaxy was added to your shopping cart."));

		WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		clickForWebElement(privacyPolicyLink);
		String privacyPolicyTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals("Privacy Policy", privacyPolicyTitle);
		scrollToBottom();

		WebElement lastRow = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td"));
		hightlightElement(lastRow);
		Assert.assertTrue(lastRow.isDisplayed());

		String navigateUrl = "http://demo.guru99.com/v4/";
		navigateToURL(navigateUrl);
		Thread.sleep(5000);

		String navigateDomainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals("demo.guru99.com", navigateDomainName);

	}
	
	
	public void TC_02_RemoveDisableAttributes() {

		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframeResult);
		
		WebElement lastNameTxt = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeNameOfElement(lastNameTxt,"disabled");
		
		WebElement firstNameTxt = driver.findElement(By.xpath("//input[@name ='fname']"));
		String firstName = "Automation";
		String lastName = "Testing";
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.sendKeys(lastName);
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@value ='Submit']"));
		clickForWebElement(submitButton);
		String afterSubmit = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(afterSubmit.contains(firstName));
		Assert.assertTrue(afterSubmit.contains(lastName));
	}
	@Test
	public void TC03_UploadImage() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		List<WebElement> imageList = driver.findElements(By.xpath("//img[contains(@src,'avatar-blank.jpg')]"));
		for(WebElement imageSingle : imageList) {
			Assert.assertTrue(checkAnyImageLoaded(imageSingle));
			System.out.println("Image ="+imageSingle);
		}
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

	public void hightlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='2px groove green'", element);
	}

	public Object scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object navigateToURL(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}
	
	public void removeAttributeNameOfElement(WebElement element, String attributeName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		js.executeScript("arguments[0].removeAttribute('" +attributeName+ "');", element);
		
	}
	public boolean checkAnyImageLoaded(WebElement image) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (boolean) js.executeScript("return arguments[0].complete &&" + "typeof arguments[0].naturaWidth != 'undefined' && arguments[0].naturaWidth > 0", image);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
