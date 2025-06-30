package de.ait.sortMaster.gui.tests;

import de.ait.sortMaster.gui.core.TestBase;
import de.ait.sortMaster.gui.page.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTests extends TestBase {

    @Test
    public void openMainPageTest() {
        driver.get("http://localhost:5175/#/");
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isPageOpened(), "Main page was not opened");
    }

    @Test
    public void searchItemInContainerTest() {
        driver.get("http://localhost:5175/#/");
        MainPage mainPage = new MainPage(driver);
        mainPage.searchItem("newspaper");
        Assert.assertTrue(mainPage.isSuggestionDisplayed("Paper"), "Incorrect container suggested");
    }
}
