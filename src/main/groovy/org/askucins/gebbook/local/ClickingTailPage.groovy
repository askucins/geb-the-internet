package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class ClickingTailPage extends GebLocalPage {
    static url = 'clicking-tail.html'
    static at = { title == 'Clicking - tail' }
    static content = {
        info { $('div.clickee').text() }
    }
}
