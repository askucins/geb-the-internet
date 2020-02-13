package org.askucins.utils

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class CustomizedFirefoxDriver {
    static FirefoxDriver firefoxDriver(Map config) {
        FirefoxOptions options = new FirefoxOptions()
        if (config?.headless) {
            options.addArguments('--window-size 1920,1080')
            options.addArguments("--headless")
        }
        new FirefoxDriver(options)
    }
}
