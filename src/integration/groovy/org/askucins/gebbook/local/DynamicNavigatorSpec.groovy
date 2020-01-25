package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.openqa.selenium.StaleElementReferenceException

@Slf4j
class DynamicNavigatorSpec extends GebLocalSpec {

    def "should open bad dynamic-navigator page"() {
        expect:
        to DynamicNavigatorPageBad
        cleanup:
        report("Dynamic-Navigator-bad")
    }

    def "should not move berries around on bad dynamic-navigator page"() {
        given:
        to DynamicNavigatorPageBad
        when:
        item("blueberry").moveUpBy(2)
        then:
        StaleElementReferenceException e = thrown()
        cleanup:
        report("Dynamic-Navigator-bad-error")
    }

    def "should open good dynamic-navigator page"() {
        expect:
        to DynamicNavigatorPageGood
        cleanup:
        report("Dynamic-Navigator-good")
    }

    def "should move berries around on bad dynamic-navigator page"() {
        given:
        to DynamicNavigatorPageGood
        when:
        item("blueberry").moveUpBy(2)
        then:
        notThrown(StaleElementReferenceException)
        cleanup:
        report("Dynamic-Navigator-good-no-error")
    }
}
