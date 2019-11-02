package org.askucins.internet


import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
abstract class InternetSpec extends GebSpec {
    def setupSpec() {
        assert System.getProperty('webdriver.chrome.driver')
        assert System.getProperty('webdriver.gecko.driver')
        assert System.getProperty('geb.build.reportsDir')
        assert System.getProperty('geb.build.baseUrl')

        log.info "=".padLeft(78, '=')
        log.info "Webdriver path to chromedriver: " + System.getProperty('webdriver.chrome.driver')
        log.info "Webdriver path to geckodriver: " + System.getProperty('webdriver.gecko.driver')
        log.info "Geb reports in: " + System.getProperty('geb.build.reportsDir')
        log.info "Geb baseUrl: " + System.getProperty('geb.build.baseUrl')
    }

    def cleanupSpec() {
        browser.quit()
    }
}
