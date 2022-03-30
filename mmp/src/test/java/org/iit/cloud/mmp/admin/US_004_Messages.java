package org.iit.cloud.mmp.admin;
import java.util.HashMap;

import org.iit.cloud.mmp.AppLibrary;
import org.iit.cloud.mmp.BaseClass;
import org.iit.cloud.mmp.HelperClass;
import org.iit.cloud.mmp.adminmodule.pages.SendMessagesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class US_004_Messages extends BaseClass {

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
		helperObj.login("ria1","Ria12345");
		helperObj.navigateToAmodule("Messages"); 	 
		
		String reason= "Reason" + AppLibrary.generateRandomText();
		String subject= "Subject"+ AppLibrary.generateRandomText(); 
		SendMessagesPage sendMsgObj = new SendMessagesPage(driver);
		
		sendMsgObj.sendMessage(reason,subject);
	 
		String patientName = sendMsgObj.fetchPatientName();
		String currentDate=  AppLibrary.getFutureDate(0,"dd-MM-yyyy");
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		expectedHMap.put("reason", reason);
		expectedHMap.put("subject", subject);
		expectedHMap.put("patientName", patientName);
		expectedHMap.put("date", currentDate);
		
		helperObj.launchBrowser("http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");
	    helperObj.login("Ben@123","Frank@123");
		helperObj.navigateToAmodule("Messages"); 	 
		 
		HashMap<String,String> actualHMap = sendMsgObj.fetchMessageDetails(patientName,reason,subject,currentDate);
		
		Assert.assertEquals(actualHMap, expectedHMap);
		
	}
	 
}
