package authorization;

import user.User;
import user.UserClient;
import user.CreateUser;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.Profile;

import java.time.Duration;

public class LogoutTest extends base.BaseTest {
    private UserClient userClient;
    private CreateUser testUser;

    @Before
    public void setUp() {
        userClient = new UserClient(); // Инициализация клиента для работы с API
        testUser = new CreateUser("test1","landoriuso@gmail.com","asdfjkl;12"); // Создание нового пользователя
        userClient.register(testUser); // Регистрация пользователя через API
    }

    @Test
    @DisplayName("Проверь выход по кнопке «Выйти» в личном кабинете.")
    public void logout() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        Profile profilePage = new Profile(webDriver);

        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailAndPassword(testUser.getEmail(), testUser.getPassword());
        loginPage.clickSignInButton();
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(MainPage.BURGER_CONSTRUCTOR));
        mainPage.clickPersonalAccountButton();
        profilePage.clickLogoutButton();
        loginPage.checkRestorePasswordButton();//проверка, что есть кнопка восстановления пароля, она есть только если юзер неавторизован и находится на экране логина



    }
    @After
    public void tearDown() {
        ValidatableResponse response = userClient.login(new User(testUser.getEmail(), testUser.getPassword()));
        String accessToken = userClient.getToken(response);
        userClient.delete(accessToken);
        webDriver.quit();
    }
}
