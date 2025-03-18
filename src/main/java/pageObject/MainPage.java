package pageObject;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static constants.Constants.BASE_URI;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class MainPage {
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By signInButtonMainPage = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By makeAnOrderButton = By.xpath(".//button[contains(text(),'Оформить заказ')]");
    private final By bunTab = By.xpath(".//div[span[text()='Булки']]");
    private final By saucesTab = By.xpath(".//div[span[text()='Соусы']]");
    private final By fillingsTab = By.xpath(".//*[text()='Начинки']");
    public static final By BURGER_CONSTRUCTOR = By.xpath("//p[text()='Конструктор']");

    private final WebDriver webDriver;

    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    public void open(){
        webDriver.get(BASE_URI);
    }
    public void clickPersonalAccountButton(){
        webDriver.findElement(personalAccountButton).click();
    }
    public void clickSignInButton(){
        webDriver.findElement(signInButtonMainPage);
    }

    public void findOrderButton(){
        String textOrderButton = webDriver.findElement(makeAnOrderButton).getText();
        MatcherAssert.assertThat(textOrderButton, startsWith("Оформить заказ"));
    }

    public void clickBunTab(){
        webDriver.findElement(bunTab).click();
    }

    public void clickSauceTab(){
        webDriver.findElement(saucesTab).click();
    }

    public void clickFillingTab(){
        webDriver.findElement(fillingsTab).click();
    }

    public void checkBunList(){
       String text = webDriver.findElement(By.xpath(".//div[@style]/div[1]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("tab_tab__1SPyG"));
    }
    public void checkSauceList(){
        String text = webDriver.findElement(By.xpath(".//div[@style]/div[2]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("tab_tab__1SPyG"));
    }
    public void checkFillingList(){
        String text = webDriver.findElement(By.xpath(".//div[@style]/div[3]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("tab_tab__1SPyG"));
    }


}
