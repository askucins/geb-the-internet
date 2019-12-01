package org.askucins.internet.redirection

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class StatusPage extends Page {
    static url = 'status_codes'
    static content = {
        header { $('div#content div.example h3').first().text() }
    }
    static at = {
        header == 'Status Codes'
    }
}
