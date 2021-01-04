package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec
import spock.lang.Unroll

@Unroll
@Slf4j
class PageWithDivMorePagesSpec extends GebLocalSpec {

    static Random r = new Random()

    def "should open page of (#header)"() {
        expect:
        to destination
        and:
        theHeader == header
        where:
        destination      | header
        PageWithDivPageA | 'Page with div A'
        PageWithDivPageB | 'Page with div B'
        PageWithDivPageC | 'Page with div C'
    }

    def "should navigate to page A"() {
        given:
        to PageWithDivPage
        when:
        targetPageA.click()
        then:
        theHeader == 'Page with div A'
    }

    def "should navigate to page B"() {
        given:
        to PageWithDivPage
        when:
        targetPageB.click()
        then:
        theHeader == 'Page with div B'
    }

    def "should navigate to page C"() {
        given:
        to PageWithDivPage
        when:
        targetPageC.click()
        then:
        theHeader == 'Page with div C'
    }

    def "should navigate to random page of A, B, C (attempt: #attempt)"() {
        given:
        to PageWithDivPage
        when:
        navigateToRandomTargetPage()
        then:
        ['Page with div A', 'Page with div B', 'Page with div C'].contains(theHeader)
        cleanup:
        if (page instanceof PageWithDivPageA) {
            log.info "Sometimes page A is randomly picked."
        } else {
            log.info "Sometimes page other than A is randomly picked, e.g.: ${page.title}"
        }
        where:
        attempt << (0..10)
    }
}
