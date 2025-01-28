package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class OrderPageAboutRent {
    private final WebDriver driver;

    protected final By orderInputDate = By.className("react-datepicker__input-container");
    protected final By orderDatepicker = By.className("react-datepicker");
    protected final By orderRentDuration = By.className("Dropdown-root");
    protected final By orderDropdown = By.className("Dropdown-menu");
    protected final By orderSelectedDuration = By.xpath(".//div[contains(@class, 'is-selected')]");
    protected final By orderSelectedColor = By.cssSelector(".Order_FilledContainer__2MKAk");
    protected final By commentCourier = By.xpath(".//input[@type='text' and @placeholder='Комментарий для курьера']");
    protected final By orderButton = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    protected final By orderModal = By.className("Order_Modal__YZ-d3");
    protected final By buttonYes = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    protected final By placedModal = By.className("Order_ModalHeader__3FDaJ");

    public OrderPageAboutRent(WebDriver driver) {
        this.driver = driver;
    }

    //метод заполняет поле "* Когда привезти самокат"
    public void whenOrder(String dateNum) {
        driver.findElement(orderInputDate).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderDatepicker));
                driver.findElement(By.xpath(".//div[contains(@class,'react-datepicker__day--0" + dateNum +"')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(driver -> (driver.findElement(orderInputDate).getText() != null));
    }

    //метод заполняет поле "* Срок аренды"
    public void durationOrder(String period) {
        driver.findElement(orderRentDuration).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderDropdown));
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(driver -> (driver.findElement(orderSelectedDuration).getText() != null));
    }

    //метод заполняет поле "Цвет самоката"
    public void colorLabel(String color) {
        driver.findElement(By.xpath(".//label[@for='" + color + "']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderSelectedColor));
    }

    //метод заполняет поле "Комментарий для курьера"
    public void commentOrder(String commentText) {
        driver.findElement(commentCourier).sendKeys(commentText);
    }

    //Клик кнопки "Заказать"
    public void clickButtonOrder() {
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderModal));
    }

    //Клик кнопки "Да" подтверждения заказа
    public void clickOnButtonYes() {
        driver.findElement(buttonYes).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(placedModal));
    }

    //Получение текста сообщения об оформлении заказа "Заказ оформлен"
    public String getTextModalCompleted () {
        return driver.findElement(placedModal).getText();
    }
}
