package org.iit.cloud.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.cloud.mmp.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {

		WebDriver driver;
		public ScheduleAppointmentPage(WebDriver driver)
		{
			this.driver= driver;
		}
		public boolean bookAppointment(String doctorName,int noofDays,String time,String sym)
		{


			driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();


			driver.findElement(By.xpath("//h4[normalize-space()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();

			HashMap<String,String> expectedhMap = new HashMap<String,String>();
			expectedhMap.put("doctorName",doctorName);


			driver.switchTo().frame("myframe");
			driver.findElement(By.id("datepicker")).click();



			String expectedDate = AppLibrary.getFutureDate(noofDays,"dd/MMMM/yyyy");
			expectedhMap.put("dateofAppointment", AppLibrary.getFutureDate(15,"MM/dd/yyyy"));

			String expectedDateArr[] = expectedDate.split("/");
			String expectedDt = expectedDateArr[0];
			String expectedMonth = expectedDateArr[1]; 
			String expectedYear = expectedDateArr[2] ;


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
				actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			}

			driver.findElement(By.linkText(expectedDt)).click();

			Select timeSelect = new Select(driver.findElement(By.id("time")));
			timeSelect.selectByVisibleText(time);
			expectedhMap.put("time", time);

			driver.findElement(By.id("ChangeHeatName")).click();


			driver.findElement(By.id("sym")).sendKeys(sym);
			expectedhMap.put("sym", sym);

			driver.findElement(By.xpath("//input[@value='Submit']")).click();


			//Read the values from the Patient Portal table
			HashMap<String,String> actualhMap = new HashMap<String,String>();
			String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText().trim();
			actualhMap.put("dateofAppointment", actualDate);
			String actualtime= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText().trim();
			actualhMap.put("time", actualtime);
			String actualSym= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText().trim();
			actualhMap.put("sym", actualSym);
			String actualDoctor= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText().trim();
			actualhMap.put("doctorName", actualDoctor);

			return expectedhMap.equals(actualhMap) ;

		}

	}

