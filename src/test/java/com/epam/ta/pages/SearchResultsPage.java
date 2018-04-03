package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends AbstractPage {


    private final Logger logger = LogManager.getRootLogger();
    private WebDriverWait wait = new WebDriverWait(driver, 50);

    @FindBy(xpath = "//a[contains(text(),'Users')]")
    private WebElement usersButton;

    @FindBy(xpath = "//div[@class=\"user-list-info ml-2\"]/a")
    private WebElement userLink;

    @FindBy(xpath = "//button[@class=\"btn btn-sm js-toggler-target\"]")
    private WebElement unFollowButton;

    @FindBy(xpath = "//button[@class=\"btn btn-sm  js-toggler-target\"]")
    private WebElement followButton;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickUsersButton(){
        wait.until(ExpectedConditions.elementToBeClickable(usersButton));
        usersButton.click();
    }

    public void clickFollowButton(){
        if (unFollowButton.isDisplayed()){
            unFollowButton.click();
        }
        else  followButton.click();
    }

    public String getTextButton(){
        String text = null;
        if (unFollowButton.isDisplayed()){
          text =  unFollowButton.getText();
        }
        else {
            text = followButton.getText();}
            return text;
        }




    public void openPage() {

    }
}
