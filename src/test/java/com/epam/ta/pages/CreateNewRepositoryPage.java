package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.utils.Utils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewRepositoryPage extends AbstractPage
{
	private final String BASE_URL = "http://www.github.com/new";
	private final Logger logger = LogManager.getRootLogger();
	private WebDriverWait wait = new WebDriverWait(driver, 50);

	@FindBy(id = "repository_name")
	private WebElement inputRepositoryName;

	@FindBy(id = "repository_description")
	private WebElement inputRepositoryDescription;

	@FindBy(xpath = "//form[@id='new_repository']//button[@type='submit']")
	private WebElement butttonCreate;

	@FindBy(xpath = "//a[@class=\"btn btn-sm\"]")
	private WebElement labelEmptyRepoSetupOption;

	@FindBy(xpath = "//a[@data-pjax=\"#js-repo-pjax-container\"]")
	private WebElement linkCurrentRepository;

	@FindBy(id = "repository_public_false")
	private WebElement privateRepo;

	@FindBy(xpath = "//div[@id=\"js-upgrade-container\"]//div[@class=\"form-checkbox\"]//em")
	private WebElement upgradeMyAccountText;


	public CreateNewRepositoryPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public boolean isCurrentRepositoryEmpty()
	{
		return labelEmptyRepoSetupOption.isDisplayed();
	}

	public String createNewRepository(String repositoryName, String repositoryDescription)
	{
		String repositoryFullName = repositoryName + Utils.getRandomString(6);
		inputRepositoryName.sendKeys(repositoryFullName);
		inputRepositoryDescription.sendKeys(repositoryDescription);
		butttonCreate.click();
		logger.info("New repository was created!");
		return repositoryFullName;
	}

	public String getCurrentRepositoryName()
	{
		return linkCurrentRepository.getText();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
		logger.info("CreateNewRepository page opened");

	}

	public void choosePrivateRepo(){
		privateRepo.click();
	}

	public boolean isElementPresent(){
		wait.until(ExpectedConditions.elementToBeClickable(upgradeMyAccountText));
		boolean result = false;
		if (wait.until(ExpectedConditions.elementToBeClickable(upgradeMyAccountText)).isEnabled()){
			return true;
		}
		return result;
	}

}
