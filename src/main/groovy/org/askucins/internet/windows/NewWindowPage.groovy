package org.askucins.internet.windows

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class NewWindowPage extends Page {
    static url = 'windows/new'
    static content = {
        header { $('div.example h3').first().text() }
    }
    static at = {
        title == 'New Window'
        header == 'New Window'
    }
}
