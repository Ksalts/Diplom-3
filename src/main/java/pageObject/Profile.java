package pageObject;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class Profile {
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logOutButton = By.xpath(".//button[text() = 'Выход']");

    private final WebDriver webDriver;

    public  Profile(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void clickLogoButton(){
        webDriver.findElement(logoButton).click();
    }
    public void clickConstructorButton(){
        webDriver.findElement(constructorButton).click();
    }

    public void checkLogoutButton(){
        String textOfLogoutButton = webDriver.findElement(logoButton).getText();
        MatcherAssert.assertThat(textOfLogoutButton, startsWith("Выход"));

    }

    public void clickLogoutButton(){
        webDriver.findElement(logoButton).click();
    }
}
