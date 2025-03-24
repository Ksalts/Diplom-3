package base;

import browser.Browser;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import pageObject.MainPage;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public WebDriver webDriver;

    @Before
    public void setUpBrowser() throws IOException{
        Browser browser = new Browser();
        webDriver = browser.initDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
    }
    @After
    public void close(){
        webDriver.quit();
    }
}
