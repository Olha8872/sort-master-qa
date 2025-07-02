package de.ait.sortMaster.gui.page;

import de.ait.sortMaster.gui.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Find the Right Container')]")
    private WebElement pageTitle;

    @FindBy(css = "input[placeholder*='item name']")
    private WebElement searchInput;

    private final By resultMessageLocator = By.xpath("//*[contains(text(), 'Find the Right Container')]");
    private final By noMatchMessageLocator = By.xpath("//*[contains(text(), 'No matching containers found')]");
    private final By resultsLocator = By.cssSelector(".result-item");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return pageTitle.isDisplayed();
    }

    public void searchItem(String itemName) {
        type(searchInput, itemName);
    }

    public void searchFor(String itemName) {
        searchInput.clear();
        searchInput.sendKeys(itemName);
        searchInput.sendKeys(Keys.ENTER);
    }

    public boolean isSuggestionDisplayed(String containerName) {
        try {
            WebElement suggestion = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
                                    + containerName.toLowerCase() + "')]")));
            return suggestion.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchInputVisible() {
        return searchInput.isDisplayed();
    }

    public boolean isCorrectResultMessageDisplayed() {
        try {
            WebElement message = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(resultMessageLocator));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoMatchingContainerMessageDisplayed() {
        try {
            WebElement message = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(noMatchMessageLocator));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoMatchMessageText() {
        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(noMatchMessageLocator));
        return message.getText();
    }

    public int getNumberOfResults() {
        List<WebElement> results = driver.findElements(resultsLocator);
        return results.size();
    }
}
