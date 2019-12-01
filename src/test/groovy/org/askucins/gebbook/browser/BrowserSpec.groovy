package org.askucins.gebbook.browser

import geb.Browser
import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import spock.lang.Stepwise

import static org.askucins.utils.ChromeDriverCustomization.chromeDriver

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

    def "start with Default"() {
        when:
        myBrowser = new Browser()
        then: 'driver is created implicitly'
        myBrowser.driver.toString()
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("At ifconfig via Default")
    }

    def "start with Firefox"() {
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
        myBrowser.report("At ifconfig via Firefox")
    }

    def "start with bare Chrome"() {
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
        myBrowser.report("At ifconfig via bare Chrome")
    }

    def "start with Chrome"() {
        when:
        myBrowser = new Browser(driver: chromeDriver([headless: false]))
        then:
        myBrowser.driver.toString().startsWith('Chrome')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("At ifconfig via Chrome")
    }

    def "start with ChromeHeadless"() {
        when:
        myBrowser = new Browser(driver: chromeDriver([headless: true]))
        then:
        myBrowser.driver.toString().startsWith('Chrome')
        when:
        myBrowser.go(url)
        then:
        myBrowser.currentUrl.contains(slug)
        cleanup:
        myBrowser.report("At ifconfig via Chrome Headless")
    }
}