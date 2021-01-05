package org.askucins.gebbook.local

import geb.error.RequiredPageContentNotPresent
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
        theDivByMethod { $('div', id: getDivId()) }
        theDivByStaticMethod { $('div', id: divId()) }

        // All three have the same content definition, only options are different
        requiredDiv() { $('div', id: 'b') }
        requiredExplicitlyDiv(required: true) { $('div', id: 'b') }
        notRequiredDiv(required: false) { $('div', id: 'b') }
        notRequiredDivAndWait(required: false, wait: true) {
            log.info "notRequiredDivAndWait called"
            $('div', id: 'b')
        }
        paragraphsNotEnough(min: 3) { $('div.paragraphs p') }
        paragraphsEnough(min: 2) { $('div.paragraphs p') }

        paragraphsTooMany(max: 1) { $('div.paragraphs p') }
        paragraphsNotTooMany(max: 2) { $('div.paragraphs p') }

        paragraphsWithinLimit(times: 2..3) { $('div.paragraphs p') }
        paragraphsOutsideLimit(times: 3..3) { $('div.paragraphs p') }

        valueCached(cache: true) { valueCacheable }
        valueNotCached { valueCacheable } // Default value of 'cache' is 'false'

        targetPageA(to: PageWithDivPageA) { $('div#pages p.target-page#page-a a') }
        targetPageB(to: PageWithDivPageB) { $('div#pages p.target-page#page-b a') }
        targetPageC(to: PageWithDivPageC) { $('div#pages p.target-page#page-c a') }

        randomTargetPage(to: [PageWithDivPageA, PageWithDivPageB, PageWithDivPageC]) { $('button#random-page') }

        // Dynamic elements
        addFile { $('input#add-file', type: 'button') }
        removeFile { addedFile -> addedFile.$('a').click() }
        addedFiles { $('p.added-file') }
        addedFilesWait(wait: true) {
            log.info "addedFilesWait called"
            addedFiles
        }
        addedFilesWaitAndNotRequired(wait: true, required: false) {
            log.info "addedFilesWaitAndNotRequired called"
            addedFiles
        }
        addedFilesNoWait(wait: false) { // wait: false is default
            log.info "addedFilesNoWait called"
            addedFiles
        }
        addedFilesWaitPreset(wait: 'quick') {
            log.info "addedFilesWaitPreset called"
            addedFiles
        }
        addedFilesWaitTimeout(wait: 4) {
            log.info "addedFilesWaitPreset called"
            addedFiles
        }
        addedFilesWaitTimeoutRetry(wait: [4, 1]) {
            log.info "addedFilesWaitTimeoutRetry called"
            addedFiles
        }
    }
    static at = {
        title == 'Page with div'
    }

    def valueCacheable = 1

    def divId = 'a'

    static divId() { 'a' }

    def navigateToRandomTargetPage() {
        randomTargetPage.click()
    }

    // Dynamic elements

    def addNewFile() {
        addFile.click()
    }

    def removeNewFile(Integer fromPosition) {
        removeFile(addedFiles[fromPosition])
    }
}
