import org.openqa.selenium.firefox.FirefoxDriver

import static org.askucins.utils.ChromeDriverCustomization.chromeDriver
import static org.askucins.utils.TestDriver.*

switch (System.getProperty('org.askucins.webdriver')) {
    case FIREFOX.toString():
        driver = { new FirefoxDriver() }
        break
    case CHROME.toString():
        driver = { chromeDriver([headless: false]) }
        break
    case CHROMEHEADLESS.toString():
        driver = { chromeDriver([headless: true]) }
        break
    default:
        driver = { chromeDriver([headless: true]) }
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

