package praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.FaqPage;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest {
    private final String itemId;
    private final String answer;

    @ClassRule
    public static DriverRule factory = new DriverRule();

    public FaqTest(String itemId, String answer) {
        this.itemId = itemId;
        this.answer = answer;
    }

    @BeforeClass
    public static void closeCookies() {
        WebDriver driver = factory.getDriver();
        var FaqPage = new FaqPage(driver);
        FaqPage.openFaqPage();
        FaqPage.acceptCookiesFaqPage();
    }

    @Parameterized.Parameters
    public static Object[][] faqData() {
        return new Object[][]{
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}

        };
    }

    @Test
    public void checkOnFaqItem() {
        WebDriver driver = factory.getDriver();
        var FaqPage = new FaqPage(driver);
        FaqPage.checkAnswerIsInvisible(itemId);
        FaqPage.clickOnQuestion(itemId);
        FaqPage.waitForAnswer(itemId);
        String text = FaqPage.getAnswer(itemId);
        System.out.println(text);
        assertEquals(answer, text);
    }

    @AfterClass
    public static void tearDown() {
        WebDriver driver = factory.getDriver();
        driver.quit();
    }
}
