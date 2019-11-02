package org.askucins.gebbook.minimal.spec

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

        log.info "Path to chromedriver: " + System.getProperty('webdriver.chrome.driver')
        log.info "Path to geckodriver: " + System.getProperty('webdriver.gecko.driver')
        log.info "Additional Geb reports in: " + System.getProperty('geb.build.reportsDir')
    }
}
