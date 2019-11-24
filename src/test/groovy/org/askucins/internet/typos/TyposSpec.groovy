package org.askucins.internet.typos

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll

@Slf4j
class TyposSpec extends InternetSpec {
    def "should open Typos page"() {
        expect:
        to TyposPage
    }

    @Unroll
    def "should open Typos page every time (#attempt)"() {
        expect:
        to TyposPage
        cleanup:
        log.debug "Found text: {}", textWithTypos
        where:
        attempt << (0..<10)
    }
}