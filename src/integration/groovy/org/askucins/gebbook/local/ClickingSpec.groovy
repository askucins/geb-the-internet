package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
class ClickingSpec extends GebLocalSpec {

    def "should open clicker page"() {
        expect:
        to ClickingHeadPage
        cleanup:
        report('Clicking-head')
    }

    def "should open clickee page"() {
        expect:
        to ClickingTailPage
        cleanup:
        report('Clicking-tail')
    }

    def "should click work"() {
        given:
        to ClickingHeadPage
        when:
        clicker.click()
        then:
        at ClickingTailPage
        cleanup:
        report('Clicking-target')
    }

    def "should click also work with the target provided explicitly"() {
        given:
        to ClickingHeadPage
        expect:
        clicker.click(ClickingTailPage)
        cleanup:
        report('Clicking-target-another')
    }
}
