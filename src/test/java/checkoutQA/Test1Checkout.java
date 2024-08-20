package checkoutQA;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test1Checkout 
{

	
	public static void main(String[] args) throws InterruptedException 
	

		
	
	{
			WebDriver driver = new ChromeDriver();
			
			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			driver.get("https://skin-seoul.com/");

			driver.findElement(By.xpath("//div[@role='dialog']//div[1]/div[1]/div[1]/div[1]/div[1]/button")).click();

			driver.findElement(By.xpath("//a[@aria-label='Select options for “Full Fit Propolis Light Ampoule”']//img[@alt='Add to cart']")).click();

			driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div[3]/div/div/div/div/div[1]/div/div[2]/div[3]/a[2]")).click();

			driver.findElement(By.id("bftCountryCurrencySelectorLink")).click();

			WebElement PRT = driver.findElement(By.id("bft_shipping_country"));

			Select s = new Select(PRT);

			// s.selectByValue("AU");
			// s.selectByValue("MY");
          	// s.selectByValue("US");
			 s.selectByValue("SG");

			driver.findElement(By.xpath("//button[text()='Save']")).click();

			Thread.sleep(6000);

			driver.findElement(By.id("billing_email")).sendKeys("mahalkargaju@85gmail.com");

			driver.findElement(By.name("billing_first_name")).sendKeys("Gajanan");

			driver.findElement(By.id("billing_last_name")).sendKeys("Mahalkar");

			driver.findElement(By.name("billing_address_1")).sendKeys("49 Walter Crescent");
			Thread.sleep(2000);

			driver.findElement(By.id("billing_city")).sendKeys("Tengah");
			
			
			// For state
		
			 WebElement stateDropdown = driver.findElement(By.xpath("//span[@id='select2-billing_state-container']"));

			WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(5000));
			
			wait.until(ExpectedConditions.visibilityOf(stateDropdown));
			stateDropdown.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			List<WebElement> allOptions = driver.findElements(By.cssSelector(".select2-results__options>li"));
			 
			 for(WebElement option:allOptions) {
				 try {
				        // Wait for the element to be stable (visible and not moving)
				        new WebDriverWait(driver, Duration.ofSeconds(5))
				            .until(ExpectedConditions.visibilityOf(option));

				        // Now, attempt to get the text
				        String text = option.getText();
				        System.out.println(text);
				        
				        if (text.equals("California")) {
				            new WebDriverWait(driver, Duration.ofSeconds(5))
				                .until(ExpectedConditions.elementToBeClickable(option)).click();
				            break;
				        }
				    } catch (StaleElementReferenceException e) {
				        System.out.println("Stale Element Exception caught. Refetching elements...");
				        allOptions = driver.findElements(By.cssSelector(".select2-results__options>li"));
				    }
			}  
			 
			Thread.sleep(3000);   // State close
		
		
		
			WebElement zipcode=driver.findElement(By.xpath("//input[@id='billing_postcode']"));
		//	js.executeScript("arguments[0].scrollIntoView(true);", zipcode);
			zipcode.sendKeys("59837");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@id='billing_phone']")).sendKeys("7507507669");

			Thread.sleep(2000);

			// Scrolling

			JavascriptExecutor ps = (JavascriptExecutor) driver;
     		ps.executeScript("window.scrollBy(0,2000)");

			// For Stripe  
     	
			
			  driver.findElement(By.id("payment_method_stripe_cc")).click();
			  
			  Thread.sleep(3000);
			  
			  WebElement iframeElement =driver.findElement(By.xpath("//*[@id=\"wc-stripe-card-element\"]/div/iframe")); 
			  driver.switchTo().frame(iframeElement);
			 
			 Thread.sleep(5000);
		  
			  driver.findElement(By.xpath("//*[@id=\"Field-numberInput\"]")).sendKeys("4242424242424242"); Thread.sleep(5000);
			  
			  
			  driver.findElement(By.xpath("//*[@id=\"Field-expiryInput\"]")).sendKeys("0226"); Thread.sleep(6000);
			  
			  driver.findElement(By.xpath("//*[@id=\"Field-cvcInput\"]")).sendKeys("123");
			  
			  Thread.sleep(5000);
			  
			  // Scrolling
		 
		//	  js.executeScript("window.scrollBy(0,700)");
			  
			  driver.switchTo().defaultContent();
			   
			  
			  driver.findElement(By.id("place_order")).click();
			  
	
			// For PayPal

		//	 driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div/form/div/div[2]/div/div[2]/div/div[3]/div/div/iframe[1]")).click();
			 
		}
}
