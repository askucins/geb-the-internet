package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class PageWithDivTemplatePage extends GebLocalPage {
    static url = 'page-with-div.html'
    static content = {
        theDivByProperty { $('div', id: divId) }
        theDivByMethod { $('div', id: getDivId()) }
    }
    static at = {
        title == 'Page with div'
    }

    String divId
}
