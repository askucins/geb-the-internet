package org.askucins.internet.abtest

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class AbTestPage extends InternetPage {
    static url = 'abtest'
    static content = {
        info { $('div#content div.example p').first().text() }
    }
    static headers = ['A/B Test Control', 'A/B Test Variation 1']
    static at = {
        headers.contains(header)
    }
}
