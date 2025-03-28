package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    protected final By orderButtonTop = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    protected final By orderButtonBottom = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]");
    protected final By cookieButton = By.id("rcc-confirm-button");
    protected final String scriptScrollText = "window.scrollBy(0,document.body.scrollHeight)";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //клик по верхней кнопке "Заказать" открывает страницу заказа "Для кого самокат"
    public OrderPageForWhom clickOnOrderTop() {
        driver.findElement(orderButtonTop).click();
        return new OrderPageForWhom(driver);
    }
    //клик по нижней кнопке "Заказать" открывает страницу заказа "Для кого самокат"
    public OrderPageForWhom clickOnOrderBottom() {
        driver.findElement(orderButtonBottom).click();
        return new OrderPageForWhom(driver);
    }


    public MainPage acceptCookiesMainPage() {
        waitForCookiesFloater();
        driver.findElement(cookieButton).click();
        waitForCookiesFloaterToDisappear();
        return this;
    }

    private void waitForCookiesFloater() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
    }

    private void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }

    //Дождаться загрузки верхней кнопки Заказать
    public void waitForLoadOrderButtonTop() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonTop));
    }

    //Дождаться загрузки нижней кнопки Заказать
    public void waitForLoadOrderButtonBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(scriptScrollText);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
        .until(ExpectedConditions.visibilityOfElementLocated(orderButtonBottom));
    }

    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }
}