package org.askucins.internet.checkboxes

import geb.Page
import geb.navigator.Navigator
import groovy.util.logging.Slf4j

@Slf4j
class CheckboxesPage extends Page {
    static url = 'checkboxes'
    static content = {
        header { $('div#content div.example h3').first().text() }
        checkboxes { $('div.example form#checkboxes input', type: 'checkbox') }
        checkboxAt { Integer position -> checkboxes[(position - 1)] }

    }
    static at = {
        header == 'Checkboxes'
    }

    static Boolean isEnabled(Navigator checkbox) { checkbox.attr('checked') }
}
