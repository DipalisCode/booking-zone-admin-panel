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
import com.bookingzone.admin.pages.OutletDetailsPage;
import com.bookingzone.admin.pages.OutletListPage;
import com.bookingzone.admin.utils.CreatePlansCommonAction;
import com.bookingzone.admin.utils.ExtentReportManager;
import com.bookingzone.admin.utils.UtilityClass;
//Class to create package time plan 
public class TC002_CreatePackageTimePlan extends BaseClass
{
	BusinessHomePage businessHomePage;
	OutletListPage outletList;
	OutletDetailsPage outletDetails;
	CreateBUY_NOWServicePlanPage createBUY_NOWServicePlan;
	CreatePackageSessionPlan pkgSession;
	CreatePackageTimePlan pkgTime;
	CreatePlansCommonAction createPlansCommonAction;


	// Test data variables
	private String planName;
	private String imgPath;
	private String minTimeInAdvanced;
	private String maxTimeInAdvanced;
	private String from;
	private String to;
	private String maxGuestPerSlot;
	private String guestPerPackage;
	private String maxPackages;
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
	private String startHourSlot2;
	private String startMinuteSlot2;
	private String startAmpmSlot2;
	private String endHourSlot2;
	private String endMinuteSlot2;
	private String endAmpmSlot2;
	private String refundType;
	private String refundAmount;
	private String processingFeeName;
	private String processingFeeAmount;
	private String packagePriceSlot1;
	private String additionalGuestPriceSlot1;
	private String packageSaleTaxSlot1;
	private String additionalGuestSaleTaxSlot1;
	private String packagePriceSlot2;
	private String additionalGuestPriceSlot2;
	private String packageSaleTaxSlot2;
	private String additionalGuestSaleTaxSlot2;
	private String slotNo1;
	private String slotNo2;

	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		businessHomePage = new BusinessHomePage(driver);
		outletList = new OutletListPage(driver);
		outletDetails = new OutletDetailsPage(driver);
		createBUY_NOWServicePlan=new CreateBUY_NOWServicePlanPage(driver);
		pkgSession=new CreatePackageSessionPlan(driver);
		pkgTime=new CreatePackageTimePlan(driver);
		createPlansCommonAction=new CreatePlansCommonAction(businessHomePage, outletList, outletDetails, createBUY_NOWServicePlan, pkgSession, pkgTime, null);

		fetchTestData();
	}

	private void fetchTestData() throws IOException 
	{
		planName=UtilityClass.getDataFromEs(2, "Plan Name", "Create_Plans");
		imgPath=UtilityClass.getDataFromEs(2, "URL /Path of Img", "Create_Plans");
		minTimeInAdvanced=UtilityClass.getDataFromEs(2,"MinTimeInAdvanced", "Create_Plans");
		maxTimeInAdvanced=UtilityClass.getDataFromEs(2,"MaxTimeInAdvanced", "Create_Plans");
		from=UtilityClass.getDataFromEs(2,"From", "Create_Plans");
		to=UtilityClass.getDataFromEs(2,"To", "Create_Plans");
		maxGuestPerSlot=UtilityClass.getDataFromEs(2,"MaxGuestPerSlot", "Create_Plans");
		guestPerPackage=UtilityClass.getDataFromEs(2,"GuestPerPackage", "Create_Plans");
		maxPackages=UtilityClass.getDataFromEs(2,"MaxPackages/Event Allowed", "Create_Plans");
		maxGuestsAllowed=UtilityClass.getDataFromEs(2,"MaxGuestsAllowed", "Create_Plans");
		purchaseItemsName=UtilityClass.getDataFromEs(2,"PurchaseItemsName", "Create_Plans");
		purchaseItemsprice=UtilityClass.getDataFromEs(2,"PurchaseItemsprice", "Create_Plans");
		purchaseItemSaleTax=UtilityClass.getDataFromEs(2,"PurchaseItemSaleTax", "Create_Plans");
		purchaseItemTimeFrame=UtilityClass.getDataFromEs(2,"PurchaseItemTimeFrame", "Create_Plans");
		//Slot1
		startHourSlot1=UtilityClass.getDataFromEs(2, "Slot1StartTimehour", "Create_Plans");	
		startMinuteSlot1=UtilityClass.getDataFromEs(2, "StartMinute", "Create_Plans");
		startAmpmSlot1=UtilityClass.getDataFromEs(2, "StartTimeAMPM", "Create_Plans");
		endHourSlot1=UtilityClass.getDataFromEs(2, "Slot1EndTimehour", "Create_Plans");	
		endMinuteSlot1=UtilityClass.getDataFromEs(2, "EndMinute", "Create_Plans");
		endAmpmSlot1=UtilityClass.getDataFromEs(2, "EndTimeAMPM", "Create_Plans");
		//Slot2
		startHourSlot2=UtilityClass.getDataFromEs(2, "Slot2StartTimehour", "Create_Plans");	
		startMinuteSlot2=UtilityClass.getDataFromEs(2, "StartMinute", "Create_Plans");
		startAmpmSlot2=UtilityClass.getDataFromEs(2, "StartTimeAMPM", "Create_Plans");
		endHourSlot2=UtilityClass.getDataFromEs(2, "Slot2EndTimehour", "Create_Plans");	
		endMinuteSlot2=UtilityClass.getDataFromEs(2, "EndMinute", "Create_Plans");
		endAmpmSlot2=UtilityClass.getDataFromEs(2, "EndTimeAMPM", "Create_Plans");
		//Slot price
		packagePriceSlot1=UtilityClass.getDataFromEs(2, "PackageSlot1/Person/guest/slot/Event  price", "Create_Plans");
		additionalGuestPriceSlot1=UtilityClass.getDataFromEs(2, "AdditionalGuestSlot1Price", "Create_Plans");
		packageSaleTaxSlot1=UtilityClass.getDataFromEs(2, "PackageSlot1/Person/guest/slot SaleTax", "Create_Plans");
		additionalGuestSaleTaxSlot1=UtilityClass.getDataFromEs(2, "AdditonalGuestSlot1SaleTax", "Create_Plans");
		packagePriceSlot2=UtilityClass.getDataFromEs(2, "PackageSlot2/Person/guest/slotPrice", "Create_Plans");
		additionalGuestPriceSlot2=UtilityClass.getDataFromEs(2, "AdditionalGuestSlot2Price", "Create_Plans");
		packageSaleTaxSlot2=UtilityClass.getDataFromEs(2, "packageSlot2/Person/guest/slot SaleTax", "Create_Plans");
		additionalGuestSaleTaxSlot2=UtilityClass.getDataFromEs(2, "AdditonalGuestSlot2SaleTax", "Create_Plans");

		refundType=UtilityClass.getDataFromEs(2, "Refund Type", "Create_Plans");
		refundAmount=UtilityClass.getDataFromEs(2, "Refund Amount", "Create_Plans");
		processingFeeName=UtilityClass.getDataFromEs(2, "ProcessingFeeName", "Create_Plans");
		processingFeeAmount=UtilityClass.getDataFromEs(2, "ProcessingFeeAmount", "Create_Plans");
		slotNo1=UtilityClass.getDataFromEs(2, "SlotNo1", "Create_Plans");;
		slotNo2=UtilityClass.getDataFromEs(2, "SlotNo2", "Create_Plans");

	}


	// create Package time plan
	@Test (testName = "testCreatePackageTimePlan")
	public void testCreatePackageTimePlan() throws IOException, InterruptedException 
	{
		try
		{ 
			createPlansCommonAction.enterNameandUploadImage(planName, imgPath);
			pkgSession.clickEnablePackageCheckBox();
			createPlansCommonAction.configurePlanDetails(minTimeInAdvanced, maxTimeInAdvanced, from, to);
			pkgSession.clickPricePerSlotPerHourCheckBox();

			createPlansCommonAction.enterGuestDetailsForPackagePlan(maxGuestPerSlot, guestPerPackage, maxPackages, maxGuestsAllowed, true);
			createPlansCommonAction.enterSaleItemDetails(purchaseItemsName, purchaseItemsprice, purchaseItemSaleTax, purchaseItemTimeFrame);
			// Slot time and price details
			createBUY_NOWServicePlan.selectSlotTime(slotNo1, startHourSlot1, startMinuteSlot1, startAmpmSlot1, endHourSlot1, endMinuteSlot1, endAmpmSlot1);//slot1
			pkgTime.selectSlot1TimeIncrement(3);
			pkgSession.enterSlotPriceDetails(slotNo1, packagePriceSlot1, additionalGuestPriceSlot1, packageSaleTaxSlot1, additionalGuestSaleTaxSlot1);
			createBUY_NOWServicePlan.addMoreReservationSlot();
			createBUY_NOWServicePlan.selectSlotTime(slotNo2, startHourSlot2, startMinuteSlot2, startAmpmSlot2, endHourSlot2, endMinuteSlot2, endAmpmSlot2);//slot2
			pkgTime.selectSlot2TimeIncrement(3);
			pkgSession.enterSlotPriceDetails(slotNo2, packagePriceSlot2, additionalGuestPriceSlot2, packageSaleTaxSlot2, additionalGuestSaleTaxSlot2);

			// Enter Pricing and Refund Details
			createPlansCommonAction.enterRefundDetails(refundType, refundAmount);

			// Enter Processing Fees
			createPlansCommonAction.enterProcessingFees(processingFeeName, processingFeeAmount);

			// Finalize and Assert
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
			ExtentReportManager.logPass("Package Time plan created successfully...!");
		} else {
			String message = driver.getPageSource();
			checkFailureMessages(message);
		}
	}

	// Method to check for specific failure messages
	private void checkFailureMessages(String pageSource ) {
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
		ExtentReportManager.logFail("Package time plan is not created due to " + message + "...!");
		System.out.println("Package time plan is not created due to " + message + "...!");
		Assert.fail(message);
	}

	// Method to handle exceptions and log error messages
	public void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("issue cause is :"+getCause);
		ExtentReportManager.logFail("testCreatePackageTimePlan test case is fail due to" + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}

}
