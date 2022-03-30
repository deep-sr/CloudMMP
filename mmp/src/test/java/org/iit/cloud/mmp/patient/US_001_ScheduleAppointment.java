package org.iit.cloud.mmp.patient;
import org.iit.cloud.mmp.AppLibrary;
import org.iit.cloud.mmp.BaseClass;
import org.iit.cloud.mmp.HelperClass;
import org.iit.cloud.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class US_001_ScheduleAppointment extends BaseClass {
	
	HelperClass helperObj;
	
	@Test
	public void validateScheduleAppointment()
	{

		helperObj = new HelperClass(driver);
		helperObj.launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObj.login("ria1","Ria12345"); 
		helperObj.navigateToAmodule("Schedule Appointment"); 	 
		String randomSymText = AppLibrary.generateRandomText();
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		boolean result = sPage.bookAppointment("Beth",15,"10Am",randomSymText);
		Assert.assertTrue(result);
		
	}
	@Test
	public void validateScheduleAppointment1()
	{
		helperObj.launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObj.login("ria1","Ria12345"); 
		helperObj.navigateToAmodule("Schedule Appointment"); 	 
		String randomSymText = AppLibrary.generateRandomText();
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		boolean result = sPage.bookAppointment("Charlie",15,"10Am",randomSymText);
		Assert.assertTrue(result);
		
	}
	 
	 
}

