package com.tridentqa.webdriver.pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sahagin.runlib.external.adapter.webdriver.WebDriverAdapter;

public class ContactPageSampleTest {
    private WebDriver wd;
    
    @Before
    public void setUp() {
        wd = new FirefoxDriver();
        WebDriverAdapter.setAdapter(wd);
    }
    
    @After
    public void tearDown() {
        if (wd != null) {
            wd.quit();
        }
    }
    
    @Test
    public void 問い合わせが成功すること() {
        wd.get("http://www-demo.trident-qa.com/contact/");
        wd.findElement(By.name("your-name")).sendKeys("テストユーザー");
        wd.findElement(By.name("your-email")).sendKeys("***@***.com");
        wd.findElement(By.name("your-organization")).sendKeys("TRIDENT Inc.");
        wd.findElement(By.name("your-subject")).sendKeys("テスト");
        wd.findElement(By.name("your-messagef")).sendKeys("テスト送信です");
        wd.findElement(By.cssSelector("input.wpcf7-submit")).click();
    }
}
