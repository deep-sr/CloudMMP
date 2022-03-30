package org.iit.cloud.mmp.adminmodule.pages;

import java.util.HashMap;

import org.iit.cloud.mmp.HelperClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SendMessagesPage {

	WebDriver driver;
	public SendMessagesPage(WebDriver driver)
	{
		this.driver= driver;
	}
	public HashMap<String,String> fetchMessageDetails(String patientName,String reason,String subject,String currentDate )
	{

		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("patientName", driver.findElement(By.xpath("(//b[contains(text(),'"+patientName+"')])[1]")).getText());
		actualHMap.put("reason", 	driver.findElement(By.xpath("(//b[contains(text(),'"+reason+"')])[1]")).getText());
		actualHMap.put("subject", driver.findElement(By.xpath("//td[normalize-space()='"+subject+"']")).getText());
		actualHMap.put("date", driver.findElement(By.xpath("(//b[contains(text(),'"+currentDate+"')])[1]")).getText());
		 return actualHMap; 
	}
	public String fetchPatientName()
	{
		HelperClass helperObj = new HelperClass(driver);
		helperObj.navigateToAmodule("Profile");
		String patientName = driver.findElement(By.id("fname")).getAttribute("value");
		return patientName;
		
	}
	public String sendMessage(String reason,String subject)
	{
		driver.findElement(By.id("subject")).sendKeys(reason);
		driver.findElement(By.name("message")).sendKeys(subject);
		driver.findElement(By.xpath("//input[@value='Send']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		return text;
	}
	 
	 
}
