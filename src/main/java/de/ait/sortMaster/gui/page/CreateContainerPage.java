package de.ait.sortMaster.gui.page;

import de.ait.sortMaster.gui.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateContainerPage extends BasePage {

    @FindBy(css = "input[placeholder='Plastic, Paper...']")
    private WebElement nameField;

    @FindBy(css = "input[type='color']")
    private WebElement colorPicker;

    @FindBy(css = "input[placeholder*='container for']")
    private WebElement descriptionField;

    @FindBy(xpath = "//button[contains(text(), 'Create Container')]")
    private WebElement createButton;

    public CreateContainerPage(WebDriver driver) {
        super(driver);
    }

    public void createContainer(String name, String color, String description) {
        type(nameField, name);
        colorPicker.sendKeys(color);
        type(descriptionField, description);
        click(createButton);
    }

    public boolean isContainerCreatedSuccessfully() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/containers"));
    }

        public boolean isFieldHasError(String fieldId) {
        WebElement field = driver.findElement(By.id(fieldId));
        String classes = field.getAttribute("class");
        return classes.contains("border-red-500");
    }
}

