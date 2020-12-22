package org.askucins.gebbook.local

import geb.waiting.WaitTimeoutException
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
class OpenLinkWithDelaySpec extends GebLocalSpec {

    def "should open open-link-with-delay page"() {
        expect:
        to OpenLinkWithDelayPage
        cleanup:
        report "OpenLinkWithDelayPage"
    }

    // Pros: reporting on failed assert; Cons: unintuitive syntax
    def "should open Geb home page in a new window - with explict assert"() {
        when:
        to OpenLinkWithDelayPage
        then:
        withNewWindow({ js.openGebInNewWindow() }, wait: true, close: false) {
            assert title == 'Geb - Very Groovy Browser Automation'
            true
        }
    }

    // Cons: lack of reporting on failed assert; Pros: intuitive syntax
    def "should open Geb home page in a new window - without explict assert"() {
        when:
        to OpenLinkWithDelayPage
        then:
        withNewWindow({ js.openGebInNewWindow() }, wait: true, close: false) {
            title == 'Geb - Very Groovy Browser Automation'
        }
    }

    def "should open Geb home page in a new window - within the timeout limit"() {
        when:
        to OpenLinkWithDelayPage
        then:
        withNewWindow({ js.openGebInNewWindow(1000) }, wait: [1.1, 0.1], close: false) {
            assert title == 'Geb - Very Groovy Browser Automation'
            true
        }
    }

    def "should fail opening Geb page due to the timeout"() {
        given:
        to OpenLinkWithDelayPage
        when:
        withNewWindow({ js.openGebInNewWindow(1100) }, wait: [1, 0.1], close: false) {
            assert title == 'Geb - Very Groovy Browser Automation'
            true
        }
        then:
        WaitTimeoutException e = thrown()
    }
}