package org.askucins.gebbook.local


import groovy.util.logging.Slf4j

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

        targetPage(to: [PageWithDivPageA, PageWithDivPageB, PageWithDivPageC]) { $('div#pages p.target-page a') }
        targetPageA(to: PageWithDivPageA) { $('div#pages p.target-page#page-a a') }
        targetPageB(to: PageWithDivPageB) { $('div#pages p.target-page#page-b a') }
        targetPageC(to: PageWithDivPageC) { $('div#pages p.target-page#page-c a') }
    }
    static at = {
        title == 'Page with div'
    }

    def value = 1

    def divId = 'a'

    def divId() { 'a' }

    def navigateToTargetPageOf(Integer n) {
        targetPage[(n)].click()
        browser.page
    }
}
