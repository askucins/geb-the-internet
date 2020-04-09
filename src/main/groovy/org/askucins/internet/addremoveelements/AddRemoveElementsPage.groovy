package org.askucins.internet.addremoveelements

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class AddRemoveElementsPage extends InternetPage {
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

    void deleteFirst() {
        delete(0)
    }

    void deleteLast() {
        delete(elements.size() - 1)
    }

    void deleteRandom() {
        Random random = new Random()
        delete(random.nextInt(elements.size()))
    }
}
