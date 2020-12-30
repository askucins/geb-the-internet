package org.askucins.gebbook

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
        log.info "geb.env: {}", System.getProperty('geb.env') ?: '[not set explicitly!]'
        assert System.getProperty('geb.build.reportsDir')
        log.info "geb.build.reportsDir: {}", System.getProperty('geb.build.reportsDir')
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
