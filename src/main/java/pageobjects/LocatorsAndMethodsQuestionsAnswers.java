package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class LocatorsAndMethodsQuestionsAnswers {
    private final WebDriver driver;

    //Кнопки на странице
    private final By topOrderButton = By.xpath(".//div[contains(@class, 'Header_Header')]//button[text()='Заказать']");  //кнопка заказать в шапке(верхний блок)
    private final By bottomOrderButton = By.xpath("(.//button[text()='Заказать'])[2]");//кнопка заказать в блоке как это работает(3 блок)
    private final By cookieButton = By.id("rcc-confirm-btn"); //кнопка куки "да все привыкли"
    private final By accordionSection = By.className("accordion"); //блок вопросов

    //Локаторы для каждого вопроса(пронумеруем по индексам кнопок):
    private final By question0 = By.id("accordion__heading-0"); //Сколько это стоит? И как оплатить?
    private final By question1 = By.id("accordion__heading-1"); //Хочу сразу несколько самокатов! Так можно?
    private final By question2 = By.id("accordion__heading-2"); //Как рассчитывается время аренды?
    private final By question3 = By.id("accordion__heading-3"); //Можно ли заказать самокат прямо на сегодня?
    private final By question4 = By.id("accordion__heading-4"); //Можно ли продлить заказ или вернуть самокат раньше?
    private final By question5 = By.id("accordion__heading-5"); //Вы привозите зарядку вместе с самокатом?
    private final By question6 = By.id("accordion__heading-6"); //Можно ли отменить заказ?
    private final By question7 = By.id("accordion__heading-7"); //Я жизу за МКАДом, привезёте?

    //Локаторы для каждого ответа(тоже пронумеруем по индексам):
    private final By answer0 = By.id("accordion__panel-0"); //Сутки — 400 рублей. Оплата курьеру — наличными или картой.
    private final By answer1 = By.id("accordion__panel-1"); //Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.
    private final By answer2 = By.id("accordion__panel-2"); //Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.
    private final By answer3 = By.id("accordion__panel-3"); //Только начиная с завтрашнего дня. Но скоро станем расторопнее.
    private final By answer4 = By.id("accordion__panel-4"); //Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.
    private final By answer5 = By.id("accordion__panel-5"); //Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.
    private final By answer6 = By.id("accordion__panel-6"); //Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.
    private final By answer7 = By.id("accordion__panel-7"); //Да, обязательно. Всем самокатов! И Москве, и Московской области.

    // Конструктор класса
    public LocatorsAndMethodsQuestionsAnswers(WebDriver driver) {
        this.driver = driver;
    }

    // Вспомогательные методы страницы
    public void clickCookieButton() {
        if (driver.findElements(cookieButton).size() > 0) {
            driver.findElement(cookieButton).click(); //клик по кукам
        }
    }

    public void scrollToQuestions() {
        WebElement element = driver.findElement(accordionSection);
        ((JavascriptExecutor) driver).executeScript("arguments.scrollIntoView();", element); //скролл до блока вопросов/ответов
    }

    // Методы, раскрывающие каждый вопрос
    public void clickQuestion0() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question0)).click();
    }

    public void clickQuestion1() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question1)).click();
    }

    public void clickQuestion2() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question2)).click();
    }

    public void clickQuestion3() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question3)).click();
    }

    public void clickQuestion4() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question4)).click();
    }

    public void clickQuestion5() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question5)).click();
    }

    public void clickQuestion6() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question6)).click();
    }

    public void clickQuestion7() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(question7)).click();
    }

    // Методы, получающие текст ответов:
    public String getAnswerText0() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer0)).getText();
    }

    public String getAnswerText1() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer1)).getText();
    }

    public String getAnswerText2() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer2)).getText();
    }

    public String getAnswerText3() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer3)).getText();
    }

    public String getAnswerText4() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer4)).getText();
    }

    public String getAnswerText5() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer5)).getText();
    }

    public String getAnswerText6() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer6)).getText();
    }

    public String getAnswerText7() {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answer7)).getText();
    }

    // Методы кнопок заказа
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        //ждем 2ю кнопку(нижнюю)
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(bottomOrderButton));
        WebElement element = driver.findElement(bottomOrderButton); // кнопка заказать
        new Actions(driver).moveToElement(element).perform(); //код для наведения фокуса
        //ждем и нажимаем кнопку
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element); //нажимаем кнопку
    }
}