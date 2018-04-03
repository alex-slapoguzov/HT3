package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewRepositoryPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private WebDriverWait wait = new WebDriverWait(driver, 50);

    @FindBy(xpath = "//a[@data-ga-click=\"Empty repo, click, Clicked README link\"]")
    private WebElement readmeLink;

    @FindBy(id = "submit-file")
    private WebElement submitButton;

    @FindBy(xpath = "//a[@class=\"js-navigation-open\"]")
    private WebElement readmeFile;

    public NewRepositoryPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage(){}

    public void clickReadmeLink(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-ga-click=\"Empty repo, click, Clicked .gitignore link\"]")));
        readmeLink.click();
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void addReadme(){
        clickReadmeLink();
        clickSubmitButton();
        logger.info("README file was added!");
    }

    public boolean isElementPresent(){
        wait.until(ExpectedConditions.elementToBeClickable(readmeFile));
        boolean result = false;
        if (readmeFile.isDisplayed()){
            result = true;
        }
        return result;
    }

}
