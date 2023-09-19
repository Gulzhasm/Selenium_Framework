package configs.browser;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElementOperations extends BrowserConfigurationImpl implements WebElement {
    WebElement element;

    public ElementOperations(WebElement element) {
        this.element = element;
    }

    public void click() {
        WebDriverWait wait = new WebDriverWait(initDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void jsClick() {
        JavascriptExecutor executor = (JavascriptExecutor) initDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void submit() {
        element.submit();
    }

    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }

    public void clear() {
        element.clear();
    }

    public String getTagName() {
        return element.getTagName();
    }

    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    public boolean isEnabled() {
        return element.isEnabled();
    }

    public String getText() {
        return element.getText();
    }

    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    public String getElementText(By by) {
        return find(by).getText();
    }

    public Point getLocation() {
        return element.getLocation();
    }

    public WebElement getWebElement() {
        return element;
    }

    public Dimension getSize() {
        return element.getSize();
    }

    public Rectangle getRect() {
        return element.getRect();
    }

    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    public ElementOperations find(By by) {
        ElementOperations element = null;
        WebDriverWait wait = new WebDriverWait(initDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (this.element != null) {
                element = new ElementOperations(this.element.findElement(by));
            } else {
                element = new ElementOperations(initDriver().findElement(by));
            }
        } catch (WebDriverException | NullPointerException e) {
        }
        return element;
    }

    public List<ElementOperations> findAll(By by) {
        List<ElementOperations> list = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(initDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (this.element != null) {
                list.addAll(this.element.findElements(by).stream().map(ElementOperations::new).collect(Collectors.toList()));
            } else {
                list.addAll(initDriver().findElements(by).stream().map(ElementOperations::new).collect(Collectors.toList()));
            }
        } catch (WebDriverException | NullPointerException e) {
        }
        return list;
    }
}