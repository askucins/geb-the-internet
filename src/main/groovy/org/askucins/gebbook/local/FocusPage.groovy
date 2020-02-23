package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class FocusPage extends GebLocalPage {
    static url = 'focus.html'
    static at = { title == 'Focus' }
    static content = {
        description { $('div.description-prompt input#description') }
    }
}
