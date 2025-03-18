package authorization;

import io.qameta.allure.Description;
import user.User;
import user.UserClient;
import user.CreateUser;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.RestorePasswordPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;

public class LoginTest extends base.BaseTest {
    private UserClient userClient;
    private CreateUser testUser;

    @Before
    public void setUp(){
        userClient = new UserClient();
        testUser = new CreateUser("test1","asdfjkl;12","k.saltanovska@gmail.com");
        userClient.register(testUser);

    }
    @Test
    @DisplayName("Авторизация через кнопку Личный кабинет")
    @Description("Можно авторизоваться через кнопку Личный кабинет")
            public void loginThroughPersonalAccountButton(){
                MainPage mainPage = new MainPage(webDriver);
                LoginPage loginPage = new LoginPage(webDriver);
                mainPage.clickPersonalAccountButton();
                loginPage.enterEmailAndPassword(testUser.getEmail(), testUser.getPassword());
                loginPage.clickSignInButton();
                mainPage.findOrderButton();
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void loginThroughRestorePasswordPageButton(){
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(webDriver);
        mainPage.clickSignInButton();
        loginPage.clickRestorePasswordButton();
        loginPage.clickSignInButton();
        loginPage.enterEmailAndPassword(testUser.getEmail(), testUser.getPassword());
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме регистрации")
    public void loginThroughRegistrationPageButton(){
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        mainPage.clickSignInButton();
        loginPage.clickLittleRegisterButton();
        registerPage.clickSignInButton();
        mainPage.findOrderButton();
    }
    @After
    public void endSession(){
        ValidatableResponse response = userClient.login(new User(testUser.getEmail(), testUser.getPassword()));
        String accessToken = userClient.getToken(response);
        userClient.delete(accessToken);
        webDriver.quit();
    }


}
