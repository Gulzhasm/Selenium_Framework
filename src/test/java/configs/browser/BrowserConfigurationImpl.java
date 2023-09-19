package configs.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static constants.Constants.configProperties;

public class BrowserConfigurationImpl implements BrowserConfiguration {
    private static WebDriver webDriver;

    @Override
    public WebDriver initDriver() {
        ChromeOptions options = new ChromeOptions();
        if (webDriver == null) {
            switch (configProperties.browser()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    break;
                case "headless_chrome":
                    options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-features=VizDisplayCompositor");
                    options.addArguments("--incognito");
                    options.addArguments("enable-automation");
                    options.addArguments("--headless");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--dns-prefetch-disable");
                    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    options.addArguments("enable-features=NetworkServiceInProcess");
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(options);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
                    webDriver = new ChromeDriver(options);
            }
        }
        return webDriver;
    }

    public static void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
