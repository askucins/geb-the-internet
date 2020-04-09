package org.askucins.internet.inputs

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class InputsPage extends InternetPage {
    static url = 'inputs'
    static content = {
        header { $('div#content h3').first().text() }
        inputGroup { $('div#content div.example') }
        inputLabel { inputGroup.$('p').text() }
        inputForm { inputGroup.$('input', type: 'input') }
    }

    static at = {
        header == 'Inputs'
    }
}
