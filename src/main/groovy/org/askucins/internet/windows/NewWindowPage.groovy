package org.askucins.internet.windows

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class NewWindowPage extends InternetPage {
    static url = 'windows/new'
    static content = {
        header { $('div.example h3').first().text() }
    }
    static at = {
        header == 'New Window'
    }
}
