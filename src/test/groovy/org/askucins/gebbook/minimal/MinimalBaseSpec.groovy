package org.askucins.gebbook.minimal

import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.util.environment.RestoreSystemProperties

@RestoreSystemProperties
@Slf4j
class MinimalBaseSpec extends Specification {

    def setupSpec() {
        expect:
        assert System.getProperty('webdriver.chrome.driver')
        assert System.getProperty('geb.build.reportsDir')

        clean:
        log.info "Path to chromedriver: " + System.getProperty('webdriver.chrome.driver')
        log.info "Additional Geb reports in: " + System.getProperty('geb.build.reportsDir')
    }
}