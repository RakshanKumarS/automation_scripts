package com.cognizant.businessComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Testing {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\cognizant-intelligent-test-scripter-1.2\\lib\\Drivers\\chromedriver.exe");
		 
		// Initialize browser
		WebDriver driver=new ChromeDriver();
		 
		// Open facebook
		driver.get("http://t3.qa3.tms.toyota.com/");
		 
		// Maximize browser
		 
		driver.manage().window().maximize();

		Select fruits = new Select(driver.findElement(By.id("userType")));
		fruits.selectByValue("tms");
		
		driver.findElement(By.id("username")).sendKeys("T3_TESTUSER_002");
		
		driver.findElement(By.id("password")).sendKeys("Toyota@123");
		
		driver.findElement(By.id("terms")).click();
		
		driver.findElement(By.id("submit")).click();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[contains(text(),'TIS')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(),'Diagnostics')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(),'Telematics')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(),'Toyota Bluetooth® Compatibility Information')]")).click();
		Thread.sleep(4000);
		
		
	
	}


}
