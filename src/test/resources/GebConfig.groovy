import org.openqa.selenium.firefox.FirefoxDriver

import static org.askucins.utils.WebDriverConfiguration.chromeDriver

switch (System.getProperty('org.askucins.webdriver')) {
    case 'firefox':
        driver = { new FirefoxDriver() }
        break
    case 'chrome':
        driver = { chromeDriver([headless: false]) }
        break
    default:
        driver = { chromeDriver([headless: true]) }
}



