package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

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
