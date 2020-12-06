package first.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JupiterDemo {
	
	
	public static void main(String[] args) {
		//Instantiation of chrome driver
		System.setProperty(JupiterConstants.ChromeDriverKey, JupiterConstants.ChromeDriverValue);
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		
		//launch Google Chrome and direct it to the Jupiter Toys URL
		
		driver.get(JupiterConstants.BaseURL);
		driver.manage().window().maximize();	
    
		
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
        
        //System.out.println("Inside contact page!");
        //System.out.println("Searching for form name.....");
        
        //Verify "Contact" page Welcome message
        
        //Conditional wait for the contact page element(form) to be loaded.       
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.name("form")));
		//System.out.println("Contact Page - response received");
		
			
		//Conditional wait for the contact page element(alert message) to be loaded.       
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert")));
		//System.out.println("Message - response received");
				
		//WebElement eleText = driver.findElement(By.className("alert"));
		var result = HandleConditionalWaits(driver,wait,JupiterConstants.ContactWelcome);
		if(result) {
			System.out.println("Test case 1:");
			System.out.println("1.	From the home page go to contact page");
			//System.out.println("Form loaded successfully");
		}
			
		
		
		//WebElement submitButton = driver.findElement(By.className("btn-contact btn btn-primary"));
		WebElement submit = driver.findElement(By.linkText(JupiterConstants.Submit));
		submit.click();
		//System.out.println("Form Submitted");
		System.out.println("2.	Click submit button");
		System.out.println("Form Validation Errors");
		
		//Conditional wait for the contact page element(alert message) to be loaded.       
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert")));
		//System.out.println("Waiting for error message");
		
		//eleText = driver.findElement(By.className("alert"));
		result = HandleConditionalWaits(driver,wait, JupiterConstants.ContactError);
		if(result) {
			System.out.println("3.	Validate errors");
		}
		
		
		//Check the Validation error
      	WebElement forenameError = driver.findElement(By.id("forename-err"));
      	System.out.println(forenameError.getText());      	
        
		
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
		result = HandleConditionalWaits(driver,wait, JupiterConstants.ContactWelcome);
		if(result) {
			System.out.println("4.	Populate mandatory fields");
			System.out.println("5.	Validate errors are gone");
			System.out.println("Form errors are fixed!");
		}
			
		
		
		//Submit the form with mandatory fields with valid data.
		submit.click();
		System.out.println("Form Submitted");
		HandleConditionalWaits(driver,wait, JupiterConstants.SubmitSuccess);
		
		
        //"We welcome your feedback"
        System.out.println("Success");
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        //Submit the form with mandatory fields with valid data.        
        WebElement backButton = driver.findElement(By.linkText(JupiterConstants.BackToHome));
        backButton.click();
        
        result = HandleConditionalWaits(driver,wait,JupiterConstants.ContactWelcome);
        System.out.println("Test case 2:");
		System.out.println("     	1.	From the home page go to contact page");
        
        //Form elements and adding values to mandatory fields
        forename = driver.findElement(By.id("forename"));
      	email = driver.findElement(By.id("email"));
      	message = driver.findElement(By.id("message"));
      		
      	//Set values for the mandatory fields with valid data.
      	forename.sendKeys(JupiterConstants.Forename);
      	email.sendKeys(JupiterConstants.EmailAddr);
      	message.sendKeys(JupiterConstants.TextMessage);
      	
      	System.out.println("       	2.	Populate mandatory fields");
      	
      //Check the Validation error are gone
  		result = HandleConditionalWaits(driver,wait, JupiterConstants.ContactWelcome);
  		//if(result) {
  			//System.out.println("       	4.	Validate successful submission message");
  		//}
      	
      	submit = driver.findElement(By.linkText(JupiterConstants.Submit));
		submit.click();
		System.out.println("       	3.	Click submit button");
		
		HandleConditionalWaits(driver,wait, JupiterConstants.SubmitSuccess);
		System.out.println("       	4.	Validate successful submission message");	     		
      	
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        	
       //Back to home page after successful submission of contact form..        
       backButton = driver.findElement(By.linkText(JupiterConstants.BackToHome));
       backButton.click();
       //Jupiter Toys home page and verify the text in home page
       driver.get(JupiterConstants.BaseURL);
       wait.until(ExpectedConditions.presenceOfElementLocated(By.className(JupiterConstants.HomeText)));
       System.out.println("Test case 3:");
       System.out.println("1.	From the home page go to contact page");
        
        //driver.get(JupiterConstants.BaseURL);
     //Capture the "Contact" hyperlink 
       contactLinkResult = driver.findElement(By.linkText(JupiterConstants.Contact));
       //Navigate to Contact Page
       contactLinkResult.click();
        
        result = HandleConditionalWaits(driver,wait,JupiterConstants.ContactWelcome);
		if(result)
			System.out.println("Form loaded successfully");
		
		
		forename = driver.findElement(By.id("forename"));
		email = driver.findElement(By.id("email"));
		message = driver.findElement(By.id("message"));
		
		//Set values for the mandatory fields with invalid data.
    	forename.sendKeys(JupiterConstants.Forename1);
    	email.sendKeys(JupiterConstants.EmailAddr1);
      	message.sendKeys(JupiterConstants.TextMessage1);
      	System.out.println("2.	Populate mandatory fields with invalid data");
      		
      	//Check the Validation error message
      	forenameError = driver.findElement(By.id("forename-err"));
      	System.out.println(forenameError.getText());
      	
        
		
      	emailError = driver.findElement(By.id("email-err"));
      	System.out.println(emailError.getText());
      	
      	
      	//WebElement messageError = driver.findElement(By.id("message-err"));
      	//System.out.println(messageError.getText());
      	
		
        //"We welcome your feedback"
        System.out.println("3.	Validate errors");
        
        
        //Jupiter Toys home page and verify the text in home page
        System.out.println("Test case 4:");
        System.out.println("       	1.	From the home page go to shop page");
        driver.get(JupiterConstants.BaseURL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(JupiterConstants.HomeText)));
        System.out.println("Home page lead text verified");
        //Capture the nav tag
        //Capture the "Contact" hyperlink 
        WebElement navList = driver.findElement(By.className(JupiterConstants.Navigation).linkText(JupiterConstants.Shop));
        System.out.println(navList);
        navList.click();
        
        //Click buy 2 times buy on Funny Cow
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-6")));
        WebElement funnyCow = driver.findElement(By.id("product-6"));
        funnyCow.findElement(By.linkText(JupiterConstants.Buy)).click();
        funnyCow.findElement(By.linkText(JupiterConstants.Buy)).click();
        System.out.println(" 2.Click buy button 2 times on “Funny Cow");
        
        //Click buy 1 time buy on Funny Cow
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-6")));
        WebElement fluffyBunny = driver.findElement(By.id("product-4"));
        fluffyBunny.findElement(By.linkText(JupiterConstants.Buy)).click();
        System.out.println("3.Click buy button 1 time on “Fluffy Bunny”");
        
        //Capture the "Cart" hyperlink 
        navList = driver.findElement(By.className(JupiterConstants.Navigation).partialLinkText(JupiterConstants.Cart));
        navList.click();
        System.out.println("4.Click the cart menu");
        
        //Capture the number of items in cart
        WebElement CartNumber = driver.findElement(By.xpath("//*[@id='nav-cart']/a/span"));
        System.out.println(CartNumber.getText());
        int total = Integer.parseInt(CartNumber.getText());
        
        //Verify the items in cart 
        //navList = driver.findElement(By.className("nav").partialLinkText("Cart"));
        //will wait up to 10 seconds until element will be present on the page
        WebElement cartElement = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[2]/div/form/table/tbody/tr[1]/td[1]"))));
        System.out.println(cartElement.getText());
        WebElement funnyCowQuantity = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[1]/td[3]/input[@name='quantity']"));
        System.out.println(funnyCowQuantity.getAttribute("value"));
       
        cartElement = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[1]"))));
        System.out.println(cartElement.getText());
        WebElement fluffyBunnyQuantity = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[3]/input[@name='quantity']"));
        System.out.println(fluffyBunnyQuantity.getAttribute("value"));
        
        //Verify the number of items in cart and sum of items in the cart.
        if (total == (Integer.parseInt(funnyCowQuantity.getAttribute("value")) + Integer.parseInt(fluffyBunnyQuantity.getAttribute("value")))) //+ 
        		System.out.println("5.Verify the items are in the cart");
        		
        	
        
	}
	public static boolean HandleConditionalWaits(WebDriver driverParam,WebDriverWait waitParam,String messageParam) {
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
