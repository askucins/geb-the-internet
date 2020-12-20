package org.askucins.gebbook.browser

import geb.Browser
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import spock.lang.Stepwise

import static org.askucins.utils.CustomizedChromeDriver.customizedChromeDriver
import static org.askucins.utils.CustomizedFirefoxDriver.customizedFirefoxDriver

@Slf4j
@Stepwise
class BrowserSpec extends GebSpec {

    static url = 'https://ipconfig.io'
    static slug = 'ipconfig'

    Browser browser_

    def cleanup() {
        log.info "Browser's properties: ${browser_.driver.properties}"
        browser_.quit()
    }

    def cleanupSpec() {
        testManager.resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }

    def "01 should work with a driver created implicitly"() {
        when:
        browser_ = new Browser()
        then: 'driver is created implicitly'
        browser_.driver.toString()
        when:
        browser_.go(url)
        then:
        browser_.currentUrl.contains(slug)
        cleanup:
        browser_.report("01-driver-created-implicitly")
    }

    def "02 should work with default Firefox driver assigned to browser"() {
        when:
        browser_ = new Browser()
        browser_.driver = new FirefoxDriver()
        then:
        browser_.driver.toString().startsWith('FirefoxDriver')
        when:
        browser_.go(url)
        then:
        browser_.currentUrl.contains(slug)
        cleanup:
        browser_.report("02-firefox-driver-assigned-to-browser")
    }

    def "03 should work with default Chrome driver assigned to browser"() {
        when:
        browser_ = new Browser()
        browser_.driver = new ChromeDriver()
        then:
        browser_.driver.toString().startsWith('Chrome')
        when:
        browser_.go(url)
        then:
        browser_.currentUrl.contains(slug)
        cleanup:
        browser_.report("03-chrome-driver-assigned-to-browser")
    }

    def "04 should work with browser initialized with Headless Firefox driver"() {
        when:
        browser_ = new Browser(driver: customizedFirefoxDriver([headless: true]))
        then:
        browser_.driver.toString().startsWith('FirefoxDriver')
        when:
        browser_.go(url)
        then:
        browser_.currentUrl.contains(slug)
        cleanup:
        browser_.report("04-browser-initialized-with-firefox-headless")
    }

    def "05 should work with browser initialized with Headless Chrome drivers"() {
        when:
        browser_ = new Browser(driver: customizedChromeDriver([headless: true]))
        then:
        browser_.driver.toString().startsWith('Chrome')
        when:
        browser_.go(url)
        then:
        browser_.currentUrl.contains(slug)
        cleanup:
        browser_.report("05-browser-initialized-with-chrome-headless")
    }

    def "06 should work with browser initialized with Firefox driver"() {
        when:
        browser_ = new Browser(driver: customizedFirefoxDriver([headless: false]))
        then:
        browser_.driver.toString().startsWith('FirefoxDriver')
        when:
        browser_.go(url)
        then:
        browser_.currentUrl.contains(slug)
        cleanup:
        browser_.report("06-browser-initialized-with-firefox")
    }

    def "07 should work with browser initialized with Chrome driver"() {
        when:
        browser_ = new Browser(driver: customizedChromeDriver([headless: false]))
        then:
        browser_.driver.toString().startsWith('Chrome')
        when:
        browser_.go(url)
        then:
        browser_.currentUrl.contains(slug)
        cleanup:
        browser_.report("07-browser-initialized-with-chrome")
    }

}