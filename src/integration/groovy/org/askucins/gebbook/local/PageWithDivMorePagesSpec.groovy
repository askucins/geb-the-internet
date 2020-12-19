package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec
import spock.lang.Ignore
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

    def "should navigate to page A by target position"() {
        given:
        to PageWithDivPage
        when:
        //targetPage[0].click()
        navigateToTargetPageOf(0)
        //page(PageWithDivPageA)
        then:
        //at PageWithDivPageA
        theHeader == 'Page with div A'
    }

    @Ignore
    def "should navigate to (#header)"() {
        given:
        to PageWithDivPage
        when:
        targetPage[(position)].click()
        then:
        pageChecker()
        where:
        position | pageChecker                                 | header
        0        | { assert page instanceof PageWithDivPageA } | 'Page with div A'
        1        | { assert page instanceof PageWithDivPageB } | 'Page with div B'
        2        | { assert page instanceof PageWithDivPageC } | 'Page with div C'
    }

    @Ignore
    def "should open a random page (#attempt)"() {
        given:
        to PageWithDivPage
        Integer numberOfTargets = targetPage.size()
        when:
        targetPage[r.nextInt(numberOfTargets)].click()
        then:
        page instanceof PageWithDivPageA
        and:
        theHeader
//        cleanup:
//        log.info "Landed at page: {}", theHeader
        where:
        attempt << (0..0)
    }

}
