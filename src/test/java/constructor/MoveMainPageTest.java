package constructor;

import user.*; //все импорты используются

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

public class MoveMainPageTest extends base.BaseTest {

    private UserClient userClient;
    private CreateUser testUser;

    @Before
    public void setUp() {
        userClient = new UserClient();
        testUser = new CreateUser("test1","landoriuso@gmail.com","asdfjkl;12");
        userClient.register(testUser);
    }

    @Test
    @DisplayName("Проверь переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void moveMainPageWithLogoButton() {
        MainPage mainPage = new MainPage(webDriver);
        Profile profilePage = new Profile(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailAndPassword(testUser.getEmail(), testUser.getPassword());
        loginPage.clickSignInButton();
        mainPage.clickPersonalAccountButton();
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(MainPage.BURGER_CONSTRUCTOR));

        profilePage.clickLogoButton();
        mainPage.findOrderButton();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void moveMainPageWithConstructorButton() {
        MainPage mainPage = new MainPage(webDriver);
        Profile profilePage = new Profile(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailAndPassword(testUser.getEmail(), testUser.getPassword());
        loginPage.clickSignInButton();
        mainPage.clickPersonalAccountButton();
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(MainPage.BURGER_CONSTRUCTOR));
        profilePage.clickConstructorButton();
        mainPage.findOrderButton();
    }
    @After
    public void close() {
        ValidatableResponse response = userClient.login(new User(testUser.getEmail(), testUser.getPassword()));
        String accessToken = userClient.getToken(response);
        userClient.delete(accessToken);

        webDriver.quit();
    }
}
