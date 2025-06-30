package de.ait.sortMaster.gui.tests;

import de.ait.sortMaster.gui.core.TestBase;
import de.ait.sortMaster.gui.page.CreateContainerPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContainerTest extends TestBase {

    @Test
    public void createValidContainerTest() {
        driver.get("http://localhost:5175/#/container-form");
        CreateContainerPage createContainerPage = new CreateContainerPage(driver);
        createContainerPage.createContainer("Organic", "#8B4513", "Brown container for organic waste");

        Assert.assertTrue(createContainerPage.isContainerCreatedSuccessfully(), "Container was not created successfully");
    }

    @Test
    public void createContainerWithMissingFieldsTest() {
        driver.get("http://localhost:5175/#/container-form");
        CreateContainerPage createContainerPage = new CreateContainerPage(driver);
        createContainerPage.createContainer("", "", "");

        Assert.assertFalse(createContainerPage.isContainerCreatedSuccessfully(), "Container should not be created with missing fields");
    }
}
