package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
class SizeAndLocationSpec extends GebLocalSpec {

    def "should open size-and-location page"() {
        expect:
        to SizeAndLocationPage
        cleanup:
        report("SizeAndLocation")
    }

    def "should report correctly sizes of elements"() {
        when:
        to SizeAndLocationPage
        then:
        verifyAll($("div", 0)) {
            height == 20
            width == 40
            x == 20
            y == 10
        }
        and:
        verifyAll {
            $("div")*.height == [20, 40]
            $("div")*.width == [40, 100]
            $("div")*.x == [20, 30]
            $("div")*.y == [10, 150]
        }
    }
}
