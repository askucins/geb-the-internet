package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
class FocusSpec extends GebLocalSpec {
    def "should open focus page"() {
        expect:
        to FocusPage
        cleanup:
        report("Focus")
    }

    def "should input does not have focus when loaded"() {
        when:
        to FocusPage
        then:
        browser.focused().size() == 1 // A focus is somewhere?
        and:
        !description.focused
        cleanup:
        report("Focus-loaded")
    }

    def "should input does have focus when clicked"() {
        given:
        to FocusPage
        when:
        description.click()
        then:
        focused().attr('name') == 'description'
        and:
        description.focused
        cleanup:
        report("Focus-clicked")
    }
}
