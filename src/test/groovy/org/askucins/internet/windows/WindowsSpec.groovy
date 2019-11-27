package org.askucins.internet.windows

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec

@Slf4j
class WindowsSpec extends InternetSpec {
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
            withWindow({ title == 'New Window' }, page: NewWindowPage) {
                log.info "New: Current window: {}", browser.currentWindow
                log.info "New: Available windows: {}", browser.availableWindows
                true
            }
        }
        cleanup:
        log.info "Old: Current window: {}", browser.currentWindow
        log.info "Old: Available windows: {}", browser.availableWindows
    }

    //TODO - it works in Chrome but not in Firefox...
    def "should open NewWindowPage on a click - second approach"() {
        given:
        to WindowsPage
        expect:
        withNewWindow({ newWindowLink.click() }, page: NewWindowPage, wait: true) {
            log.info "New: Current window: {}", browser.currentWindow
            log.info "New: Available windows: {}", browser.availableWindows
            true
        }
        cleanup:
        log.info "Old: Current window: {}", browser.currentWindow
        log.info "Old: Available windows: {}", browser.availableWindows
    }
}
