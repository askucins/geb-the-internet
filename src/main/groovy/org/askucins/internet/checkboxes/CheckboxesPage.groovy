package org.askucins.internet.checkboxes

import geb.Page
import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import org.openqa.selenium.WebElement

@Slf4j
class CheckboxesPage extends Page {
    static url = 'checkboxes'
    static content = {
        header { $('div#content div.example h3').first().text() }
        checkboxesGroup { $('div.example form#checkboxes') }
        checkboxes { checkboxesGroup.$('input', type: 'checkbox') }
        checkboxAt { Integer position -> checkboxes[(position - 1)] }
    }
    static at = {
        header == 'Checkboxes'
    }

    static Boolean isEnabled(Navigator checkbox) { checkbox.attr('checked') }

    String textNext(WebElement elem) {
        //Based on idea from Konrad G.
        browser.driver.executeScript("return arguments[0].nextSibling.textContent;", elem)
    }
}
