package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageLocatorsAndMethods {
    private final WebDriver driver;

    // Локаторы форма "Для кого самокат"
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']"); // Поле "Имя"
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']"); // Поле "Фамилия"
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']"); // Поле "Адрес"
    private final By metroInput = By.className("select-search__input"); // Поле "Станция метро"
    private final By metroOption = By.className("select-search__row"); // Строка выбора первой найденной станции метро
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); // Поле "Телефон"
    private final By nextButton = By.xpath(".//button[text()='Далее']"); // Кнопка "Далее"

    // Локаторы формы "Про аренду"
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); // Поле "Когда привезти"
    private final By rentTimeDropdown = By.className("Dropdown-control"); // Выпадающий список "Срок аренды"
    private final By rentTimeOptionOneDay = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']"); // Опция "сутки"
    private final By rentTimeOptionTwoDays = By.xpath(".//div[@class='Dropdown-menu']/div[text()='двое суток']"); // Опция "двое суток"
    private final By blackColorCheckbox = By.id("black"); // Чекбокс "чёрный жемчуг"
    private final By greyColorCheckbox = By.id("grey"); // Чекбокс "серая безысходность"
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']"); // Поле "Комментарий"
    private final By finalOrderButton = By.xpath(".//button[text()='Заказать' and contains(@class, 'Button_Middle')]"); // Кнопка "Заказать" в форме
    private final By confirmOrderYesButton = By.xpath(".//button[text()='Да']"); // Кнопка "Да" в окне подтверждения

    // Локатор окна подтвержденного заказа
    private final By orderSuccessHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]"); // Заголовок "Заказ оформлен"

    // Конструктор класса
    public OrderPageLocatorsAndMethods(WebDriver driver) {
        this.driver = driver;
    }

    // Метод заполнения формы для кого самокат
    public void fillFirstOrderForm(String name, String surname, String address, String metroSearch, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);

        // Клик по полю метро, ввод текста для поиска(metroSearch) и(после) выбор первой строчки в выпадающем списке
        driver.findElement(metroInput).click();
        driver.findElement(metroInput).sendKeys(metroSearch);
        // Ждем, когда станция появится в списке на экране
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(metroOption));
        driver.findElement(metroOption).click();  //кликаем на нее

        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Метод заполнения формы "Про аренду"
    public void fillSecondOrderForm(String date, String duration, String color, String comment) {
        // Ожидаем появление формы про аренду по полю ввода даты
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(dateInput));

        driver.findElement(dateInput).sendKeys(date);

        // Нажимаем кнопку ESCAPE на клавиатуре робота для закрытия всплывшего календаря
        driver.findElement(dateInput).sendKeys(org.openqa.selenium.Keys.ESCAPE);

        // Выбор срока аренды
        driver.findElement(rentTimeDropdown).click();
        if ("сутки".equals(duration)) {
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(rentTimeOptionOneDay)).click();
        } else {
            new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(rentTimeOptionTwoDays)).click();
        }

        // Выбор цвета самоката
        if ("black".equals(color)) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("grey".equals(color)) {
            driver.findElement(greyColorCheckbox).click();
        }

        driver.findElement(commentInput).sendKeys(comment);
        // Нажатие кнопки "Заказать"
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(finalOrderButton)).click();

        // Нажатие кнопки «Да» в модальном окне подтверждения заказа
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(confirmOrderYesButton)).click();
    }

    // Метод проверяем, что заказ оформлен
    public boolean isOrderSuccessWindowDisplayed() {
        // Получаем текст из финального всплывающего окна
        String successText = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessHeader)).getText();
        // Возвращает true, если текст содержит фразу "Заказ оформлен"
        return successText.contains("Заказ оформлен");
    }
}