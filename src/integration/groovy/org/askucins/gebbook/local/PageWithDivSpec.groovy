package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import geb.Browser

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
            assert theDiv.text() == 'a' // as a property
            assert theDiv().text() == 'a' // as a method
        }.quit()
    }
}
