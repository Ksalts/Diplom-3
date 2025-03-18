package pageObject;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.core.StringStartsWith.startsWith;

public class RegisterPage {
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By signInButton = By.xpath(".//a[text()='Войти']");
    private final By wrongPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");

    private final WebDriver webDriver;

    public RegisterPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void enterName(String text){
        webDriver.findElement(nameField).sendKeys(text);
    }

    public void enterEmail(String text){
        webDriver.findElement(emailField).sendKeys(text);
    }
    public void enterPassword(String text){
        webDriver.findElement(passwordField).sendKeys(text);
    }
    public void clickRegisterButton(){
        webDriver.findElement(registerButton).click();
    }

    public void enterWrongPassword(){
        String textOfError = webDriver.findElement(wrongPasswordMessage).getText();
        MatcherAssert.assertThat("Вход", textOfError, startsWith("Некорректный пароль"));
    }

    public void clickSignInButton(){
        webDriver.findElement(signInButton).click();
    }
}
