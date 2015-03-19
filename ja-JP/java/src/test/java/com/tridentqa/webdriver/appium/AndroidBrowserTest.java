package com.tridentqa.webdriver.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.sahagin.runlib.external.adapter.AdapterContainer;
import org.sahagin.runlib.external.adapter.ScreenCaptureAdapter;
import org.sahagin.runlib.external.adapter.appium.AppiumAdapter;

import com.tridentqa.webdriver.pages.ContactPage;


//- Android emulator must be launched before this test
//- must set environment variable ANDROID_HOME
public class AndroidBrowserTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        AppiumLauncher.launch();
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("browserName", "Browser");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        AppiumAdapter.setAdapter(driver);
        AdapterContainer.globalInstance().setScreenCaptureAdapter(new ScreenCaptureAdapter() {
            
            // cannot take screen capture with chromedriver..
            @Override
            public byte[] captueScreen() {
                byte[] result;
                String contextName = driver.getContext();
                driver.context("NATIVE_APP");
                try {
                    try {
                        result = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    } catch (SessionNotFoundException e) {
                        result = null;
                    }
                } finally {
                    driver.context(contextName);
                }
                return result;
            }
        });
    }
    
    @After
    public void tearDown() {
        driver.quit();
        AppiumLauncher.stop();
    }
    
    @Test
    public void 問い合わせが成功すること() {
        driver.get("http://www-demo.trident-qa.com/contact/");
        ContactPage contact = new ContactPage(driver);
        contact.setName("テストユーザー");
        contact.setMail("***@***.com");
        contact.setOrganization("TRIDENT");
        contact.setSubject("テスト");
        contact.setMessage("テスト送信です");
        contact.send();
    }
}
