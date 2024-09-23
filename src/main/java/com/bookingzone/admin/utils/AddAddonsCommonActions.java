package com.bookingzone.admin.utils;

import com.bookingzone.admin.pages.CreateAddonsPage;
import com.bookingzone.admin.pages.OutletDetailsPage;
import com.bookingzone.admin.pages.OutletListPage;

public class AddAddonsCommonActions {

	public static void navigateToAddons(OutletListPage outletList, OutletDetailsPage outletDetails, boolean isFirstTestCase) throws InterruptedException {
		outletList.clickArrow();
		ExtentReportManager.logInfo("Clicked On Arrow...!");

		outletDetails.clickAddonsBtn();

		if (isFirstTestCase) {
			outletDetails.clickAddIemsBtn();
			ExtentReportManager.logInfo("Clicked On AddItems Button...!");
		} else {
			outletDetails.clickUpdateIemsBtn();
			ExtentReportManager.logInfo("Clicked On UpdateItems Button...!");
		}
	}

	public static void enterAddonDetails(CreateAddonsPage createAddon, String addonName, String tagName, String quantity, String basePrice, String percentTax, String dollarTax) throws InterruptedException   {
		createAddon.clickAddItemBtn();
		createAddon.enterItemName(addonName);
		ExtentReportManager.logInfo("Entered Addon Name...!");

		createAddon.selectTag(tagName);
		ExtentReportManager.logInfo("Tag Selected...!");

		createAddon.enterQuantityInStock(quantity);
		ExtentReportManager.logInfo("Entered Quantity In Stock...!");

		createAddon.enterBasePrice(basePrice);
		ExtentReportManager.logInfo("Entered Base Price...!");

		if (percentTax != null) {
			createAddon.enterPerTax(percentTax);
			ExtentReportManager.logInfo("Entered %Tax...!");
		}

		if (dollarTax != null) {
			createAddon.enterFlatTax(dollarTax);
			ExtentReportManager.logInfo("Entered $Tax...!");
		}

		createAddon.clickSaveBtn();
		ExtentReportManager.logInfo("Clicked On Save button...!");
	}


}
