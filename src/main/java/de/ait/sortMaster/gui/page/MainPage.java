package de.ait.sortMaster.gui.page;

import de.ait.sortMaster.gui.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Trash Sort')]")
    private WebElement pageTitle;

    @FindBy(css = "input[placeholder*='item name']")
    private WebElement searchInput;

    public MainPage(WebDriver driver) {
        super(driver);
    }
    public boolean isPageOpened() {
        return pageTitle.isDisplayed();
    }

    public void searchItem(String itemName) {
        type(searchInput, itemName);
    }

    public boolean isSuggestionDisplayed(String containerName) {
        return driver.findElement(By.xpath("//*[contains(text(), '" + containerName + "')]")).isDisplayed();
    }

}
