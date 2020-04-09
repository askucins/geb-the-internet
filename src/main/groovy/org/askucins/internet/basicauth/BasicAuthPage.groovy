package org.askucins.internet.basicauth

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class BasicAuthPage extends InternetPage {
    static url = 'basic_auth'
    static content = {
        info { $('div#content div.example p').first().text() }
    }
    static at = {
        header == 'Basic Auth'
        info == 'Congratulations! You must have the proper credentials.'
    }
}
