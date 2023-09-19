package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static constants.Constants.configProperties;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        loginPage.fillUserName(configProperties.username());
        loginPage.fillUserPassword(configProperties.password());
        loginPage.clickSignInButton();
        Assertions.assertTrue(loginPage.verifyTopNavBarIdentityVisible());
    }

    @Test
    public void logoutTest() {
        loginPage.clickLogOutButton();
    }
}
