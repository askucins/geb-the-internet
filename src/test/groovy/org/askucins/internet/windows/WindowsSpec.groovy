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
}