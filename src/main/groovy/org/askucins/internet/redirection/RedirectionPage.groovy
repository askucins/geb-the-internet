package org.askucins.internet.redirection

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class RedirectionPage extends InternetPage {
    static url = 'redirector'
    static content = {
        redirect { $('div.example p a#redirect') }
    }
    static at = {
        header == 'Redirection'
    }
}
