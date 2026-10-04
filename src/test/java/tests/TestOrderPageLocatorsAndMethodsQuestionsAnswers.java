package tests;

import pageobjects.LocatorsAndMethodsQuestionsAnswers;
import factory.DriverFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobjects.OrderPageLocatorsAndMethods;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOrderPageLocatorsAndMethodsQuestionsAnswers {

    // Подключаем вашу фабрику браузеров как правило JUnit 4
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    // Параметры для параметризованного теста
    private final String buttonType; // "top" (верхняя) или "bottom" (нижняя) кнопка заказа
    private final String name;
    private final String surname;
    private final String address;
    private final String metroSearch;
    private final String phone;
    private final String date;
    private final String duration; // "сутки" или "двое суток"
    private final String color; // "black" или "grey"
    private final String comment;

    // Конструктор тестового класса принимает данные для каждого круга проверки
    public TestOrderPageLocatorsAndMethodsQuestionsAnswers(String buttonType, String name, String surname, String address,
                                                           String metroSearch, String phone, String date, String duration,
                                                           String color, String comment) {
        this.buttonType = buttonType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroSearch = metroSearch;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    // Два набора данных для проверки двух разных флоу и двух точек входа
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                // 1 набор: верхняя кнопка, Иван, метро Бульвар Рокоссовского, аренда на сутки, черный цвет
                { "top", "Татьяна", "Иванова", "Адрес1", "Бульвар", "89993331629", "25.10.2026", "сутки", "black", "Комментарий1" },
                // 2 набор: нижняя кнопка, Анна, метро Сокольники, аренда на двое суток, серый цвет
                { "bottom", "Татьяна", "Петрова", "Адрес 2", "Сокольники", "89093031629", "26.10.2026", "двое суток", "grey", "Комментарий2" }
        };
    }

    @Test
    public void checkScooterOrderingFlow() {
        // Получаем готовый драйвер из фабрики
        WebDriver driver = driverFactory.getDriver();

        // Открываем главную страницу Самоката
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Инициализируем наши Page Object страницы
        LocatorsAndMethodsQuestionsAnswers mainPage = new LocatorsAndMethodsQuestionsAnswers(driver);
        OrderPageLocatorsAndMethods orderPage = new OrderPageLocatorsAndMethods(driver);

        // Убираем куки, чтобы они не перекрывали нижнюю кнопку заказа
        mainPage.clickCookieButton();

        // 1. Проверяем точку входа: кликаем по нужной кнопке «Заказать»
        if ("top".equals(buttonType)) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        // 2. Заполняем первую форму: «Для кого самокат»
        orderPage.fillFirstOrderForm(name, surname, address, metroSearch, phone);

        // 3. Заполняем вторую форму: «Про аренду»
        orderPage.fillSecondOrderForm(date, duration, color, comment);

        // 4. Проверяем финальный результат: появилось ли окно успешного заказа
        boolean isSuccess = orderPage.isOrderSuccessWindowDisplayed();
        assertTrue("Окно успешного оформления заказа не появилось на экране!", isSuccess);
    }

}