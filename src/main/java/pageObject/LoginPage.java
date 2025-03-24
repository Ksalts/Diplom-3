package pageObject;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.core.StringStartsWith.startsWith;

public class LoginPage {
    private final By littleRegisterButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");
    private final By restorePasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    private final By signInButton = By.xpath(".//button[text()='Войти']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");

    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    public void clickLittleRegisterButton(){
        webDriver.findElement(littleRegisterButton).click();
    }

    public void checkRestorePasswordButton(){
        String textOfRestoreButton = webDriver.findElement(restorePasswordButton).getText();
        MatcherAssert.assertThat(textOfRestoreButton, startsWith("Восстановить пароль"));
    }

    public void clickRestorePasswordButton(){
        webDriver.findElement(restorePasswordButton).click();
    }

    public void enterEmailAndPassword(String email, String password){
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInButton(){
        webDriver.findElement(signInButton).click();
    }


}
