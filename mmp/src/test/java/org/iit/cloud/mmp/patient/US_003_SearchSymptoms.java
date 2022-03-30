package org.iit.cloud.mmp.patient;


import java.time.Duration;

import org.iit.cloud.mmp.BaseClass;
import org.iit.cloud.mmp.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class US_003_SearchSymptoms extends BaseClass {

 
	@Test(description="TC_001_search_symptoms")
	public void search_symptoms()
	{

		launchbrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		HelperClass HelperClassobj=new HelperClass(driver);
		HelperClassobj.login("ria1","Ria12345");
		navigateToAmodule("Search Symptoms");  
		boolean result=  SearchSymptoms("cold");
		Assert.assertTrue(result);

	} 
	boolean SearchSymptoms(String symp)
	{

		driver.findElement(By.xpath("//input[@id='search']")).sendKeys(symp);
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(60));
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[contains(text(),'"+symp+"')])[1]")));
		String text= e.getText();  
		return symp.equals(text) ;                            


	}
	@BeforeTest
	public void initdriver() 
	{

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	/*@AfterTest
	 public void tearDown()
	 {
	 driver.quit();
	 }*/

	public void launchbrowser(String url) 
	{
		driver.get(url);

	}


	public void navigateToAmodule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();

	}


}

/*
 * import java.time.Duration;
 * 
 * import org.iit.cloud.mmp1.BaseClass; import org.iit.cloud.mmp1.HelperClass;
 * import org.openqa.selenium.By; import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.WebElement; import
 * org.openqa.selenium.chrome.ChromeDriver; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.WebDriverWait; import org.testng.Assert;
 * import org.testng.annotations.BeforeTest; import org.testng.annotations.Test;
 * 
 * import io.github.bonigarcia.wdm.WebDriverManager;
 * 
 * public class US_003_SearchSymptoms extends BaseClass {
 * 
 * HelperClass helperObj;
 * 
 * @Test(description =
 * "TC_003 Validate Search Symptoms functionality in Patient Module") public
 * void searchSymptoms() {
 * 
 * helperObj = new HelperClass(driver); helperObj.launchBrowser(
 * "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php")
 * ; helperObj.login("ria1","Ria12345");
 * helperObj.navigateToAmodule("Search Symptoms"); boolean results =
 * SearchSymptoms("cold"); Assert.assertTrue(results);
 * 
 * 
 * 
 * } boolean SearchSymptoms(String symp) {
 * driver.findElement(By.id("//input[@id='search']")).sendKeys(symp);
 * driver.findElement(By.name("//input[@name='submit']")).click(); WebDriverWait
 * wait = new WebDriverWait(driver, Duration.ofSeconds(10)); WebElement
 * e=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
 * "(//td[contains(text(),'"+symp+"')])[1]"))); String text =e.getText(); return
 * symp.equals(text); }
 * 
 * @BeforeTest public void instantiateDriver() {
 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
 * ChromeDriver(); driver.manage().window().maximize(); } public void
 * launchBrowser(String url) { driver.get(url); } public void
 * navigateToAmodule(String moduleName) {
 * 
 * } }
 */