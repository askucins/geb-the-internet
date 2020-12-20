package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class PageWithDivPageB extends GebLocalPage {
    static url = 'page-with-div-b.html'
    static content = {
        theHeader { $('h1').text() }
    }
    static at = {
        title == 'Page with div B'
    }
}
