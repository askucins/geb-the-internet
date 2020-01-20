package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class DynamicNavigatorSpec extends GebLocalSpec {

    def "should open dynamic-navigator page"() {
        expect:
        to DynamicNavigatorPage
        cleanup:
        report("Dynamic-Navigator")
    }

    def "should move berries around"() {
        when:
        to DynamicNavigatorPage
        then:
        item("blueberry").moveUpBy(2)
    }
}
