package com.task11;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Que2framesconcept {

	   protected static String url="http://the-internet.herokuapp.com/nested_frames";
	   WebDriver driver;

@BeforeSuite 
public void startchromebrowser() {
	 WebDriverManager.chromedriver().setup();
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
}	

@BeforeClass
public void openurl() {
	  driver.get(url);
}

@Test
public void frameshandling() throws InterruptedException, IOException {
	WebElement topframe=driver.findElement(By.xpath("//frameset//frame[@name='frame-top']"));
	driver.switchTo().frame(topframe);
	List<WebElement>frameelement=driver.findElements(By.tagName("frame"));
	System.out.println("total frames "+frameelement.size());
	
	if(frameelement.size()==3) {
		System.out.println("there are 3 frames in top frame");	
	}else {
		System.out.println("there are no 3 frames in top frame");
	}
	
	WebElement leftframe=driver.findElement(By.xpath("//frameset//frame[@name=\"frame-left\"]"));
	driver.switchTo().frame(leftframe);
	WebElement leftframetext=driver.findElement(By.xpath("//body[contains(.,'LEFT')]"));
	Assert.assertEquals(leftframetext.getText(), "LEFT");
	System.out.println("left frame text:"+leftframetext.getText());
	
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame(topframe);
	
	WebElement middleframe=driver.findElement(By.xpath("//frame[@name='frame-middle']"));
	driver.switchTo().frame(middleframe);
	WebElement middleframetext=driver.findElement(By.xpath("//body[contains(.,'MIDDLE')]"));
	Assert.assertEquals(middleframetext.getText(), "MIDDLE");
	System.out.println("middle frame text:"+middleframetext.getText());
	
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame(topframe);
	
	WebElement rightframe=driver.findElement(By.xpath("//frame[@name='frame-right']"));
	driver.switchTo().frame(rightframe);
	WebElement rightframetext=driver.findElement(By.xpath("//body[contains(.,'RIGHT')]"));
	Assert.assertEquals(rightframetext.getText(), "RIGHT");
	System.out.println("right frame text:"+rightframetext.getText());
	
	driver.switchTo().defaultContent();
			
	WebElement bottomframe=driver.findElement(By.xpath("//frame[@name='frame-bottom']"));
	driver.switchTo().frame(bottomframe);
	WebElement bottomframetext=driver.findElement(By.xpath("//body[contains(.,'BOTTOM')]"));
	Assert.assertEquals(bottomframetext.getText(), "BOTTOM");
	System.out.println("bottom frame text:"+bottomframetext.getText());
	
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame(topframe);
	
	Thread.sleep(1000);	 
    File task11que2screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(task11que2screenshot,new File("seleniumjatconcept//task11que2screenshot.png"));	    
    System.out.println("screenshot captured successfully...");
					
}

@AfterSuite
public void closechromebrowser() {
	  driver.quit();
}
}
