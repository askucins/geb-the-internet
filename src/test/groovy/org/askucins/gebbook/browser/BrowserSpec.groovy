package org.askucins.gebbook.browser

import geb.Browser
import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

@Slf4j
class BrowserSpec extends GebSpec {
    def "should it work"() {
        given:
        Browser myBrowser
        when:
        myBrowser = new Browser()
        myBrowser.driver = new FirefoxDriver()
        then:
        myBrowser.driver.toString().startsWith('FirefoxDriver')
        when:
        myBrowser.quit()
        myBrowser = new Browser(driver: new ChromeDriver())
        then:
        myBrowser.driver.toString().startsWith('ChromeDriver')
        cleanup:
        log.info "Browser's properties: ${myBrowser.driver}"
        /*
        [
            baseUrl:null,
            driver:ChromeDriver: chrome on LINUX (b9fd3f240444c9cd838fdec316970c91),
            navigatorFactory:geb.navigator.factory.BrowserBackedNavigatorFactory@1e788980,
            sessionStorage:geb.webstorage.SessionStorage@3a160be7,
            augmentedDriver:ChromeDriver: chrome on LINUX (b9fd3f240444c9cd838fdec316970c91),
            seleniumWebStorage:ChromeDriver: chrome on LINUX (b9fd3f240444c9cd838fdec316970c91),
            currentUrl:data:,,
            js:geb.js.JavascriptInterface@1524990a,
            class:class geb.Browser,
            availableWindows:[CDwindow-3A588277A9C4EEB995AF8A042B9B8B21],
            config:geb.Configuration@42e26cb,
            reportGroupDir:reports,
            page:geb.Page,
            localStorage:geb.webstorage.LocalStorage@5e409b47,
            currentWindow:CDwindow-3A588277A9C4EEB995AF8A042B9B8B21,
            pageEventListener:geb.CompositePageEventListener@5403ea5b
         ]
         */
        myBrowser.quit()
    }
}