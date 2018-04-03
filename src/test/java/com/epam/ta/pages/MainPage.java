package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

	private final String BASE_URL = "https://github.com/";
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//span[@class=\"dropdown-caret mt-1\"]")
	private WebElement buttonCreateNew;

	@FindBy(xpath = "//a[contains(text(), 'New repository')]")
	private WebElement linkNewRepository;

	@FindBy(xpath = "//input[@class=\"form-control header-search-input  js-site-search-focus \"]")
	private WebElement searchGitHub;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void clickOnCreateNewRepositoryButton()
	{
		buttonCreateNew.click();
		linkNewRepository.click();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
		logger.info("Main page opened");
	}

	public void typeData(String text){
		searchGitHub.clear();
		searchGitHub.sendKeys(text);
		searchGitHub.sendKeys(Keys.ENTER);
		logger.info("SearchResults page opened");
	}


}
