package Selenium;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenApproach {
  
		
		WebDriver driver = null;
		
		@BeforeTest
		public void launchBrowser() {
			 
			
			String browserName = "firefox";
			
			if(browserName.toLowerCase().trim().equalsIgnoreCase("chrome")) {
				
				 driver =new ChromeDriver();
			}
			else if(browserName.toLowerCase().trim().equalsIgnoreCase("firefox")){
				 driver = new FirefoxDriver();			
			}
			else if(browserName.toLowerCase().trim().equalsIgnoreCase("edge")){
				 driver = new EdgeDriver();			
			}
			else {
				System.out.println("Pass correct browser name");
			}
			
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
			
		}
		
		
		@DataProvider
		public Object[][] multipleLogins(){
			
		return new Object[][] {
			
			{ "user1@test.com","Test@123" },
			{ "user2@test.com","Test@123" },
			{ "user3@test.com","Test@123" },
			{ "user4@test.com","Test@123" },
			{ "user5@test.com","Test@123" },

			
		};
			
		}
		
	  @Test(priority = 1, invocationCount = 2)
	  public void validloginTest() throws InterruptedException {
		  
		    driver.findElement(By.xpath("(//a[text()='Login'])[2]")).click();
		    driver.findElement(By.xpath("//input[@name='email']")).sendKeys("testuser1@gmail.com");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//a[text()='Logout'])[2]")).click();
			Thread.sleep(2000);

			
	  }
	  
	  @Test(priority=0,dataProvider = "multipleLogins")
	  public void invalidLoginTest(String un, String pw) throws InterruptedException {
			  
			    driver.findElement(By.xpath("(//a[text()='Login'])[2]")).click();
			    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(un);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				Thread.sleep(3000);
				String validationMsg= driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
				assertTrue(true, validationMsg);
	  }
	  
	  
	  
	  @AfterTest
	  public void browserclose() {
		  driver.close();
	  }
	  
	}


