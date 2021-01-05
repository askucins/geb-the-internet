package org.askucins.gebbook.local


import groovy.util.logging.Slf4j

@Slf4j
class PageWithDivWaitHiddenSpec extends PageWithDivWaitSpec {

    def "should find visible element of -welcome-"() {
        given:
        to PageWithDivPage
        expect:
        welcome
        and:
        welcomeVisible
        and:
        !isWelcomeHidden
    }

    def "should find hidden element of -awesome-"() {
        given:
        to PageWithDivPage
        expect:
        awesome
        and:
        isAwesomeHidden
    }

    def "should not find visible element of -awesome-"() {
        given:
        to PageWithDivPage
        expect:
        !awesomeVisible
    }

    def "should find visible element of -awesome- once revealed"() {
        given:
        to PageWithDivPage
        when:
        welcomeCta.click()
        then:
        awesomeVisible
        and:
        !isAwesomeHidden
        and:
        !welcomeVisible
    }
}