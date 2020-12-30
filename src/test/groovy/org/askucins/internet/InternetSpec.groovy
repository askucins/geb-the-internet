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
        // According to https://gebish.org/manual/current/#base-url
        // Base url with trailing slash, relative urls without leading slash

        //System.setProperty('geb.build.baseUrl', 'http://localhost:9292/')
        System.setProperty('geb.build.baseUrl', 'https://the-internet.herokuapp.com/')

        assert System.getProperty('webdriver.chrome.driver')
        assert System.getProperty('webdriver.gecko.driver')
        assert System.getProperty('geb.build.reportsDir')
        assert System.getProperty('geb.build.baseUrl')
        //assert System.getProperty('geb.env')

        log.info "=".padLeft(78, '=')
        log.info "webdriver.chrome.driver: {}", System.getProperty('webdriver.chrome.driver')
        log.info "webdriver.gecko.driver: {}", System.getProperty('webdriver.gecko.driver')
        log.info "geb.build.reportsDir: {}", System.getProperty('geb.build.reportsDir')
        log.info "geb.build.baseUrl: {}", System.getProperty('geb.build.baseUrl')
        log.info "geb.env: {}", System.getProperty('geb.env') ?: '[not set explicitly!]'
    }

    def setup() {
        log.info ">>>> ${testName.methodName}"
    }

    def cleanupSpec() {
        log.info "Closing webdriver..."
        driver?.quit()
        testManager.resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
}
