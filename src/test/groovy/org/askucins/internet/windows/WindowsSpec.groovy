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

    def "should open NewWindowPage on a click"() {
        given:
        to WindowsPage
        when:
        newWindowLink.click()
        then:
        pause()
        at NewWindowPage
        cleanu
    }
}
