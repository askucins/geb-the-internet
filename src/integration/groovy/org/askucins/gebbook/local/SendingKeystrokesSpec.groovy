package org.askucins.gebbook.local

import geb.error.SingleElementNavigatorOnlyMethodException
import geb.module.Checkbox
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec
import org.openqa.selenium.Keys

@Slf4j
class SendingKeystrokesSpec extends GebLocalSpec {

    def "should open sending-keys page"() {
        expect:
        to SendingKeystrokesPage
        report "sending-keystrokes"
    }

    def "should send content to the input"() {
        given:
        String message = "ala ma kota"
        to SendingKeystrokesPage
        when:
        inputDemo << message
        inputDemo << Keys.ENTER
        then:
        inputDemo.value() == message
        when:
        inputDemo << message
        then: "values are concatenated"
        inputDemo.value() == message + message
        when:
        inputDemo << Keys.chord(Keys.CONTROL, 'c')
        then:
        inputDemo.value() == message + message
        when:
        inputDemo.value(message)
        then: "value is overwritten"
        inputDemo.value() == message
    }

    def "should read value only on a sole input"() {
        given:
        to SendingKeystrokesPage
        when:
        inputText.value()
        then:
        thrown(SingleElementNavigatorOnlyMethodException)
    }

    def "should set value of all text inputs"() {
        given:
        String message = "ala ma kota"
        to SendingKeystrokesPage
        when:
        inputText.value(message)
        then:
        inputDemo.value() == message
        and:
        inputAnother.value() == message
        cleanup:
        report "sending-keystrokes-write-to-all-text-inputs"
    }

    def "should set values of checkboxes"() {
        given:
        to SendingKeystrokesPage
        when:
        inputEnabler.value(true)
        then:
        inputEnabler.value()
        and:
        inputEnabler.value() == 'on'
        and:
        inputEnabler.module(Checkbox).checked
        when:
        inputEnabler.value(false)
        then:
        !inputEnabler.value()
        and:
        inputEnabler.value() == null
        and:
        inputEnabler.module(Checkbox).unchecked
    }

    def "should set value of a textarea"() {
        given:
        String message = 'This is Ala.\nAla ma kota.'
        to SendingKeystrokesPage
        when:
        textMe.value(message)
        then:
        textMe.value() == message
        cleanup:
        report "text-area"
    }

    def "should set values of select"() {
        given:
        to SendingKeystrokesPage
        when:
        petSelector.value('dog')
        then:
        petSelector.value() == 'dog'
        when:
        plantSelector.value(['cactus', 'oak'])
        then:
        plantSelector.value() == ['oak', 'cactus'] //Gotcha! Check the order.
    }
}
