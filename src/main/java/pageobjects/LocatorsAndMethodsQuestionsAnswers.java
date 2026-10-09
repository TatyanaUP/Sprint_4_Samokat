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
    private final By cookieButton = By.id("rcc-confirm-button"); //кнопка куки "да все привыкли"
    private final By accordionSection = By.className("accordion"); //блок вопросов




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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element); //скролл до блока вопросов/ответов
    }

    // Метод для клика по вопросу
    public void clickQuestion(int index) {
        By questionLocator = By.id("accordion__heading-" + index);
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(questionLocator)).click();
    }



    // Методы, получающие текст ответов:
    public String getAnswerText(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).getText();
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);//нажимаем кнопку
    }
}