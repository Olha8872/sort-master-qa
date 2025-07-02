package de.ait.sortMaster.gui.tests;

import de.ait.sortMaster.gui.core.TestBase;
import de.ait.sortMaster.gui.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageTests extends TestBase {

    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        driver.get("http://localhost:5175/#/");
        mainPage = new MainPage(app.driver) ;
    }

    @Test
    public void openMainPageTest() {
        Assert.assertTrue(mainPage.isPageOpened(), "Main page was not opened");
    }

    @Test
    public void searchItemInContainerTest() {
        mainPage.searchFor("newspaper");
        Assert.assertTrue(mainPage.isSuggestionDisplayed("paper"), "Container suggestion 'Paper' was not found");
        Assert.assertTrue(mainPage.isCorrectResultMessageDisplayed(), "Correct result message not displayed");
        int count = mainPage.getNumberOfResults();
        Assert.assertFalse(count > 0, "Expected at least one search result");
    }

    @Test
    public void inputShouldBeVisibleTest() {
        Assert.assertTrue(mainPage.isSearchInputVisible(), "Search input is not visible");
    }

    @Test
    public void searchInvalidItemTest() {
        mainPage.searchFor("sxbfewq");
        Assert.assertTrue(mainPage.isNoMatchingContainerMessageDisplayed(), "'No matching containers found' was not displayed");
        Assert.assertEquals(mainPage.getNoMatchMessageText(), "No matching containers found.", "Incorrect error message text");
        Assert.assertEquals(mainPage.getNumberOfResults(), 0, "Expected zero results for invalid search");
    }

    @Test
    public void searchEmptyInputTest() {
        mainPage.searchFor("   ");
        Assert.assertFalse(mainPage.isNoMatchingContainerMessageDisplayed(), "'No matching containers found' was not displayed for empty input");
    }
}

