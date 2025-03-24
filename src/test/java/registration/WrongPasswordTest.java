package registration;

import com.github.javafaker.Faker;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;


public class WrongPasswordTest extends base.BaseTest{
    Faker faker = new Faker();

    @Test
    @DisplayName("Пароль менее 6 символов при регистрации")
    public void shortPassword(){
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);

        mainPage.clickPersonalAccountButton();
        loginPage.clickLittleRegisterButton();
        registerPage.enterName(faker.name().firstName());
        registerPage.enterEmail(faker.internet().emailAddress());
        registerPage.enterPassword(faker.internet().password(3,5));
        registerPage.clickRegisterButton();
        registerPage.enterWrongPassword();
    }
    @After
    public void close(){
        webDriver.quit();
    }
}
