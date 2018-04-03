package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest {
    private Steps steps;
    private final String USERNAME = "testautomationuser";
    private final String PASSWORD = "Time4Death!";

    @BeforeMethod(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test
    public void oneCanCreateProject() {
        steps.loginGithub(USERNAME, PASSWORD);
        Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
        Assert.assertTrue(steps.currentRepositoryIsEmpty());
        // do not use lots of asserts
    }

    @Test(description = "Login to Github")
    public void oneCanLoginGithub() {
        steps.loginGithub(USERNAME, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn(USERNAME));
    }

    @Test
    public void testCanPrivateRepo() {
        steps.loginGithub(USERNAME, PASSWORD);
        steps.chooseNewPrivateRepo();
        Assert.assertTrue(steps.isCanChoosePrivateRepo());
    }


    @Parameters({"userName"})
    @Test
    public void testFollowButton(String userName) {
        steps.loginGithub(USERNAME, PASSWORD);
        steps.goToResults(userName);
        Assert.assertFalse(steps.isButtonChanged());
    }

    @Test
    public void testAddReadmeFileInNewRepo() {
        steps.loginGithub(USERNAME, PASSWORD);
        steps.addGitignoreFile("testRepo", "auto-generated test repo");
        Assert.assertTrue(steps.isGitignoreFilePresent());
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }

}
