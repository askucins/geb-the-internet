package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class SmokeSpec extends GebLocalSpec {

    def "should open page"() {
        expect:
        to SmokePage
        cleanup:
        report("Smoke-page")
    }
}
