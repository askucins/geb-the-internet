package org.askucins.internet

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import groovy.util.logging.Slf4j
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
abstract class InternetSpec extends GebReportingSpec {
    def setupSpec() {
        assert System.getProperty('webdriver.chrome.driver')
        assert System.getProperty('webdriver.gecko.driver')
        assert System.getProperty('geb.build.reportsDir')
        assert System.getProperty('geb.env')

        log.info "=".padLeft(78, '=')
        log.info "webdriver.chrome.driver: {}", System.getProperty('webdriver.chrome.driver')
        log.info "webdriver.gecko.driver: {}", System.getProperty('webdriver.gecko.driver')
        log.info "geb.build.reportsDir: {}", System.getProperty('geb.build.reportsDir')
        log.info "geb.env: {}", System.getProperty('geb.env')
    }

    def cleanupSpec() {
        // Based on https://github.com/geb/issues/issues/473
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
}
