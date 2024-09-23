package com.bookingzone.admin.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookingzone.admin.pages.StepperPaymentPage;
import com.bookingzone.admin.pages.StepperSummaryPage;

public class PaymentActions {
	static Logger logger = (Logger) LogManager.getLogger(PaymentActions.class);

	private StepperPaymentPage stepperPaymentPage;
	private StepperSummaryPage stepperSummaryPage;


	public PaymentActions(StepperPaymentPage stepperPaymentPage, StepperSummaryPage stepperSummaryPage) {
		this.stepperPaymentPage = stepperPaymentPage;
		this.stepperSummaryPage = stepperSummaryPage;

	}

	public void completePayment(WebDriver driver, String paymentMethod, String cardNumber, String expiryDate, String cvc) throws InterruptedException, IOException, TimeoutException {
		switch (paymentMethod.toLowerCase()) {
		case "mppg":
			handleCardPayment(driver, cardNumber, expiryDate, cvc);
			break;
		case "cash":
			// handlePaypalPayment();
			break;
		case "other":
			//  handleBankTransferPayment();
			break;
		default:
			ExtentReportManager.getTest().fail("Unsupported payment method: " + paymentMethod);
			throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
		}
	}


	private void handleCardPayment(WebDriver driver, String cardNumber, String expiryDate, String cvc) throws InterruptedException, IOException, TimeoutException {
		// Select payment method
		stepperPaymentPage.selectPaymentMethod(UtilityClass.getDataFromEs(1, "Payment Method", "Create_Booking"));
		stepperPaymentPage.clickTipCrossButton();

		// Check if the "Pay via Saved Card" option is available
		if (stepperPaymentPage.isPayViaSavedCardOptionAvailable())
		{
			stepperPaymentPage.clickPayViaSavedCard();
		} 
		else 
		{
			System.out.println("Else Condition");
			
			// Switch to iframe for entering card details
			driver.switchTo().frame("__teConnectSecureFrame");
            // Enter card details
			stepperPaymentPage.enterCardNumber(cardNumber);
			stepperPaymentPage.enterExpiryDate(expiryDate);
			stepperPaymentPage.enterCVC(cvc);

			// Switch back to the default content
			driver.switchTo().defaultContent();
		}

		// Click the pay button
		stepperPaymentPage.clickPayButton();
		Thread.sleep(15000);
	}

	public void verifyTotal_SubTotalAmount() throws InterruptedException {
		stepperPaymentPage.verifySubtotal();
		stepperPaymentPage.verifyTotalAmount();
	}


	public String getBookingId() 
	{
		String BookingId=stepperSummaryPage.getBookingId();
		System.out.println("Booking Id is:-" + BookingId);
		return BookingId;
	}

}
