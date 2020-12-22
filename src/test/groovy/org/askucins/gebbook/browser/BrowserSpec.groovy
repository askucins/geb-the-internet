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

    Browser myBrowser

    def cleanup() {
        log.info "Browser's properties: ${myBrowser.driver.properties}"
        myBrowser.quit()
    }

    def cleanupSpec() {
        testManager.resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }

    def "01 should work with a driver created implicitly"() {
        when:
        myBrowser = new Browser()
        then: 'driver is created implicitly'
        myBrowser.driver.toString()
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("01-driver-created-implicitly")
    }

    def "02 should work with default Firefox driver assigned to browser"() {
        when:
        myBrowser = new Browser()
        myBrowser.driver = new FirefoxDriver()
        then:
        myBrowser.driver.toString().startsWith('FirefoxDriver')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("02-firefox-driver-assigned-to-browser")
    }

    def "03 should work with default Chrome driver assigned to browser"() {
        when:
        myBrowser = new Browser()
        myBrowser.driver = new ChromeDriver()
        then:
        myBrowser.driver.toString().startsWith('Chrome')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("03-chrome-driver-assigned-to-browser")
    }

    def "04 should work with browser initialized with Headless Firefox driver"() {
        when:
        myBrowser = new Browser(driver: customizedFirefoxDriver([headless: true]))
        then:
        myBrowser.driver.toString().startsWith('FirefoxDriver')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("04-browser-initialized-with-firefox-headless")
    }

    def "05 should work with browser initialized with Headless Chrome drivers"() {
        when:
        myBrowser = new Browser(driver: customizedChromeDriver([headless: true]))
        then:
        myBrowser.driver.toString().startsWith('Chrome')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("05-browser-initialized-with-chrome-headless")
    }

    def "06 should work with browser initialized with Firefox driver"() {
        when:
        myBrowser = new Browser(driver: customizedFirefoxDriver([headless: false]))
        then:
        myBrowser.driver.toString().startsWith('FirefoxDriver')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("06-browser-initialized-with-firefox")
    }

    def "07 should work with browser initialized with Chrome driver"() {
        when:
        myBrowser = new Browser(driver: customizedChromeDriver([headless: false]))
        then:
        myBrowser.driver.toString().startsWith('Chrome')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("07-browser-initialized-with-chrome")
    }

}