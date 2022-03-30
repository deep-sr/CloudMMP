package org.iit.cloud.mmp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperClass {
	WebDriver driver;
	public HelperClass(WebDriver driver)
	{
		this.driver = driver;
		
	}
	public void login(String username,String password)
	{
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Sign In']")).click();
	}

public void launchBrowser(String url)
{
	driver.get(url);
}
public void navigateToAmodule(String moduleName)
{
	driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();

}
}