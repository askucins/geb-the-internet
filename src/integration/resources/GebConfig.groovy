import geb.Browser
import geb.navigator.Navigator
import geb.navigator.event.NavigatorEventListenerSupport
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.askucins.utils.ChromeDriverCustomization.chromeDriver
import static org.askucins.utils.TestDriver.*

Logger log = LoggerFactory.getLogger("GebConfig")

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

navigatorEventListener = new NavigatorEventListenerSupport() {
    void afterClick(Browser browser, Navigator navigator) {
        // TODO This actually breaks those dynamic-navigator tests!!!
        //log.debug "${navigator*.tag()} was clicked"
        log.debug "Something was clicked!"
    }
}