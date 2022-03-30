/*Write a logic in such a way that the data that is displayed 
in the patient portal homepage first row is matching with 
the data what you enter in the shedule appointment page
*/



import java.text.SimpleDateFormat;
//import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SheduleAppointment1 {
@Test
public void validateLogin() throws InterruptedException 
{
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
	driver.findElement(By.id("username")).sendKeys("ria1");
	driver.findElement(By.id("password")).sendKeys("Ria12345");
	driver.findElement(By.name("submit")).click();
	
	driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
	driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
	String doctorName= "Alexander";
	driver.findElement(By.xpath("//h4[normalize-space()='Dr."+doctorName +"']/ancestor::ul/following-sibling::button")).click();
	
	HashMap<String,String> expectedhMap = new HashMap<String,String>();
	expectedhMap.put("doctorName",doctorName);
	driver.switchTo().frame("myframe");
	driver.findElement(By.id("datepicker")).click();
	
	

	String expectedDate = getFutureDate(20,"dd/MMMM/yyyy");
	
	expectedhMap.put("dateofAppointment",getFutureDate(20,"MM/dd/yyyy"));
	
	String expectedDateArr[] = expectedDate.split("/");
	String expectedDt = expectedDateArr[0];
	String expectedMonth = expectedDateArr[1];
	String expectedYear = expectedDateArr[2];
	
	String actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
	while(!(expectedYear.equals(actualYear)))
	{
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
	}
	
	String actualMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
	while(!(expectedMonth.equals(actualMonth)))
	{
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
	}
	driver.findElement(By.linkText(expectedDt)).click();
	Select timeSelect = new Select(driver.findElement(By.id("time")));
	timeSelect.selectByVisibleText("11Am");
	expectedhMap.put("time","11Am");
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	Thread.sleep(2000);
	driver.findElement(By.id("ChangeHeatName")).click();
	
	String randomSymText = generateRandomText();
	driver.findElement(By.id("sym")).sendKeys(randomSymText);
	expectedhMap.put("sym",randomSymText);
	driver.findElement(By.xpath("//input[@value='Submit']")).click();
	
	
	//Read the value from patient portal table
	HashMap<String,String> actualhMap = new HashMap<String,String>();
	
	String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText().trim();
	actualhMap.put("dateofAppointment",actualDate);
	
	String actualTime = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText().trim();
	actualhMap.put("time",actualTime);
	
	String actualSym = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText().trim();
	actualhMap.put("sym",actualSym);
	String actualDoctor = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText().trim();
	actualhMap.put("doctorName",actualDoctor);
	
	Assert.assertTrue(expectedhMap.equals(actualhMap));

}
public String generateRandomText()
{
	Random r1 = new Random();
	String symptomText = "MMP Automation Team "+ r1.nextInt(1000);
	return symptomText;
	
}
public static String getFutureDate(int days,String format)
{
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, days);
	SimpleDateFormat  sdf = new SimpleDateFormat(format);
	return sdf.format(cal.getTime());
	
}

}
