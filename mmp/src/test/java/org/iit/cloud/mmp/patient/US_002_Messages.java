package org.iit.cloud.mmp.patient;
import org.iit.cloud.mmp.AppLibrary;
import org.iit.cloud.mmp.BaseClass;
import org.iit.cloud.mmp.HelperClass;
import org.iit.cloud.mmp.adminmodule.pages.SendMessagesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
//ctrl+shift+o to organize imports
public class US_002_Messages extends BaseClass{

	/*
	 * 1. Login to Patient module
		2. Send the messages
	 */
	HelperClass helperObj;
	
	@Test(description="TC_002 Validate the SendMEssges functionality in Patient Module")
	public void validateMessage()
	{
		helperObj = new HelperClass(driver);
		helperObj.launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		//HelperClass helperObj = new HelperClass(driver);

		helperObj.login("ria1","Ria12345");

		helperObj.navigateToAmodule("Messages"); 	 
		String reason= "Reason" + AppLibrary.generateRandomText();
		String subject= "Subject"+ AppLibrary.generateRandomText(); 
		SendMessagesPage sendMsgObj = new SendMessagesPage(driver);
		String actual = sendMsgObj.sendMessage(reason,subject);
		
		String expected="Messages Successfully sent.";
		Assert.assertEquals(actual,expected);
	}
	 
	 
}

