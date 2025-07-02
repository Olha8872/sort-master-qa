package de.ait.sortMaster.gui.page;

import de.ait.sortMaster.gui.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContainerPage extends BasePage {

    @FindBy(css = "li.p-4.rounded-lg.shadow-md.text-white")
    private List<WebElement> containers;

    private WebDriverWait wait;

    public ContainerPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getContainerCount() {
        return containers.size();
    }

    public void addItemToFirstContainer(String itemName) {
        if (!containers.isEmpty()) {
            WebElement firstContainer = containers.get(0);

            WebElement itemNameInput = firstContainer.findElement(By.cssSelector("input[name='name']"));
            WebElement addItemButton = firstContainer.findElement(By.cssSelector("button[type='submit']"));

            itemNameInput.clear();
            itemNameInput.sendKeys(itemName);
            addItemButton.click();

        }
    }

    public boolean isItemAddedSuccessfully(String itemName) {
        if (containers.isEmpty()) {
            return false;
        }

        WebElement firstContainer = containers.get(0);

        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.text-sm.p-2.rounded.bg-red-100.text-red-700")),
                    ExpectedConditions.attributeToBe(
                            firstContainer.findElement(By.cssSelector("input[name='name']")),
                            "value",
                            ""
                    )
            ));
        } catch (Exception e) {
            return false;
        }
        List<WebElement> errorDivs = firstContainer.findElements(By.cssSelector("div.text-sm.p-2.rounded.bg-red-100.text-red-700"));
        if (!errorDivs.isEmpty() && errorDivs.get(0).isDisplayed()) {
            return false;
        }
        WebElement input = firstContainer.findElement(By.cssSelector("input[name='name']"));
        return input.getAttribute("value").isEmpty();
    }
}

