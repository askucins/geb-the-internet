package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec
import org.openqa.selenium.StaleElementReferenceException

@Slf4j
class DynamicNavigatorSpec extends GebLocalSpec {

    def "should open naive dynamic-navigator page"() {
        expect:
        to DynamicNavigatorPageNaive
        and:
        giveMeThoseBerries() == ["strawberry", "raspberry", "blueberry", "cranberry"]
        cleanup:
        report("Dynamic-Navigator-naive")
    }

    def "should move berries around on naive dynamic-navigator page"() {
        given:
        to DynamicNavigatorPageNaive
        when:
        moveUpBy("blueberry", 2)
        then:
        notThrown(StaleElementReferenceException)
        and:
        giveMeThoseBerries() == ["blueberry", "strawberry", "raspberry", "cranberry"]
        cleanup:
        report("Dynamic-Navigator-naive-no-error")
    }

    def "should open bad dynamic-navigator page"() {
        expect:
        to DynamicNavigatorPageBad
        and:
        items*.text() == ["strawberry", "raspberry", "blueberry", "cranberry"]
        cleanup:
        report("Dynamic-Navigator-bad")
    }

    def "should not move more berries around on bad dynamic-navigator page"() {
        given:
        to DynamicNavigatorPageBad
        when:
        item("blueberry").moveUpBy(2)
        then:
        StaleElementReferenceException e = thrown()
        cleanup:
        report("Dynamic-Navigator-bad-error")
    }

    def "should move one berry around on bad dynamic-navigator page"() {
        given:
        to DynamicNavigatorPageBad
        when:
        item("blueberry").moveUpBy(1)
        then:
        notThrown(StaleElementReferenceException)
        and:
        items*.text() == ["strawberry", "blueberry", "raspberry", "cranberry"]
        cleanup:
        report("Dynamic-Navigator-bad-no-error")
    }

    def "should open good dynamic-navigator page"() {
        expect:
        to DynamicNavigatorPageGood
        and:
        items*.text() == ["strawberry", "raspberry", "blueberry", "cranberry"]
        cleanup:
        report("Dynamic-Navigator-good")
    }

    def "should move berries around on good dynamic-navigator page"() {
        given:
        to DynamicNavigatorPageGood
        when:
        item("blueberry").moveUpBy(2)
        then:
        notThrown(StaleElementReferenceException)
        and:
        items*.text() == ["blueberry", "strawberry", "raspberry", "cranberry"]
        cleanup:
        report("Dynamic-Navigator-good-no-error")
    }

    def "should not move berries around as part of the berries list on good dynamic-navigator page"() {
        given:
        to DynamicNavigatorPageGood
        when:
        items[2].moveUpBy(2) //Gotcha! This does not work, no matter if 'items' is dynamic
        then:
        StaleElementReferenceException e = thrown()
        cleanup:
        report("Dynamic-Navigator-good-error")
    }
}
