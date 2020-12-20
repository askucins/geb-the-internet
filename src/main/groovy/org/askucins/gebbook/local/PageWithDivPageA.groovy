package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class PageWithDivPageA extends GebLocalPage {
    static url = 'page-with-div-a.html'
    static content = {
        theHeader { $('h1').text() }
    }
    static at = {
        title == 'Page with div A'
    }
}
