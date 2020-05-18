package org.askucins.gebbook.local

import geb.Browser
import groovy.util.logging.Slf4j

@Slf4j
class PageWithDivSpec extends GebLocalSpec {

    def "should open page-with-div page"() {
        expect:
        to PageWithDivPage
        cleanup:
        report "PageWithDiv"
    }

    def "should find the div via 'drive'"() {
        expect:
        Browser.drive {
            to PageWithDivPage
            assert theDiv.text() == 'aa'
        }.quit()
    }

    def "should work with various content templates"() {
        when:
        to PageWithDivPage
        then:
        theDiv.text() == 'aa' // as a property
        and:
        theDiv().text() == 'aa' // as a method
        and:
        theDivText == 'aa'
        and:
        theDivTemplate('a').text() == 'aa' // returns Navigator
        and:
        theDivTemplateText('a') == 'aa' //returns text
    }
}
