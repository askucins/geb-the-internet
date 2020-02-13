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
        //assert System.getProperty('webdriver.gecko.driver')
        assert System.getProperty('geb.build.reportsDir')
        assert System.getProperty('geb.build.baseUrl')
        //assert System.getProperty('org.askucins.tesseract')

        log.info "=".padLeft(78, '=')
        log.info "Webdriver path to chromedriver: " + System.getProperty('webdriver.chrome.driver')
        //log.info "Webdriver path to geckodriver: " + System.getProperty('webdriver.gecko.driver')
        log.info "Geb reports in: " + System.getProperty('geb.build.reportsDir')
        log.info "Geb baseUrl: " + System.getProperty('geb.build.baseUrl')
        log.info "Tesseract (ocr) data path: " + System.getProperty('org.askucins.tesseract')
        log.info "Webdriver used in tests: " + System.getProperty('org.askucins.webdriver') ?: 'none explicitly!'
    }

    def cleanupSpec() {
        // Based on https://github.com/geb/issues/issues/473
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
}
