package first.first.demo;

import org.testng.annotations.Test;

import first.demo.JupiterConstants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class TestCase2 {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
 	public void testSetup() {
		//Instantiation of chrome driver
				System.setProperty(JupiterConstants.ChromeDriverKey, JupiterConstants.ChromeDriverValue);
				driver = new ChromeDriver();
				wait = new WebDriverWait(driver, 20);
				driver.manage().window().maximize();
		  
	  }
	
	@BeforeMethod
	public void openBrowser() {
		driver.get(JupiterConstants.BaseURL);
		System.out.println("We are currently on the following URL" +driver.getCurrentUrl());
	  }
	
	@Test(description ="This method validates successful submission with valid data - Test case 2:")
	public void submitValidData() {
		
		
		//Capture the "Contact" hyperlink 
        WebElement contactLinkResult = driver.findElement(By.linkText(JupiterConstants.Contact));
        //Navigate to Contact Page
        contactLinkResult.click();
		
        var result = HandleConditionalWaits(driver, wait,JupiterConstants.ContactWelcome);
        System.out.println("Test case 2:");
		System.out.println("1.From the home page go to contact page");
        
        //Form elements and adding values to mandatory fields
        WebElement forename = driver.findElement(By.id("forename"));
      	WebElement email = driver.findElement(By.id("email"));
      	WebElement message = driver.findElement(By.id("message"));
      		
      	//Set values for the mandatory fields with valid data.
      	forename.sendKeys(JupiterConstants.Forename);
      	email.sendKeys(JupiterConstants.EmailAddr);
      	message.sendKeys(JupiterConstants.TextMessage);
      	
      	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
      	//Check the Validation error are gone
  		result = HandleConditionalWaits(driver,wait, JupiterConstants.ContactWelcome);
  		
  		//Submit the contact page with valid data
  		WebElement submit = driver.findElement(By.linkText(JupiterConstants.Submit));
		submit.click();
	}
 

	@AfterMethod
	public void validateSucceessfulSubmissionMessage() {
		HandleConditionalWaits(driver,wait, JupiterConstants.SubmitSuccess);    	    	
	}

 

	@AfterClass
	public void afterClass() {
		driver.quit();		
	}
	
	public boolean HandleConditionalWaits(WebDriver driverParam,WebDriverWait waitParam,String messageParam) {
		//Conditional wait for the contact page element(alert message) to be loaded.       
		waitParam.until(ExpectedConditions.presenceOfElementLocated(By.className(JupiterConstants.Alert)));
		System.out.println("Message - response received");
				
		WebElement eleText = driverParam.findElement(By.className(JupiterConstants.Alert));

		int launchMessage = eleText.getText().indexOf(messageParam);
		if(launchMessage >= 0)
			return true;
		else
			return false;		

	}


}
