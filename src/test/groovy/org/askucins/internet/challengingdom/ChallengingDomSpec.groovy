package org.askucins.internet.challengingdom

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import org.askucins.util.ImageOcr
import org.openqa.selenium.WebElement
import spock.lang.Unroll

@Slf4j
class ChallengingDomSpec extends InternetSpec {
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
        imageContent.startsWith 'Answer: '

        cleanup:
        log.info "OCRed: " + imageContent

        where:
        selector | _
        'ID'     | _
        'JS'     | _
    }

    @Unroll
    def "should react when #color button is clicked"() {
        expect:
        false
        where:
        color   | button
        'blue'  | null
        'read'  | null
        'green' | null
    }

    @Unroll
    def "should find a header matching to (#label)"() {
        expect:
        false
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
        expect:
        false
        where:
        label          | _
        'NoSuchHeader' | _
    }

    @Unroll
    def "should match a text of (#row, #column) cell"() {
        expect:
        false
        where:
        row | column | prefix
        0   | 0      | 'luvaret'
    }

    @Unroll
    def "should run 'edit' on (#row) row"() {
        expect:
        false
        where:
        row | _
        0   | _
    }

    @Unroll
    def "should run 'delete' on (#row) row"() {
        expect:
        false
        where:
        row | _
        0   | _
    }
}