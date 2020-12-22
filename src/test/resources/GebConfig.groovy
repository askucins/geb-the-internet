import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.askucins.utils.CustomizedChromeDriver.customizedChromeDriver
import static org.askucins.utils.CustomizedFirefoxDriver.customizedFirefoxDriver

Logger log = LoggerFactory.getLogger("GebConfig")

// Default driver
driver = { customizedChromeDriver([headless: true]) }

environments {
    firefoxBare {
        driver = { new FirefoxDriver() }
    }
    firefox {
        driver = { customizedFirefoxDriver([headless: false]) }
    }
    firefoxHeadless {
        driver = { customizedFirefoxDriver([headless: true]) }
    }
    chromeBare {
        driver = { new ChromeDriver() }
    }
    chrome {
        driver = { customizedChromeDriver([headless: false]) }
    }
    chromeHeadless {
        driver = { customizedChromeDriver([headless: true]) }
    }
}

//cacheDriver = false
//cacheDriverPerThread = true

requirePageAtCheckers = true
atCheckWaiting = true

waiting {
    presets {
        slow {
            timeout = 30
            retryInterval = 1
        }
        quick {
            timeout = 5
        }
    }
}
