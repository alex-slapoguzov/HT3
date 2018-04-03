package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Steps
{
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void loginGithub(String username, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public boolean isLoggedIn(String username)
	{
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
		logger.info("Actual username: " + actualUsername);
		return actualUsername.equals(username);
	}

	public boolean createNewRepository(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
		return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
	}

	public void chooseNewPrivateRepo()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		createNewRepositoryPage.choosePrivateRepo();
	}

	public boolean currentRepositoryIsEmpty()
	{
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

	public boolean isCanChoosePrivateRepo(){
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		return createNewRepositoryPage.isElementPresent();
	}

	public void goToResults(String text){
		MainPage mainPage = new MainPage(driver);
		mainPage.typeData(text);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		searchResultsPage.clickUsersButton();
	}

	public boolean isButtonChanged(){
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		String currentTextButton = searchResultsPage.getTextButton();
		boolean result = false;
		searchResultsPage.clickFollowButton();
		if (currentTextButton.equals(searchResultsPage.getTextButton())){
			result = true;
		}
		return result;
	}

	public void addGitignoreFile(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
		NewRepositoryPage newRepositoryPage = new NewRepositoryPage(driver);
		newRepositoryPage.addReadme();
	}

	public boolean isGitignoreFilePresent(){
		NewRepositoryPage newRepositoryPage = new NewRepositoryPage(driver);
		return newRepositoryPage.isElementPresent();
	}

}
