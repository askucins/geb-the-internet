package org.askucins.internet.abtest

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll

@Slf4j
class AbTestSpec extends InternetSpec {

    def "should open page"() {
        when:
        to AbTestPage
        then:
        info.startsWith 'Also known as split testing.'
        cleanup:
        log.debug "Current header variant: $headerVariant"
    }

    @Unroll
    def "should always open page (#attempt)"() {
        when:
        to AbTestPage
        then:
        at AbTestPage
        cleanup:
        log.debug "Current header variant: $headerVariant"
        where:
        attempt << (0..10)
    }
}