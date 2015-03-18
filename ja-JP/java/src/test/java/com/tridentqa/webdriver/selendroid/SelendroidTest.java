package com.tridentqa.webdriver.selendroid;

import io.selendroid.client.Configuration;
import io.selendroid.client.DriverCommand;
import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sahagin.runlib.external.adapter.selendroid.SelendroidAdapter;
import com.tridentqa.webdriver.pages.ContactPage;

//- Execute test on Android emulator
//- Assume Selendroid server is started
public class SelendroidTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
      DesiredCapabilities caps = SelendroidCapabilities.android();
      driver = new SelendroidDriver(caps);
      Configuration configurable = (Configuration) driver;
      // this setting is required when uses unicode character as sendKeys method argument
      configurable.setConfiguration(DriverCommand.SEND_KEYS_TO_ELEMENT, "nativeEvents", false);
      SelendroidAdapter.setAdapter(driver);
    }
    
    @After
    public void tearDown() {
        driver.quit();
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
