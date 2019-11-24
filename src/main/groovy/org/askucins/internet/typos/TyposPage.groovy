package org.askucins.internet.typos

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class TyposPage extends Page {
    static url = 'typos'
    static content = {
        header { $('div#content div.example h3').first().text() }
        textWithTypos { $('div.example p')[1].text() }
    }

    static at = {
        header == 'Typos'
        textWithTypos ==~ /Sometimes you'll see a typo, other times you won.t\./
    }
}
