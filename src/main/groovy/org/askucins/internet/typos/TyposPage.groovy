package org.askucins.internet.typos

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class TyposPage extends InternetPage {
    static url = 'typos'
    static content = {
        textWithTypos { $('div.example p')[1].text() }
    }

    static at = {
        header == 'Typos'
        textWithTypos ==~ /Sometimes you'll see a typo, other times you won.t\./
    }
}
