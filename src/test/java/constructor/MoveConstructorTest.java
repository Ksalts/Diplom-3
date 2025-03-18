package constructor;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPage;

public class MoveConstructorTest extends base.BaseTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(webDriver);
    }

    @Test
    @DisplayName("Переходы к разделу «Начинки».")
    public void navigationToFillings() {
        mainPage.clickFillingTab();
        mainPage.checkFillingList();
    }
    @Test
    @DisplayName("Переходы к разделу «Соусы».")
    public void navigationToSauces() {
        mainPage.clickSauceTab();
        mainPage.checkSauceList();
    }
    @Test
    @DisplayName("Переход к разделу «Булки».")
    public void navigationToBuns() {
        mainPage.checkBunList();
    }
    @After
    public void close() {
        webDriver.quit();
    }
}
