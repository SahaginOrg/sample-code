package com.tridentqa.webdriver.pages;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.sahagin.runlib.external.adapter.webdriver.WebDriverAdapter;

public class ContactPageSampleTest {
        
    @Test
    public void Firefoxでの問い合わせが成功すること() {
        WebDriver wd = new FirefoxDriver();
        WebDriverAdapter.setAdapter(wd);
        try {
            testMain(wd);
        } finally {
            wd.quit();
        }
    }
    
    @Test
    public void Chromeでの問い合わせが成功すること() {
        WebDriver wd = new ChromeDriver();
        WebDriverAdapter.setAdapter(wd);
        try {
            testMain(wd);
        } finally {
            wd.quit();
        }
    }
    
    @Test
    public void PhantomJSでの問い合わせが成功すること() {
        WebDriver wd = new PhantomJSDriver();
        WebDriverAdapter.setAdapter(wd);
        try {
            testMain(wd);
        } finally {
            wd.quit();
        }
    }
    
    private void testMain(WebDriver wd) {
        wd.get("http://www-demo.trident-qa.com/contact/");
        wd.findElement(By.name("your-name")).sendKeys("テストユーザー");
        wd.findElement(By.name("your-email")).sendKeys("***@***.com");
        wd.findElement(By.name("your-organization")).sendKeys("TRIDENT Inc.");
        wd.findElement(By.name("your-subject")).sendKeys("テスト");
        wd.findElement(By.name("your-message")).sendKeys("テスト送信です");
        wd.findElement(By.cssSelector("input.wpcf7-submit")).click();
    }
}
