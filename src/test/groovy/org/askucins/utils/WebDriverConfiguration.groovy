package org.askucins.utils

import com.browserup.bup.BrowserUpProxy
import com.browserup.bup.client.ClientUtil
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType

class WebDriverConfiguration {

/*
 See more about Chrome options in regards to content settings:
 https://developer.chrome.com/extensions/contentSettings
 */

    enum ChromeGeoLocation {
        Allow(1), Block(2)

        Integer value

        ChromeGeoLocation(value) {
            this.value = value
        }
    }

    static ChromeOptions defaultChromeOptions() {
        ChromeOptions options = new ChromeOptions()
        options.setBinary('/opt/google/chrome/google-chrome')
        //options.addArguments("disable-gpu")
        //options.addArguments('--auto-ssl-client-auth')
        options.addArguments('start-maximized')
        return options
    }

    static ChromeDriver chromeDriver(Map config) {
        ChromeOptions options = defaultChromeOptions()

        if (config.shareLocation) {
            options.setExperimentalOption("prefs", [profile: [default_content_setting_values: [geolocation: ChromeGeoLocation.Allow.value]]])
        } else {
            options.setExperimentalOption("prefs", [profile: [default_content_setting_values: [geolocation: ChromeGeoLocation.Block.value]]])
        }

        if (config?.headless) {
            // See more at http://blog.faraday.io/headless-chromium-with-capybara-and-selenium-webdriver/
            options.addArguments("headless")
        }

        if (config?.proxy) {
            // Host defined explicitly to enforce using localhost/127.0.0.1
            def seleniumProxy = ClientUtil.createSeleniumProxy(config.proxy as BrowserUpProxy, InetAddress.getByName('localhost'))
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true) //cert...
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true) //cert...
            options.setCapability(CapabilityType.PROXY, seleniumProxy)
        }

        ChromeDriverService.Builder serviceBuilder = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .usingDriverExecutable(new File('/opt/webdriver/chromedriver'))
        new ChromeDriver(serviceBuilder.build(), options)
    }

}
