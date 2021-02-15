package com.ucf.pcte;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.kms.katalon.util.CryptoUtil;




public class Webui {
	private WebDriver driver;
	static Webui open;
	
	public static void openBrowser(String nothing)
	{
		open = new Webui();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		open.driver = new ChromeDriver(options);
	    open.driver.manage().window().maximize();
	    open.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public static void navigateToUrl(String url)
	{
		open.driver.navigate().to(url);
	}
	
	public static void setText(String xpath, String text)
	{
		WebElement element = open.driver.findElement(By.xpath(xpath));
		element.sendKeys(text);
		
	}
	
	public static void setEncryptedText(String xpath, String text)
	{
		WebElement element = open.driver.findElement(By.xpath(xpath));
		String rawText = "couldn't do the thing";
		CryptoUtil.CrytoInfo cryptoInfo = CryptoUtil.getDefault(text);
		try {
			 rawText = CryptoUtil.decode(cryptoInfo);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		element.sendKeys(rawText);
		
	}
	
	public static void click(String xpath)
	{
		WebElement element = open.driver.findElement(By.xpath(xpath));
		element.click();
		
	}
	
	public static void verifyElementText(String xpath, String text)
	{
		WebElement element = open.driver.findElement(By.xpath(xpath));
		if(!element.getText().equals(text))
		{
			Assert.fail();
		}
		
	}
	
	public static void verifyElementNotPresent(String xpath, int time)
	{
	    try
	    {
	    	open.driver.wait(time*5000);
	    	WebElement shouldNotFind = open.driver.findElement(By.xpath(xpath));
	    	System.out.println("**" + shouldNotFind + "**");
	    	if(shouldNotFind != null)
	    		Assert.fail();
	    }catch (Exception e)
	    {
	    	System.out.println("Not found. We can continue.");
	    }
		
	}
	
	public static void verifyElementPresent(String xpath, int time)
	{
	    try
	    {
	    	open.driver.findElement(By.xpath(xpath));
	    }catch (Exception e)
	    {
	    	Assert.fail();
	    }
		
	}
	public static void closeBrowser()
	{
		open.driver.close();
		open.driver.quit();
	}
}
