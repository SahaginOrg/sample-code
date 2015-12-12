package com.tridentqa.webdriver.pages;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sahagin.runlib.external.adapter.webdriver.WebDriverAdapter;

@RunWith(Parameterized.class)
public class ContactPageSampleTest {
    private Class<WebDriver> driverClass;
    private WebDriver wd;
    
    @Before
    public void setUp() throws InstantiationException, IllegalAccessException {
        wd = driverClass.newInstance();
        WebDriverAdapter.setAdapter(wd);
    }
    
    @After
    public void tearDown() {
        if (wd != null) {
            wd.quit();
        }
    }

    @Parameters(name = "{1}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {FirefoxDriver.class, "Firefox"}, 
                {ChromeDriver.class, "Chrome"}
        });
    }
    
    public ContactPageSampleTest(Class<WebDriver> driverClass, String driverName) {
        this.driverClass = driverClass;
    }
    
    @Test
    public void 問い合わせが成功すること() {
        wd.get("http://www-demo.trident-qa.com/contact/");
        wd.findElement(By.name("your-name")).sendKeys("テストユーザー");
        wd.findElement(By.name("your-email")).sendKeys("***@***.com");
        wd.findElement(By.name("your-organization")).sendKeys("TRIDENT Inc.");
        wd.findElement(By.name("your-subject")).sendKeys("テスト");
        wd.findElement(By.name("your-message")).sendKeys("テスト送信です");
        wd.findElement(By.cssSelector("input.wpcf7-submit")).click();
    }
}
