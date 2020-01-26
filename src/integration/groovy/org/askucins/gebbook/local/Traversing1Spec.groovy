package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class Traversing1Spec extends GebLocalSpec {

    def "should open traversing-1 page"() {
        expect:
        to Traversing1Page
        cleanup:
        report("Traversing-1")
    }

}
