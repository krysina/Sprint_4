package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class OrderPageForWhom {
    private final WebDriver driver;

    protected final By orderInputName = By.xpath(".//input[contains(@class,'Input_Responsible__1jDKN') and @placeholder='* Имя']");
    protected final By orderInputSurname = By.xpath(".//input[contains(@class,'Input_Responsible__1jDKN') and @placeholder='* Фамилия']");
    protected final By orderInputAddress = By.xpath(".//input[contains(@class,'Input_Responsible__1jDKN') and @placeholder='* Адрес: куда привезти заказ']");
    protected final By orderInputMetro = By.className("select-search__input");
    protected final By orderButtonMetro = By.cssSelector(".select-search__row");
    protected final By orderInputPhone = By.xpath(".//input[contains(@class,'Input_Responsible__1jDKN') and @placeholder='* Телефон: на него позвонит курьер']");
    protected final By orderButtonLater = By.className("Button_Middle__1CSJM");

    public OrderPageForWhom(WebDriver driver) {
        this.driver = driver;
    }

    //метод заполняет поле "Имя"
    public void nameOrder(String nameIssue) {
        driver.findElement(orderInputName).sendKeys(nameIssue);
    }

    //метод заполняет поле "Фамилия"
    public void surnameOrder(String surnameIssue) {
        driver.findElement(orderInputSurname).sendKeys(surnameIssue);
    }

    //метод заполняет поле "Адрес"
    public void addressOrder(String addressIssue) {
        driver.findElement(orderInputAddress).sendKeys(addressIssue);
    }

    //метод выбирает Станцию метро
    public void selectMetro(String indexNum) {
        driver.findElement(orderInputMetro).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonMetro));
        driver.findElement(By.xpath(".//li[@data-index='" + indexNum + "']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
               .until(driver -> (driver.findElement(orderInputMetro).getText() != null));
    }

    //метод заполняет поле "Телефон"
    public void phoneOrder(String phoneIssue) {
        driver.findElement(orderInputPhone).sendKeys(phoneIssue);
    }
    //Клик кнопки "Далее"
    public OrderPageAboutRent clickButtonLater() {
        driver.findElement(orderButtonLater).click();
        return new OrderPageAboutRent(driver);
    }

}
