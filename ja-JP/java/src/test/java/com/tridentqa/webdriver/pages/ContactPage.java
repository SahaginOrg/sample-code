package com.tridentqa.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sahagin.runlib.external.Page;
import org.sahagin.runlib.external.TestDoc;

@Page(value = "問い合わせページ")
public class ContactPage {
    private WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @TestDoc("名前に「{name}」をセットする")
    public void setName(String name) {
        driver.findElement(By.name("your-name")).sendKeys(name);
    }

    @TestDoc("メールアドレスに「{email}」をセットする")
    public void setMail(String email) {
        driver.findElement(By.name("your-email")).sendKeys(email);
    }

    @TestDoc("組織に「{organization}」をセットする")
    public void setOrganization(String organization) {
        driver.findElement(By.name("your-organization")).sendKeys(organization);
    }

    @TestDoc("件名に「{subject}」をセットする")
    public void setSubject(String subject) {
        driver.findElement(By.name("your-subject")).sendKeys(subject);
    }

    @TestDoc("問い合わせ内容に「{message}」をセットする")
    public void setMessage(String message) {
        driver.findElement(By.name("your-message")).sendKeys(message);
    }

    @TestDoc("問い合わせを送信する")
    public void send() {
        driver.findElement(By.cssSelector("input.wpcf7-submit")).click();
    }
}
