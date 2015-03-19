package com.tridentqa.webdriver.appium;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sahagin.runlib.external.adapter.webdriver.WebDriverAdapter;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IOSNativeTest {
    private AppiumLauncher appium;
    private IOSDriver driver;
    
    @Before
    public void setUp() throws MalformedURLException {
        appium = new AppiumLauncher();
        appium.launch();
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone 5");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "8.2");
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "../../apps/TestApp/TestApp.app");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverAdapter.setAdapter(driver);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (appium != null) {
            appium.stop();
        }
    }
    
    @Test
    public void 計算処理が正しく行われること() {
        driver.findElementByAccessibilityId("IntegerA").sendKeys("123");
        driver.findElementByAccessibilityId("IntegerB").sendKeys("456");
        driver.findElementByAccessibilityId("ComputeSumButton").click();
        assertThat(driver.findElementByAccessibilityId("Answer").getText(), is("579"));
    }
}
