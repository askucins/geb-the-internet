package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class SmokeSpec extends GebLocalSpec {

    def "should open smoke page"() {
        expect:
        to SmokePage
        cleanup:
        report("Smoke-page")
    }

    def "should find all elements matching to a given class"() {
        when:
        to SmokePage
        then:
        heading.size() == 2
        and:
        heading*.text() == ['This is heading.', 'This is another heading.']
    }

    def "should find element only a given class"() {
        when:
        to SmokePage
        then:
        headingOnly.size() == 1
        and:
        headingOnly.text() == 'This is heading.'
    }

    def "should find all elements not containing a given class"() {
        when:
        to SmokePage
        then:
        headingNotAnother.size() == 1
        and:
        headingNotAnother.text() == 'This is heading.'
    }

    def "should find all elements not containing a given class - another approach"() {
        when:
        to SmokePage
        then:
        headingNotAnotherAgain.size() == 1
        and:
        headingNotAnotherAgain.text() == 'This is heading.'
    }
}
