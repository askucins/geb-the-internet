package org.askucins.internet.windows

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
class WindowsSpec extends InternetSpec {
    def setupSpec() {
        System.setProperty('geb.env', 'firefox')
    }

    def cleanupSpec() {
        //TODO weirdly @RestoreSystemProperties does not work as expected
        System.clearProperty('geb.env')
    }

    def "should open WindowsPage page"() {
        expect:
        to WindowsPage
    }

    def "should open NewWindowPage page directly"() {
        expect:
        to NewWindowPage
    }

    def "should open NewWindowPage on a click - first approach"() {
        given:
        to WindowsPage
        when:
        newWindowLink.click()
        then:
        waitFor {
            log.info 'Checking...'
            withWindow({ title == 'New Window' }, page: NewWindowPage, close: true) {
                log.info "New: Current window: {}", browser.currentWindow
                log.info "New: Available windows: {}", browser.availableWindows
                true
            }
        }
        cleanup:
        log.info "Old: Current window: {}", browser.currentWindow
        log.info "Old: Available windows: {}", browser.availableWindows
    }

    @Unroll
    def "should open NewWindowPage on a click - second approach (attempt: #attempt)"() {
        given:
        to WindowsPage
        expect:
        withNewWindow({ newWindowLink.click() }, page: NewWindowPage, wait: 'slow', close: true) {
            log.info "New: Current window: {}", browser.currentWindow
            log.info "New: Available windows: {}", browser.availableWindows
            true
        }
        cleanup:
        log.info "Old: Current window: {}", browser.currentWindow
        log.info "Old: Available windows: {}", browser.availableWindows

        where:
        attempt << (0..<10)
    }
}
