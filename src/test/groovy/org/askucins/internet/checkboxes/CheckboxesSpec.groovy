package org.askucins.internet.checkboxes

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
        def checkbox = checkboxAt(position)
        Boolean enabledBefore = isEnabled(checkbox)
        checkbox.click()
        then:
        isEnabled(checkbox) == !enabledBefore
        and:
        checkbox.value().contains "checkbox $position" //TODO it doesn't work! on<->null
        where:
        position | _
        1        | _
        2        | _
    }
}