package com.tridentqa.webdriver.pages;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactPageSampleTest {

    @Test
    public void 問い合わせが成功すること() {
        WebDriver wd = new ChromeDriver();
        wd.get("http://www-demo.trident-qa.com/contact/");
        wd.findElement(By.name("your-name")).sendKeys("テストユーザー");
        wd.findElement(By.name("your-email")).sendKeys("***@***.com");
        wd.findElement(By.name("your-organization")).sendKeys("TRIDENT Inc.");
        wd.findElement(By.name("your-subject")).sendKeys("テスト");
        wd.findElement(By.name("your-message")).sendKeys("テスト送信です");
        wd.findElement(By.cssSelector("input.wpcf7-submit")).click();
        wd.quit();
    }
}
