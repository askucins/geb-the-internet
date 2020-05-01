package org.askucins.internet.draganddrop

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import org.openqa.selenium.Point
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

@Slf4j
class DragAndDropSpec extends InternetSpec {

    def "should open DragAndDrop page"() {
        expect:
        to DragAndDropPage
    }

    def "should be boxes displayed"() {
        when:
        to DragAndDropPage
        then:
        label(boxLeft) == 'A'
        and:
        label(boxRight) == 'B'
        and:
        boxOfLabel('A') == boxLeft
        and:
        boxOfLabel('B') == boxRight
        cleanup:
        report 'final'
    }

    def "should drag and drop box Left over box Right by 'Actions'"() {
        given:
        to DragAndDropPage
        def actions = new Actions(driver)
        and:
        WebElement leftBoxElem = boxLeft.firstElement()
        WebElement rightBoxElem = boxRight.firstElement()
        assert leftBoxElem.text == 'A'
        assert rightBoxElem.text == 'B'
        and:
        def switchBoxes = actions
            .moveToElement(headerNav.firstElement())
            .dragAndDrop(leftBoxElem, rightBoxElem)
            .click(rightBoxElem)
            .build()
        when:
        switchBoxes.perform()
        sleep(5000)
        then:
        leftBoxElem.text == 'B'
        and:
        rightBoxElem.text == 'A'
        cleanup:
        report "final-by-actions"
        log.info "Left:{}, Right:{}", boxLeft.text, boxRight.text
    }

    def "should drag and drop box Left over box Right by 'interact'"() {
        given:
        to DragAndDropPage
        assert label(boxLeft) == 'A'
        assert label(boxRight) == 'B'
        when:
        interact {
            dragAndDrop(boxLeft, boxRight)
        }
        then:
        label(boxLeft) == 'B'
        and:
        label(boxRight) == 'A'

        cleanup:
        report "final-by-interact"
        log.info "Left:{}, Right:{}", boxLeft.text, boxRight.text

    }

    def "should drag and drop box Left over box Right by 'interact and offset'"() {
        given:
        to DragAndDropPage
        assert label(boxLeft) == 'A'
        assert label(boxRight) == 'B'

        def source = new Point(boxLeft.x + boxLeft.width.intdiv(2), boxLeft.y + boxLeft.height.intdiv(2))
        def target = new Point(boxRight.x + boxRight.width.intdiv(2), boxRight.y + boxRight.height.intdiv(2))
        def offset = new Point(target.x - source.x, target.y - source.y)
        when:
        interact {
            clickAndHold(boxLeft)
            moveToElement(boxRight)
            release()
        }
        then:
        label(boxes.$('div#column-a')) == 'B'
        and:
        label(boxes.$('div#column-b')) == 'A'

        cleanup:
        report "final-by-offset"
        log.info "Source: {}, Target: {}, Offset: {}", source, target, offset
    }
}
