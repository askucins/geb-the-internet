package org.askucins.internet.redirection

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec

@Slf4j
class RedirectionSpec extends InternetSpec {
    def "should open redirection page"() {
        expect:
        to RedirectionPage
    }

    def "should open status page"() {
        expect:
        to StatusPage
    }

    def "should redirect directly"() {
        when:
        via RedirectPage
        then:
        at StatusPage
    }

    def "should redirect on click"() {
        given:
        to RedirectionPage
        when:
        redirect.click()
        then:
        via RedirectPage
        and:
        at StatusPage
    }

    def "should also redirect on click"() {
        given:
        to RedirectionPage
        when:
        redirect.click()
        then:
        at StatusPage
    }
}
