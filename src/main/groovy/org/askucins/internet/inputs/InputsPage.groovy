package org.askucins.internet.inputs

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class InputsPage extends Page {
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
