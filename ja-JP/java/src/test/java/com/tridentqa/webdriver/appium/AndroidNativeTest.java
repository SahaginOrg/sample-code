package com.tridentqa.webdriver.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sahagin.runlib.external.adapter.appium.AppiumAdapter;

//- Android emulator must be launched before this test
//- must set environment variable ANDROID_HOME
public class AndroidNativeTest {
    private AppiumLauncher appium;
    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        appium = new AppiumLauncher();
        appium.launch();
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","Android Emulator");
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "../../apps/ContactManager/ContactManager.apk");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        AppiumAdapter.setAdapter(driver);
    }
    
    @After
    public void tearDown() {
        driver.quit();
        if (appium != null) {
            appium.stop();
        }
    }
  
    @Test
    public void 連絡先の登録が成功すること() {
        driver.findElement(By.name("Add Contact")).click();
        driver.findElementById("com.example.android.contactmanager:id/contactNameEditText").sendKeys("sample user");
        driver.findElementById("com.example.android.contactmanager:id/contactPhoneEditText").sendKeys("000000000");
        driver.findElementById("com.example.android.contactmanager:id/contactEmailEditText").sendKeys("***.com");
        driver.findElementById("com.example.android.contactmanager:id/contactSaveButton").click();     
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
}
