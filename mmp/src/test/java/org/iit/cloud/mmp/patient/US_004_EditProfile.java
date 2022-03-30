package org.iit.cloud.mmp.patient;


	import java.util.HashMap;

	import org.iit.cloud.mmp.AppLibrary;
	import org.iit.cloud.mmp.BaseClass;
	import org.iit.cloud.mmp.HelperClass;
	import org.iit.cloud.mmp.adminmodule.pages.SendMessagesPage;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	
	public class US_004_EditProfile extends BaseClass {
	
		HelperClass helperObj;
		@Test(description="TC_004 Validate the Edit Profile functionality in Patient Module")
		public void validateMessage()
		{
			helperObj = new HelperClass(driver);
			helperObj.launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");	 
			helperObj.login("ria1","Ria12345");
			helperObj.navigateToAmodule("Profile"); 	
}
		
		public void EditPatientProfile(String userName,String Password) {
			instantiateDriver();
			helperObj= new HelperClass(driver);
		}
	}
