package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Browser {
    protected WebDriver webDriver;

    public WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/Browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        BrowserType browserType = BrowserType.valueOf(browserProperty);

        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=");

                webDriver = new ChromeDriver(chromeOptions);
                break;

            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/yandex/yandexdriver");
                webDriver = new ChromeDriver();
                break;

            default:
                throw new RuntimeException("Browser undefined");
        }
        return webDriver;
    }
}
