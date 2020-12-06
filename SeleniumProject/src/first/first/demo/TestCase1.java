package first.first.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import first.demo.JupiterConstants;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestCase1 {
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
	
	@Test(description="This method validates the Homepage page to Contact Page - Test case 1:")
	public void validateContactPage() 	{
		
		String actualTitle = "";
		// get the actual value of the title 
		actualTitle = driver.getTitle();
		/*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
		if (actualTitle.contentEquals(JupiterConstants.ExpectedHomeTitle)){
            System.out.println("Jupiter Toys Home page launch - Passed!");
        } else {
            System.out.println("Jupiter Toys Home page launch - Failed");
        }
		//Capture the "Contact" hyperlink 
        WebElement contactLinkResult = driver.findElement(By.linkText(JupiterConstants.Contact));
        //Navigate to Contact Page
        contactLinkResult.click();
        
        var result = HandleConditionalWaits(driver,wait,JupiterConstants.ContactWelcome);
		if(result) {
			System.out.println("Test case 1:");
			System.out.println("1.From the home page go to contact page");
		}
		
		WebElement submit = driver.findElement(By.linkText(JupiterConstants.Submit));
		submit.click();
		System.out.println("2.Click submit button");
		System.out.println("Form Validation Errors");
		
		//Conditional wait for the contact page element(alert message) to be loaded.       
       	result = HandleConditionalWaits(driver,wait, JupiterConstants.ContactError);
		if(result) {
			System.out.println("3.Validate errors");
		}
		
		//Check the Validation error
		//Forename field error message
      	WebElement forenameError = driver.findElement(By.id("forename-err"));
      	System.out.println(forenameError.getText());      	
        
		//Email field Calidation error
      	WebElement emailError = driver.findElement(By.id("email-err"));
      	System.out.println(emailError.getText());
      	
      	//Form elements and adding values to mandatory fields
      	WebElement forename = driver.findElement(By.id("forename"));
      	WebElement email = driver.findElement(By.id("email"));
      	WebElement message = driver.findElement(By.id("message"));
      		
      	//Set values for the mandatory fields with valid data.
      	forename.sendKeys(JupiterConstants.Forename);
      	email.sendKeys(JupiterConstants.EmailAddr);
      	message.sendKeys(JupiterConstants.TextMessage);
      		
      	//Check the Validation error are gone
      	result = HandleConditionalWaits(driver, wait, JupiterConstants.ContactWelcome);
      	if(result) {
      		System.out.println("4.Populate mandatory fields");
      		System.out.println("5.Validate errors are gone");
      		System.out.println("Form errors are fixed!");
      	}
      	
      	//Submit the form with mandatory fields with valid data.
      	submit.click();
      	System.out.println("Form Submitted");
      	
  }
	
  @AfterMethod
  public void postContactValidation() {
	  HandleConditionalWaits(driver,wait, JupiterConstants.SubmitSuccess);
    	
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
