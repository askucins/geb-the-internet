package org.askucins.internet.redirection

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class RedirectionPage extends Page {
    static url = 'redirector'
    static content = {
        header { $('div#content div.example h3').first().text() }
        redirect { $('div.example p a#redirect') }
    }
    static at = {
        header == 'Redirection'
    }
}
