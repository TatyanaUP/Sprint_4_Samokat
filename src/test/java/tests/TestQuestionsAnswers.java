package tests;

import factory.DriverFactory;
import pageobjects.LocatorsAndMethodsQuestionsAnswers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestQuestionsAnswers {

    // Подключаем DriverFactory как правило JUnit
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    private final int index;
    private final String expectedAnswer;

    public TestQuestionsAnswers(int index, String expectedAnswer) {
        this.index = index;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                { 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой." },
                { 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." },
                { 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30." },
                { 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее." },
                { 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." },
                { 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится." },
                { 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." },
                { 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области." }
        };
    }

    @Test
    public void checkAccordionQuestionsAndAnswers() {
        // Берем готовый драйвер из нашей фабрики
        WebDriver driver = driverFactory.getDriver();

        // Открываем сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");

        LocatorsAndMethodsQuestionsAnswers steps = new LocatorsAndMethodsQuestionsAnswers(driver);

        steps.clickCookieButton(); //плашку куки закрываем
        steps.scrollToQuestions(); //скролл до блока

        String actualAnswer = ""; //переменная для ответа

        if (index == 0) { steps.clickQuestion0(); actualAnswer = steps.getAnswerText0(); }
        else if (index == 1) { steps.clickQuestion1(); actualAnswer = steps.getAnswerText1(); }
        else if (index == 2) { steps.clickQuestion2(); actualAnswer = steps.getAnswerText2(); }
        else if (index == 3) { steps.clickQuestion3(); actualAnswer = steps.getAnswerText3(); }
        else if (index == 4) { steps.clickQuestion4(); actualAnswer = steps.getAnswerText4(); }
        else if (index == 5) { steps.clickQuestion5(); actualAnswer = steps.getAnswerText5(); }
        else if (index == 6) { steps.clickQuestion6(); actualAnswer = steps.getAnswerText6(); }
        else if (index == 7) { steps.clickQuestion7(); actualAnswer = steps.getAnswerText7(); }

        assertEquals("Текст ответа под индексом " + index + " не совпал!", expectedAnswer, actualAnswer);
    }
}
