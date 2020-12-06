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

public class TestCase3 {
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
		
	@Test(description ="This method validates contact form error message with invalid data - Test case 3:")
	public void submitInvalidData() {
		
		//Capture the "Contact" hyperlink 
        WebElement contactLinkResult = driver.findElement(By.linkText(JupiterConstants.Contact));
        //Navigate to Contact Page
        contactLinkResult.click();
		
        var result = HandleConditionalWaits(driver, wait,JupiterConstants.ContactWelcome);
		
		WebElement forename = driver.findElement(By.id("forename"));
		WebElement email = driver.findElement(By.id("email"));
		WebElement message = driver.findElement(By.id("message"));
		
		//Set values for the mandatory fields with invalid data.
    	forename.sendKeys(JupiterConstants.Forename1);
    	email.sendKeys(JupiterConstants.EmailAddr1);
      	message.sendKeys(JupiterConstants.TextMessage1);
      	System.out.println("2.	Populate mandatory fields with invalid data");
      	
      	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 	
		
	}
 
    @AfterMethod
    public void validateErrors() {
    	
    	//Check the Validation error message
      	var forenameError = driver.findElement(By.id("forename-err"));
      	System.out.println(forenameError.getText());
      	     
		
      	var emailError = driver.findElement(By.id("email-err"));
      	System.out.println(emailError.getText());
      	
      	
      	//WebElement messageError = driver.findElement(By.id("message-err"));
      	//System.out.println(messageError.getText());
    	
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
