package org.askucins.gebbook.local

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
abstract class GebLocalSpec extends GebReportingSpec {
    @Rule
    TestName testName = new TestName()

    def setupSpec() {
        assert System.getProperty('geb.env')
        log.info "geb.env: {}", System.getProperty('geb.env')
        assert System.getProperty('geb.build.reportsDir')
        log.info "geb.build.reportsDir: {}", System.getProperty('geb.build.reportsDir')
    }

    def setup() {
        log.info ">>>> ${testName.methodName}"
    }

    def cleanupSpec() {
        log.info "Closing webdriver..."
        // Based on https://github.com/geb/issues/issues/473
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
}
