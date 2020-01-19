package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class SmokeSpec extends GebLocalSpec {

    def "should open page"() {
        when:
        to SmokePage
        then:
        heading.size() == 2
        and:
        heading*.text() == ['This is heading.','This is another heading.']
        cleanup:
        report("Smoke-page")
    }
}
