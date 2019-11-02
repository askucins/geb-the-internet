package org.askucins.internet.addremoveelements

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class AddRemoveElementsPage extends Page {
    static url = 'add_remove_elements/'
    static content = {
        header { $('div#content h3').first().text() }
        addElement { $('div.example button', text: 'Add Element') }
        elementsContainer { $('div.example div#elements') }
        elements(required: false) { elementsContainer.$('button.added-manually') }
        element(required: false) { i -> elements[i] }
    }
    static at = {
        header == 'Add/Remove Elements'
    }

    void add() {
        addElement.click()
    }

    void delete(Integer i) {
        element(i).click()
    }
}
