package com.task11;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Que1windowsconcept {

	   
	   protected static String url="https://the-internet.herokuapp.com/windows";
	   WebDriver driver;

@BeforeSuite 
public void startchromebrowser() {
	 WebDriverManager.chromedriver().setup();
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 driver.manage().deleteAllCookies();
}	

@BeforeMethod
public void openurl() {
	  driver.get(url);
}

@Test
public void windowshandle() throws InterruptedException, IOException {
	driver.findElement(By.xpath("//a[text()='Click Here']")).click();
	
	//manage the window and switch over the windows
	Set<String> windowhandles=driver.getWindowHandles();
	List<String> listwindows=new ArrayList<>(windowhandles);
	
	//switch to window using index
	driver.switchTo().window(listwindows.get(1));
	String childtext=driver.findElement(By.xpath("//h3[.='New Window']")).getText();
	
	String actual="New Window";
	Assert.assertEquals(childtext, actual);
	
	if(childtext.equals(actual)) {
		System.out.println("the element is available in child window "+childtext);
	}else {
		System.out.println("the element is not available in child window "+childtext);
	}
	Thread.sleep(1000);
	
	driver.close();	
	
	driver.switchTo().window(listwindows.get(0));
	
	String originalactual="Opening a new window";
	String parenttext=driver.findElement(By.xpath("//h3[.='Opening a new window']")).getText();
	Assert.assertEquals(parenttext, originalactual);
	
	if(parenttext.equals(originalactual)) {
		System.out.println("the element is available in parent window "+parenttext);
	}else {
		System.out.println("the element is not available in parent window "+parenttext);
	}
	
	Thread.sleep(1000);
		 
    File task11que1screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(task11que1screenshot,new File("seleniumjatconcept//task11que1screenshot.png"));	    
    System.out.println("screenshot captured successfully...");
		
	driver.close();	
}

@AfterSuite
public void closechromebrowser() {
	  driver.quit();
}
}
