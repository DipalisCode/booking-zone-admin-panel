package com.bookingzone.admin.createplans.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzone.admin.pages.BaseClass;
import com.bookingzone.admin.pages.BusinessHomePage;
import com.bookingzone.admin.pages.CreateBUY_NOWServicePlanPage;
import com.bookingzone.admin.pages.CreatePackageSessionPlan;
import com.bookingzone.admin.pages.CreatePackageTimePlan;
import com.bookingzone.admin.pages.CreatePerEventPlan;
import com.bookingzone.admin.pages.CreatePerHourSlotPlan;
import com.bookingzone.admin.pages.OutletDetailsPage;
import com.bookingzone.admin.pages.OutletListPage;
import com.bookingzone.admin.utils.CreatePlansCommonAction;
import com.bookingzone.admin.utils.ExtentReportManager;
import com.bookingzone.admin.utils.UtilityClass;
// Class to create Per Hour Slot Plan
public class TC006_CreatePerHourSlotPlan extends BaseClass
{
	BusinessHomePage businessHomePage;
	OutletListPage outletList;
	OutletDetailsPage outletDetails;
	CreateBUY_NOWServicePlanPage createBUY_NOWServicePlan;
	CreatePackageSessionPlan pkgSession;
	CreatePackageTimePlan pkgTime;
	CreatePerEventPlan perEvent;
	CreatePerHourSlotPlan perHourSlot;
	CreatePlansCommonAction createPlansCommonAction;

	// Test data variables
	private String planName;
	private String imgPath;
	private String minTimeInAdvanced;
	private String maxTimeInAdvanced;
	private String from;
	private String to;
	private String maxEventAllowed;
	private String minGuestAllowed;
	private String maxGuestPerSlot;
	private String maxGuestsAllowed;
	private String purchaseItemsName;
	private String purchaseItemsprice;
	private String purchaseItemSaleTax;
	private String purchaseItemTimeFrame;
	private String startHourSlot1;
	private String startMinuteSlot1;
	private String startAmpmSlot1;
	private String endHourSlot1;
	private String endMinuteSlot1;
	private String endAmpmSlot1;
	private String refundType;
	private String refundAmount;
	private String processingFeeName;
	private String processingFeeAmount;
	private String guestPriceSlot1;
	private String slotNo1;

	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		businessHomePage = new BusinessHomePage(driver);
		outletList = new OutletListPage(driver);
		outletDetails = new OutletDetailsPage(driver);
		createBUY_NOWServicePlan=new CreateBUY_NOWServicePlanPage(driver);
		pkgSession=new CreatePackageSessionPlan(driver);
		pkgTime=new CreatePackageTimePlan(driver);
		perEvent=new CreatePerEventPlan(driver);
		perHourSlot=new CreatePerHourSlotPlan(driver);
		createPlansCommonAction=new CreatePlansCommonAction(businessHomePage, outletList, outletDetails, createBUY_NOWServicePlan, pkgSession, pkgTime, perEvent);

		fetchTestData();
	}

	private void fetchTestData() throws IOException 
	{
		planName=UtilityClass.getDataFromEs(6, "Plan Name", "Create_Plans");
		imgPath=UtilityClass.getDataFromEs(6, "URL /Path of Img", "Create_Plans");
		minTimeInAdvanced=UtilityClass.getDataFromEs(6,"MinTimeInAdvanced", "Create_Plans");
		maxTimeInAdvanced=UtilityClass.getDataFromEs(6,"MaxTimeInAdvanced", "Create_Plans");
		from=UtilityClass.getDataFromEs(6,"From", "Create_Plans");
		to=UtilityClass.getDataFromEs(6,"To", "Create_Plans");
		maxEventAllowed=UtilityClass.getDataFromEs(6, "MaxPackages/Event Allowed", "Create_Plans");
		minGuestAllowed=UtilityClass.getDataFromEs(6, "MinGuestAllowed", "Create_Plans");
		maxGuestPerSlot=UtilityClass.getDataFromEs(6, "MaxGuestPerSlot", "Create_Plans");
		maxGuestsAllowed=UtilityClass.getDataFromEs(6, "MaxGuestsAllowed", "Create_Plans");

		purchaseItemsName=UtilityClass.getDataFromEs(6,"PurchaseItemsName", "Create_Plans");
		purchaseItemsprice=UtilityClass.getDataFromEs(6,"PurchaseItemsprice", "Create_Plans");
		purchaseItemSaleTax=UtilityClass.getDataFromEs(6,"PurchaseItemSaleTax", "Create_Plans");
		purchaseItemTimeFrame=UtilityClass.getDataFromEs(6,"PurchaseItemTimeFrame", "Create_Plans");
		//Slot1
		startHourSlot1=UtilityClass.getDataFromEs(6, "Slot1StartTimehour", "Create_Plans");	
		startMinuteSlot1=UtilityClass.getDataFromEs(6, "StartMinute", "Create_Plans");
		startAmpmSlot1=UtilityClass.getDataFromEs(6, "StartTimeAMPM", "Create_Plans");
		endHourSlot1=UtilityClass.getDataFromEs(6, "Slot1EndTimehour", "Create_Plans");	
		endMinuteSlot1=UtilityClass.getDataFromEs(6, "EndMinute", "Create_Plans");
		endAmpmSlot1=UtilityClass.getDataFromEs(6, "EndTimeAMPM", "Create_Plans");
		guestPriceSlot1=UtilityClass.getDataFromEs(6, "PackageSlot1/Person/guest/slot/Event  price", "Create_Plans");
		refundType=UtilityClass.getDataFromEs(6, "Refund Type", "Create_Plans");
		refundAmount=UtilityClass.getDataFromEs(6, "Refund Amount", "Create_Plans");
		processingFeeName=UtilityClass.getDataFromEs(6, "ProcessingFeeName", "Create_Plans");
		processingFeeAmount=UtilityClass.getDataFromEs(6, "ProcessingFeeAmount", "Create_Plans");
		slotNo1=UtilityClass.getDataFromEs(6, "SlotNo1", "Create_Plans");

	}


	// create Per Hour Slot plan
	@Test (testName = "testCreatePerHourSlotPlan")
	public void testCreatePerHourSlotPlan() throws IOException, InterruptedException 
	{
		try
		{ 
			createPlansCommonAction.enterNameandUploadImage(planName, imgPath);

			createPlansCommonAction.configurePlanDetails(minTimeInAdvanced, maxTimeInAdvanced, from, to);

			pkgSession.clickPricePerSlotPerHourCheckBox();
			perHourSlot.clickSlotRadiobtn();
			pkgTime.selectBookingDuration();

			createPlansCommonAction.enterGuestDetailsForEventPlan(maxEventAllowed, minGuestAllowed, maxGuestPerSlot, maxGuestsAllowed, false);
			createPlansCommonAction.enterSaleItemDetails(purchaseItemsName, purchaseItemsprice, purchaseItemSaleTax, purchaseItemTimeFrame);
			createBUY_NOWServicePlan.selectSlotTime(slotNo1, startHourSlot1, startMinuteSlot1, startAmpmSlot1, endHourSlot1, endMinuteSlot1, endAmpmSlot1);//slot1
			pkgTime.selectSlot1TimeIncrement(3);
			perHourSlot.enterGuestPriceSlot1(guestPriceSlot1);

			createPlansCommonAction.enterRefundDetails(refundType, refundAmount);
			createPlansCommonAction.enterProcessingFees(processingFeeName, processingFeeAmount);
			createPlansCommonAction.finalizePlanCreation();
			validatePlanCreation();

		}

		catch(Exception e) 
		{
			handleException(e);

		}

	}
	// Method to validate outlet creation
	private void validatePlanCreation() {
		String pageSource = driver.getPageSource();

		if (pageSource.contains("Service item has been successfully saved..!")) {
			ExtentReportManager.logPass("Per Hour Slot plan created successfully...!");
		} else {
			String message = driver.getPageSource();
			checkFailureMessages(message);
		}
	}

	// Method to check for specific failure messages
	private void checkFailureMessages(String pageSource) {
		if(pageSource.contains("Title is required"))
		{
			handleFailure("Tital is required. Please provide a valid tital.");
		}
		else if(pageSource.contains("Days is required"))
		{
			handleFailure("Days is required is required. Please provide a Days.");
		}
		else if(pageSource.contains("Required"))
		{
			handleFailure("Some feilds are required. Please provide a required feilds.");
		}
		else if(pageSource.contains("Atleast 1 booking frequency is required."))
		{
			handleFailure("Atleast 1 booking frequency is required.");
		}
		else if(pageSource.contains("Duration is required"))
		{
			handleFailure("Duration is required. Please provide a duration.");
		}
		else if(pageSource.contains("Max Guest is required"))
		{
			handleFailure("Max Guest is required. Please provide a max guest count.");
		}	
		else if(pageSource.contains("Time Inrcement is required"))
		{
			handleFailure("Time Inrcement is required. Please provide a time increment.");
		}
		else if(pageSource.contains("Refund Amount is required"))
		{
			handleFailure("Refund Amount is required. Please provide a refund amount.");
		}
		else if(pageSource.contains("Fee Name is required"))
		{
			handleFailure("Fee Name is required. Please provide a fee name.");
		}
	}
	/**
	 * Method to handle test failures.
	 * 
	 * @param message The failure message
	 */
	private void handleFailure(String message) {
		ExtentReportManager.logFail("Per Hour Slot plan is not created due to " + message + "...!");
		System.out.println("Per Hour Slot plan is not created due to " + message + "...!");
		Assert.fail(message);
	}
	// Method to handle exceptions and log error messages
	public void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("issue cause is :"+getCause);
		ExtentReportManager.logFail("testCreatePerHourSlotPlan test case is fail due to" + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
