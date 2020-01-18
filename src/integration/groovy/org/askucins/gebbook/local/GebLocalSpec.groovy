package org.askucins.gebbook.local

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import groovy.util.logging.Slf4j
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
abstract class GebLocalSpec extends GebReportingSpec {
    def setupSpec() {
        assert System.getProperty('geb.build.reportsDir')
    }

    def cleanupSpec() {
        // Based on https://github.com/geb/issues/issues/473
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }
}
