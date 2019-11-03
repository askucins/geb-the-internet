package org.askucins.internet.basicauth

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class BasicAuthPage extends Page {
    static url = 'basic_auth'
    static content = {
        header { $('div#content div.example h3').first().text() }
        info { $('div#content div.example p').first().text() }
    }
    static at = {
        header == 'Basic Auth'
        info == 'Congratulations! You must have the proper credentials.'
    }
}
