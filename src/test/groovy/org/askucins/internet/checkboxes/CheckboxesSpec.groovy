package org.askucins.internet.checkboxes

import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll

import static org.askucins.internet.checkboxes.CheckboxesPage.isEnabled

@Slf4j
class CheckboxesSpec extends InternetSpec {
    def "should open Checkboxes page"() {
        expect:
        to CheckboxesPage
    }

    @Unroll
    def "should switch checkbox (#position) on click"() {
        given:
        to CheckboxesPage
        when:
        Navigator checkbox = checkboxAt(position)
        Boolean enabledBefore = isEnabled(checkbox)
        checkbox.click()
        then:
        isEnabled(checkbox) == !enabledBefore
        cleanup:
        log.debug "Checkbox $position: {}", isEnabled(checkbox)
        where:
        position | _
        1        | _
        2        | _
    }

    @Unroll
    def "should read text companion of checkbox (#position)"() {
        given:
        to CheckboxesPage
        expect:
        textNext(checkboxAt(position).firstElement()) == content

        where:
        position | content
        1        | ' checkbox 1'
        2        | ' checkbox 2\n  '
    }

    @Unroll
    def "should read the value of checkbox (#position)"() {
        given:
        to CheckboxesPage
        expect:
        checkboxAt(position).value() == value

        where:
        position | value
        1        | null
        2        | 'on'
    }
}