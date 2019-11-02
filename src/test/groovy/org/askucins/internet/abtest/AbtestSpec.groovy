package org.askucins.internet.abtest

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll

@Slf4j
class AbtestSpec extends InternetSpec {

    def "should display correct info"() {
        when:
        to AbtestPage
        then:
        info == 'Also known as split testing. This is a way in which businesses are able to simultaneously test and learn different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).'
        cleanup:
        log.debug "Info starts with: ${info[0..9]}..."
    }

    def "should display correct header"() {
        when:
        to AbtestPage
        then:
        AbtestPage.headerVariants.contains headerVariant
        cleanup:
        log.debug "Current header variant: $headerVariant"
    }

    @Unroll
    def "should always display correct header (#attempt)"() {
        when:
        to AbtestPage
        then:
        AbtestPage.headerVariants.contains headerVariant
        cleanup:
        log.debug "Current header variant: $headerVariant"
        where:
        attempt << (0..10)
    }
}