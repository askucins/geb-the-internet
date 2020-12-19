package org.askucins.gebbook.local

import geb.Browser
import geb.error.ContentCountOutOfBoundsException
import geb.error.RequiredPageContentNotPresent
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec
import spock.lang.Ignore

@Slf4j
class PageWithDivSpec extends GebLocalSpec {

    @Ignore
    def "should find the div via 'drive'"() {
        expect:
        Browser.drive {
            to PageWithDivPage
            assert theDiv.text() == 'aa'
        }.quit()
    }

    def "should open page-with-div page"() {
        expect:
        to PageWithDivPage
        cleanup:
        report "PageWithDiv"
    }

    def "should work with various content templates"() {
        when:
        to PageWithDivPage
        then:
        theDiv.text() == 'aa' // as a property
        and:
        theDiv().text() == 'aa' // as a method
        and:
        theDivText == 'aa' //internal reference to another content definition
        and:
        theDivTemplate('a').text() == 'aa' // returns Navigator
        and:
        theDivTemplateText('a') == 'aa' //returns text
        and:
        theDivByProperty.text() == 'aa'
        and:
        theDivByMethod.text() == 'aa'
    }

    def "should apply 'required' option"() {
        when:
        to PageWithDivPage
        then:
        !notRequiredDiv

        when:
        requiredDiv
        then:
        RequiredPageContentNotPresent error = thrown()
    }

    def "should apply 'min' option"() {
        when:
        to PageWithDivPage
        then:
        paragraphsEnough

        when:
        paragraphsNotEnough
        then:
        ContentCountOutOfBoundsException error = thrown()
    }

    def "should apply 'max' option"() {
        when:
        to PageWithDivPage
        then:
        paragraphsNotTooMany

        when:
        paragraphsTooMany
        then:
        ContentCountOutOfBoundsException error = thrown()
    }

    def "should apply 'times' option"() {
        when:
        to PageWithDivPage
        then:
        paragraphsWithinLimit

        when:
        paragraphsOutsideLimit
        then:
        ContentCountOutOfBoundsException error = thrown()
    }

    def "should apply 'cache' option"() {
        when:
        to PageWithDivPage
        then:
        valueCached == 1
        and:
        valueNotCached == 1

        when:
        value = 2 // Gotcha! This will be evaluated within page, so the property will be changed
        then:
        valueCached == 1
        and:
        valueNotCached == 2
    }
}
