package org.askucins.gebbook.local


import groovy.util.logging.Slf4j

@Slf4j
class PageWithDivPage extends GebLocalPage {
    static url = 'page-with-div.html'
    static content = {
        theDiv { $('div', id: 'a') }
        theDivText {theDiv.text()}
        theDivTemplate { id -> $('div', id: id) }
        theDivTemplateText { id -> theDivTemplate(id).text() }
    }
    static at = {
        title == 'Page with div'
    }
}
