package registration;
import io.qameta.allure.Description;
import user.User;
import user.UserClient;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;

public class RegistrationTest extends base.BaseTest {
    protected UserClient client = new UserClient();
    protected Faker faker = new Faker();
    protected String name = faker.name().firstName();
    protected String email = faker.internet().emailAddress();
    protected String password = faker.internet().password(6,9);

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registrationSuccess(){
        MainPage mainPage = new MainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);

        mainPage.clickPersonalAccountButton();
        loginPage.clickLittleRegisterButton();
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
        loginPage.checkRestorePasswordButton();
    }
    @After
    public void close(){
        ValidatableResponse response = client.login(new User(email, password));
        String accessToken = client.getToken(response);
        client.delete(accessToken);

        webDriver.quit();
    }


}
