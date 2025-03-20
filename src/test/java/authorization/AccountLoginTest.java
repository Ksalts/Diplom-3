package authorization;
import pageObject.Profile;
import user.CreateUser;
import user.User;
import user.UserClient;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.Profile;


import java.time.Duration;

public class AccountLoginTest extends base.BaseTest {
    private UserClient userClient;
    private CreateUser testUser;

    @Before
    public void setUp() {
        userClient = new UserClient();
        testUser = new CreateUser("test1","landoriuso@gmail.com","asdfjkl;12");
        userClient.register(testUser);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void accountLogin() {
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        Profile profilePage = new Profile(webDriver);
        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailAndPassword(testUser.getEmail(), testUser.getPassword());
        loginPage.clickSignInButton();
        mainPage.clickPersonalAccountButton();
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(MainPage.BURGER_CONSTRUCTOR));
        profilePage.checkLogoutButton();
    }

    @After
    public void tearDown() {
        ValidatableResponse response = userClient.login(new User(testUser.getEmail(), testUser.getPassword()));
        String accessToken = userClient.getToken(response);
        userClient.delete(accessToken);
        webDriver.quit();
    }
}