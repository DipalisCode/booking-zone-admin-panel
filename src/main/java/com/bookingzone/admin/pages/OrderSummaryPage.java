package com.bookingzone.admin.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represents the functionality related to making payments via email in the admin section.
 */
public class OrderSummaryPage {


    @FindBy(xpath = "//div//p[@class='jsx-2926317546 fs_14 float-right m-0']")
    private WebElement payableAmount;
    
    @FindBy(xpath = "(//div//p[@class='jsx-2926317546 fs_14 float-right m-0'])[2]")
    private WebElement payButton;

    /**
     * Initializes the web elements using the PageFactory.
     * @param driver The WebDriver instance to use
     */
    public OrderSummaryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

   /**
     * Gets the payable amount displayed on the order summary page.
     * @return The payable amount as a string
     */
    public String getPayableAmountOnOrderSummaryPage() {
        String payableAmountText = payableAmount.getText();
        System.out.println("Payable amount on order summary page is: " + payableAmountText);
        return payableAmountText;
    }
    /**
     * Method to click the pay button.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void clickPayButton() throws InterruptedException {
        payButton.click();
        Thread.sleep(2000);
    }

  

}
