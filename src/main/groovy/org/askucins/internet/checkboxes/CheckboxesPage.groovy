package org.askucins.internet.checkboxes

import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage
import org.openqa.selenium.WebElement

@Slf4j
class CheckboxesPage extends InternetPage {
    static url = 'checkboxes'
    static content = {
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
