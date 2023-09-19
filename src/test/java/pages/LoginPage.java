package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {
    private final By inputEmail = By.id("Input_Email");
    private final By inputPassword = By.id("Input_Password");
    private final By signInButton = By.xpath("//button[text()='Sign in']");
    private final By identityTopNavBar = By.xpath("//li/div/a[contains(@href,'/ChangePassword')]");
    private final By logoutButton = By.xpath("//form[@id='logoutForm']//button");


    public void fillUserName(String text) {
        findAll(inputEmail).get(0).sendKeys(text);
    }

    public void fillUserPassword(String text) {
        findAll(inputPassword).get(0).sendKeys(text);
    }

    public void clickSignInButton() {
        find(signInButton).click();
    }

    @Step("Verify if an user logged in and Navbar username is displayed")
    public Boolean verifyTopNavBarIdentityVisible() {
        return find(identityTopNavBar).isDisplayed();
    }

    @Step("Click on Sign Out button")
    public void clickLogOutButton() {
        findAll(logoutButton).get(0).click();
    }
}