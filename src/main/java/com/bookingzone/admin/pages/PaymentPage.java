package com.bookingzone.admin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

   

    @FindBy(xpath = "//button[contains(text(),'Pay ')]")
    private WebElement payOnPaymentPage;

    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

   

    /**
     * Method to click the pay on payment page button.
     */
    public void clickPayOnPaymentPage() {
        payOnPaymentPage.click();
    }
}
