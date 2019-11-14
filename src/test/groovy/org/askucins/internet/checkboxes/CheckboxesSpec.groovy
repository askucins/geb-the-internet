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
        and:
        checkbox.siblings()[1].firstElement().text().contains("checkbox $position") //TODO it doesn't work! on<->null
        cleanup:
        //See more at: https://stackoverflow.com/questions/27307131/selenium-webdriver-how-do-i-find-all-of-an-elements-attributes
        def attributes = browser.driver.executeScript('var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;', checkbox.firstElement())
        log.warn "Attributes: " + attributes
        //TODO check solution from Konrad:
        // document.querySelectorAll('input')[0].nextSibling
        where:
        position | _
        1        | _
        //2        | _
    }
}