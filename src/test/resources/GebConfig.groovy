import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

import static org.askucins.utils.CustomizedChromeDriver.chromeDriver
import static org.askucins.utils.CustomizedFirefoxDriver.firefoxDriver
import static org.askucins.utils.TestDriver.*

switch (System.getProperty('org.askucins.webdriver')) {
    case FIREFOX.toString():
        driver = { new FirefoxDriver() }
        break
    case FIREFOXHEADLESS.toString():
        driver = { firefoxDriver([headless: true]) }
        break
    case CHROME.toString():
        driver = { chromeDriver([headless: false]) }
        break
    case CHROMEHEADLESS.toString():
        driver = { chromeDriver([headless: true]) }
        break
    default:
        driver = { new ChromeDriver() }
}

waiting {
    presets {
        slow {
            timeout = 20
            retryInterval = 1
        }
        quick {
            timeout = 1
        }
    }
}

