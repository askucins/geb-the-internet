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
}
