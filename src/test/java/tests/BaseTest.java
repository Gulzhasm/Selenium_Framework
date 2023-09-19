package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import pages.LoginPage;


import static configs.browser.BrowserConfigurationImpl.closeDriver;

public class BaseTest {
    protected LoginPage loginPage = new LoginPage();

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        loginPage.openByUrl();
    }

    @AfterAll
    static void tearDown() {
        closeDriver();
    }

}

