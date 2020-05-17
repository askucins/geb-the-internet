package org.askucins.internet

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
abstract class InternetSpec extends GebReportingSpec {
    @Rule
    TestName testName = new TestName()

    def setupSpec() {
        //System.setProperty('geb.build.baseUrl', 'http://localhost:9292/')
        System.setProperty('geb.build.baseUrl', 'https://the-internet.herokuapp.com/')

        assert System.getProperty('webdriver.chrome.driver')
        assert System.getProperty('webdriver.gecko.driver')
        assert System.getProperty('geb.build.reportsDir')
        assert System.getProperty('geb.env')
        assert System.getProperty('geb.build.baseUrl')

        log.info "=".padLeft(78, '=')
        log.info "webdriver.chrome.driver: {}", System.getProperty('webdriver.chrome.driver')
        log.info "webdriver.gecko.driver: {}", System.getProperty('webdriver.gecko.driver')
        log.info "geb.build.reportsDir: {}", System.getProperty('geb.build.reportsDir')
        log.info "geb.env: {}", System.getProperty('geb.env')
        log.info "geb.env: {}", System.getProperty('geb.build.baseUrl')

        log.info "About to reset the browser..."
        resetBrowser() //TODO check why it fails in 4.0.0-alpha-5
        log.info "About to clear cache and quit the running driver..."
        CachingDriverFactory.clearCacheAndQuitDriver()
    }

    def setup() {
        log.info ">>>> ${testName.methodName}"
    }
}
