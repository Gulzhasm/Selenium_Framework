package pages;


import configs.browser.ElementOperations;
import io.qameta.allure.Step;

import java.time.Duration;

import static constants.Constants.configProperties;


public abstract class BasePage extends ElementOperations {
    public BasePage() {
        super(null);
    }

    public void openByUrl() {
        initDriver().get(configProperties.baseURL());
        initDriver().manage().window().fullscreen();
        initDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
}