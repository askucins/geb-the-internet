package org.askucins.gebbook.local


import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class PageWithDivPage extends GebLocalPage {
    static url = 'page-with-div.html'
    static content = {
        theDiv { $('div', id: 'a') }
        theDivText { theDiv.text() }
        theDivTemplate { id -> $('div', id: id) }
        theDivTemplateText { id -> theDivTemplate(id).text() }
        theDivByProperty { $('div', id: divId) }
        theDivByMethod { $('div', id: divId()) }

        requiredDiv { $('div', id: 'b') }
        notRequiredDiv(required: false) { $('div', id: 'b') }

        paragraphsNotEnough(min: 3) { $('div.paragraphs p') }
        paragraphsEnough(min: 2) { $('div.paragraphs p') }

        paragraphsTooMany(max: 1) { $('div.paragraphs p') }
        paragraphsNotTooMany(max: 2) { $('div.paragraphs p') }

        paragraphsWithinLimit(times: 2..3) { $('div.paragraphs p') }
        paragraphsOutsideLimit(times: 3..3) { $('div.paragraphs p') }

        valueCached(cache: true) { value }
        valueNotCached { value }

        targetPageA(to: PageWithDivPageA) { $('div#pages p.target-page#page-a a') }
        targetPageB(to: PageWithDivPageB) { $('div#pages p.target-page#page-b a') }
        targetPageC(to: PageWithDivPageC) { $('div#pages p.target-page#page-c a') }

        randomTargetPage(to: [PageWithDivPageA, PageWithDivPageB, PageWithDivPageC]) { $('button#random-page') }
    }
    static at = {
        title == 'Page with div'
    }

    def value = 1

    def divId = 'a'

    static divId() { 'a' }

    def navigateToRandomTargetPage() {
        randomTargetPage.click()
    }
}
