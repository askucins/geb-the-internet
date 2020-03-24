package org.askucins.internet.challengingdom

import geb.error.RequiredPageContentNotPresent
import groovy.util.logging.Slf4j
import org.apache.commons.text.similarity.LevenshteinDistance
import org.askucins.internet.InternetSpec
import org.askucins.utils.ImageOcr
import org.openqa.selenium.WebElement
import spock.lang.Requires
import spock.lang.Unroll

@Slf4j
@Requires({ System.getProperty('user.name') == 'askuci' })
class ChallengingDomSpec extends InternetSpec {

    def setupSpec() {
        assert System.getProperty('org.askucins.tesseract')
        log.info "Tesseract (ocr) data path: " + System.getProperty('org.askucins.tesseract')
    }

    def "should open the challenging DOM page"() {
        expect:
        to ChallengingDomPage
    }

    @Unroll
    def "should extract answer when canvas accessed by #selector"() {
        when:
        to ChallengingDomPage
        then:
        def canvas = answerCanvasBy(selector)
        and:
        canvas instanceof WebElement

        when:
        String imageContent = ImageOcr.extractTextFromImage(extractImageFromCanvas(canvas))
        then:
        new LevenshteinDistance().apply(imageContent.substring(0, 8), 'Answer: ') <= 2

        cleanup:
        log.info "OCRed: " + imageContent

        where:
        selector | _
        'ID'     | _
        'JS'     | _
    }

    @Unroll
    def "should react when #buttonType button is clicked"() {
        given:
        to ChallengingDomPage
        when:
        buttonOf(buttonType).click()
        then:
        at ChallengingDomPage

        where:
        buttonType | _
        'red'      | _
        'blue'     | _
        'green'    | _
    }

    @Unroll
    def "should find a header matching to (#label)"() {
        given:
        to ChallengingDomPage
        expect:
        tableHeaderOf(label).text() == label
        where:
        label     | _
        'Lorem'   | _
        'Ipsum'   | _
        'Dolor'   | _
        'Sit'     | _
        'Amet'    | _
        'Diceret' | _
        'Action'  | _
    }

    @Unroll
    def "should not find a header matching to (#label)"() {
        given:
        to ChallengingDomPage
        when:
        tableHeaderOf(label)
        then:
        RequiredPageContentNotPresent e = thrown()
        and:
        e.toString().startsWith('geb.error.RequiredPageContentNotPresent: The required page content')
        where:
        label          | _
        'NoSuchHeader' | _
    }

    @Unroll
    def "should match a text of (#row, #column) cell"() {
        given:
        to ChallengingDomPage
        expect:
        tableCellOf(row, column).text() == "$prefix$row"
        where:
        row | column | prefix
        0   | 0      | 'Iuvaret'
        0   | 4      | 'Consequuntur'
        1   | 1      | 'Apeirian'
        6   | 2      | 'Adipisci'
        9   | 5      | 'Phaedrum'
    }

    @Unroll
    def "should run 'edit' on (#row) row"() {
        given:
        to ChallengingDomPage
        expect:
        actionEditOn(row).text() == 'edit'
        where:
        row | _
        0   | _
        2   | _
    }

    @Unroll
    def "should run 'delete' on (#row) row"() {
        given:
        to ChallengingDomPage
        expect:
        actionDeleteOn(row).text() == 'delete'
        where:
        row | _
        1   | _
        3   | _
    }
}