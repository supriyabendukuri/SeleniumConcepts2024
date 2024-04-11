package Selenium;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginModule {
	
public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("testuser1@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(3000);
		driver.close();

	}

}
