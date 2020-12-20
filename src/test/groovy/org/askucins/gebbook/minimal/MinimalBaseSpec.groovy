package org.askucins.gebbook.minimal

import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
abstract class MinimalBaseSpec extends GebSpec {
    def setupSpec() {
        assert System.getProperty('webdriver.chrome.driver')
        assert System.getProperty('webdriver.gecko.driver')
        assert System.getProperty('geb.build.reportsDir')
        //assert System.getProperty('geb.env')

        log.info "webdriver.chrome.driver: {}", System.getProperty('webdriver.chrome.driver')
        log.info "webdriver.gecko.driver: {}", System.getProperty('webdriver.gecko.driver')
        log.info "geb.build.reportsDir: {}", System.getProperty('geb.build.reportsDir')
        log.info "geb.env: {}", System.getProperty('geb.env') ?: '[not set explicitly!]'
    }

    def cleanupSpec() {
        testManager.resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
}
