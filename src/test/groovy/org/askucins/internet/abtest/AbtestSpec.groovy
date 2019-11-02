package org.askucins.internet.abtest

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll

@Slf4j
class AbtestSpec extends InternetSpec {

    def "should open page"() {
        when:
        to AbtestPage
        then:
        info.startsWith 'Also known as split testing.'
        cleanup:
        log.debug "Current header variant: $headerVariant"
    }

    @Unroll
    def "should always open page (#attempt)"() {
        when:
        to AbtestPage
        then:
        at AbtestPage
        cleanup:
        log.debug "Current header variant: $headerVariant"
        where:
        attempt << (0..10)
    }
}