package com.bookingzone.admin.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the Edit_Addon functionality in the application.
 */
public class AddonsPage {
    

    // WebElement for selecting an addon
    @FindBy(xpath = "(//button[contains(@class,'MuiButton-root M')])[18]")
    private WebElement selectAddon;

   
    /**
     * Constructor to initialize elements using PageFactory.
     * @param driver The WebDriver instance.
     */
    public AddonsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to select an addon.
     * @param driver The WebDriver instance.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void selectAddon(WebDriver driver) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", selectAddon);
        selectAddon.click();
    }

}
