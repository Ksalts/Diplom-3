package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private final By signInButton = By.xpath(".//a[text()='Войти']");

    private final WebDriver webDriver;

    public RestorePasswordPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void clickSignInButton(){
        webDriver.findElement(signInButton).click();
    }
}
