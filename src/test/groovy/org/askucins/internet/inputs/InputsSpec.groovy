package org.askucins.internet.inputs

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec

@Slf4j
class InputsSpec extends InternetSpec {
    def "should open Inputs page"() {
        expect:
        to InputsPage
    }

    def "should find input form"() {
        when:
        to InputsPage
        then:
        inputLabel == 'Number'
    }

    //TODO add more tests once review Modules
}
