package de.ait.sortMaster.gui.core;

import de.ait.sortMaster.utils.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class ApplicationManager {

    public static SoftAssert softly = new SoftAssert();
    public WebDriver driver;

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public WebDriver startTest() {
        switch (browser) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
        }

        WebDriverListener listener = new MyListener();
        driver = new EventFiringDecorator<>(listener).decorate(driver);

        driver.get("http://localhost:5175/#/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public void stopTest() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}

