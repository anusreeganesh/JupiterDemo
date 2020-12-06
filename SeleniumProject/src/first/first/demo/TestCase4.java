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

public class TestCase4 {
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
	  @Test(description = "This method validates the shopping cart items - Test case 4: ")
	  public void shoppingCartItemsValidation() {
		  	//Capture the nav tag
	        //Capture the "Shop" hyperlink 
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
	        
	        try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        //Capture the "Cart" hyperlink 
	        navList = driver.findElement(By.className(JupiterConstants.Navigation).partialLinkText(JupiterConstants.Cart));
	        navList.click();
	        System.out.println("4.Click the cart menu");
	  }


	  @AfterMethod
	  public void validateCartItems() {
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
