package org.askucins.internet

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
abstract class InternetPage extends Page {
    static content = {
        header { $('div#content div.example h3').first().text() }
    }
    static at = {
        title == 'The Internet'
    }
}
