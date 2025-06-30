package de.ait.sortMaster.gui.tests;

import de.ait.sortMaster.gui.core.TestBase;
import de.ait.sortMaster.gui.page.ContainerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContainerPageTests extends TestBase {

    @Test
    public void checkContainerListNotEmptyTest() {
        app.getDriver().get("http://localhost:5175/#/containers");

        ContainerPage containerPage = new ContainerPage(app.getDriver());
        Assert.assertTrue(containerPage.getContainerCount() > 0, "No containers on page");
    }

    @Test
    public void addItemToContainerTest() {
        app.getDriver().get("http://localhost:5175/#/containers");

        ContainerPage containerPage = new ContainerPage(app.getDriver());
        String itemName = "newspaper_" + System.currentTimeMillis();
        containerPage.addItemToFirstContainer(itemName);
        Assert.assertFalse(containerPage.isItemAddedSuccessfully(itemName), "The item was not added");


    }
}
