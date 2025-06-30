package de.ait.sortMaster.gui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager
            (System.getProperty("browser", Browser.CHROME.browserName()));

    protected WebDriver driver;  // <- добавь это поле

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startTest(Method method, Object[] p) {
        driver = app.startTest();  // <- инициализируем драйвер
        logger.info("Start test " + method.getName() + " with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: " + result.getMethod().getMethodName());
        }
        logger.info("Stop test");
        logger.info("**********************************************************");
        app.stopTest();
        driver = null; // по желанию, освобождаем ссылку после теста
    }
}
