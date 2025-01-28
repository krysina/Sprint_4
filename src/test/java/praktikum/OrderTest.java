package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class OrderTest {

    //Добавление необходимых полей
    private final String nameIssue;
    private final String surnameIssue;
    private final String addressIssue;
    private final String indexNum;
    private final String phoneIssue;
    private final String dateNum;
    private final String period;
    private final String color;
    private final String commentText;

    @Rule
    public DriverRule factory = new DriverRule();

    public OrderTest(String nameIssue, String surnameIssue, String addressIssue, String indexNum, String phoneIssue, String dateNum, String period, String color, String commentText) {
        this.nameIssue = nameIssue;
        this.surnameIssue = surnameIssue;
        this.addressIssue = addressIssue;
        this.indexNum = indexNum;
        this.phoneIssue = phoneIssue;
        this.dateNum = dateNum;
        this.period = period;
        this.color = color;
        this.commentText = commentText;
    }

    @Before
    public void closeCookies() {
        WebDriver driver = factory.getDriver();
        var MainPage = new MainPage(driver);
        MainPage.openMainPage();
        MainPage.acceptCookiesMainPage();
    }

    @Parameterized.Parameters
    public static Object[][] orderData() {
        //Генерируем тестовые данные
        return new Object[][] {
                {"Василий", "Васильев", "г. Москва, ул. Тверская, д. 5", "1", "+79102356789", "10", "сутки", "black", "Не звонить в домофон"},
                {"Иван", "Иванов", "г. Санкт-Петербург, пр-т Невский, д. 2", "2", "+79802356789", "20", "пятеро суток", "grey", "Звонить в домофон"}
        };
    }


    @Test
    public void orderOnButtonTop() {
        WebDriver driver = factory.getDriver();
        //Создать объект класса главной страницы MainPage
        var mainPage = new MainPage(driver);
        //Дождаться доступности верхней кнопки Заказать
        mainPage.waitForLoadOrderButtonTop();
        //клик по верхней кнопке "Заказать" для открытия страницы заказа "Для кого самокат"
        var orderPageForWhom = mainPage.clickOnOrderTop();
        //метод заполняет поле "Имя"
        orderPageForWhom.nameOrder(nameIssue);
        //метод заполняет поле "Фамилия"
        orderPageForWhom.surnameOrder(surnameIssue);
        //метод заполняет поле "Адрес"
        orderPageForWhom.addressOrder(addressIssue);
        //метод выбирает Станцию метро
        orderPageForWhom.selectMetro(indexNum);
        //метод заполняет поле "Телефон"
        orderPageForWhom.phoneOrder(phoneIssue);
        //Клик кнопки "Далее" для открытия страницы заказа "Про аренду"
        var orderPageAboutRent = orderPageForWhom.clickButtonLater();
        //метод заполняет поле "* Когда привезти самокат"
        orderPageAboutRent.whenOrder(dateNum);
        //метод заполняет поле "* Срок аренды"
        orderPageAboutRent.durationOrder(period);
        //метод заполняет поле "Цвет самоката"
        orderPageAboutRent.colorLabel(color);
        orderPageAboutRent.commentOrder(commentText);
        //Клик кнопки "Заказать"
        orderPageAboutRent.clickButtonOrder();
        //Клик кнопки "Да" подтверждения заказа
        orderPageAboutRent.clickOnButtonYes();
        //Получение текста сообщения об оформлении заказа
        String text = orderPageAboutRent.getTextModalCompleted();
        //Сверяем, что текст сообщения начинается с "Заказ оформлен"
        MatcherAssert.assertThat(text, startsWith(EnvConfig.EXPECTED_TEXT));

    }

    @Test
    public void orderOnButtonBottom() {
        WebDriver driver = factory.getDriver();
        //Создать объект класса главной страницы MainPage
        var mainPage = new MainPage(driver);
        //Дождаться доступности нижней кнопки Заказать
        mainPage.waitForLoadOrderButtonBottom();
        //Клик по нижней кнопке "Заказать" для открытия страницы заказа "Для кого самокат"
        var orderPageForWhom = mainPage.clickOnOrderBottom();
        //Метод заполняет поле "Имя"
        orderPageForWhom.nameOrder(nameIssue);
        //Метод заполняет поле "Фамилия"
        orderPageForWhom.surnameOrder(surnameIssue);
        //Метод заполняет поле "Адрес"
        orderPageForWhom.addressOrder(addressIssue);
        //Метод выбирает Станцию метро
        orderPageForWhom.selectMetro(indexNum);
        //Метод заполняет поле "Телефон"
        orderPageForWhom.phoneOrder(phoneIssue);
        //Клик кнопки "Далее" для открытия страницы заказа "Про аренду"
        var orderPageAboutRent = orderPageForWhom.clickButtonLater();
        //Метод заполняет поле "* Когда привезти самокат"
        orderPageAboutRent.whenOrder(dateNum);
        //Метод заполняет поле "* Срок аренды"
        orderPageAboutRent.durationOrder(period);
        //Метод заполняет поле "Цвет самоката"
        orderPageAboutRent.colorLabel(color);
        orderPageAboutRent.commentOrder(commentText);
        //Клик кнопки "Заказать"
        orderPageAboutRent.clickButtonOrder();
        //Клик кнопки "Да" подтверждения заказа
        orderPageAboutRent.clickOnButtonYes();
        //Получение текста сообщения об оформлении заказа
        String text = orderPageAboutRent.getTextModalCompleted();
        //Сверяем, что текст сообщения начинается с "Заказ оформлен"
        MatcherAssert.assertThat(text, startsWith(EnvConfig.EXPECTED_TEXT));

    }

    @After
    public void tearDown() {
        WebDriver driver = factory.getDriver();
        driver.quit();
    }
}