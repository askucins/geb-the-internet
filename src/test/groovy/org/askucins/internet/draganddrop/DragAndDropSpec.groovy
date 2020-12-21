package org.askucins.internet.draganddrop

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import org.openqa.selenium.Point
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import spock.util.environment.RestoreSystemProperties

import java.awt.*
import java.awt.event.InputEvent

import static org.askucins.internet.InternetPage.centerOf
import static org.askucins.utils.CustomizedChromeDriver.customizedChromeDriver

@RestoreSystemProperties
@Slf4j
class DragAndDropSpec extends InternetSpec {
    def setupSpec() {
        //driver = customizedChromeDriver([headless: false])
        //System.setProperty('webdriver.chrome.verboseLogging', 'true')
    }

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
        WebElement source = boxLeft.firstElement()
        WebElement target = boxRight.firstElement()
        assert source.text == 'A'
        assert target.text == 'B'
        log.info "Before: Left:{}, Right:{}", source.text, target.text
        and:
        def switchBoxes = actions.dragAndDrop(source, target).build()
        when:
        switchBoxes.perform()
        then:
        source.text == 'B'
        and:
        target.text == 'A'
        cleanup:
        report "final-by-actions"
        log.info "After: Left:{}, Right:{}", source.text, target.text
    }

    def "should drag and drop box Left over box Right by 'interact drag-and-drop'"() {
        given:
        to DragAndDropPage
        assert label(boxLeft) == 'A'
        assert label(boxRight) == 'B'
        log.info "Before: Left:{}, Right:{}", label(boxLeft), label(boxRight)
        when:
        interact {
            dragAndDrop(boxLeft, boxRight)
        }
        then:
        label(boxLeft) == 'B'
        and:
        label(boxRight) == 'A'

        cleanup:
        report "final-by-interact-drag-and-drop"
        log.info "After: Left:{}, Right:{}", label(boxLeft), label(boxRight)

    }

    def "should drag and drop box Left over box Right by 'interact'"() {
        given:
        to DragAndDropPage
        assert label(boxLeft) == 'A'
        assert label(boxRight) == 'B'
        log.info "Before: Left:{}, Right:{}", label(boxLeft), label(boxRight)
        when:
        interact {
            moveToElement(boxLeft)
            pause(1000)
            clickAndHold(boxLeft)
            pause(1000)
            moveToElement(boxRight)
            pause(1000)
            release()
            pause(1000)
        }
        then:
        label(boxLeft) == 'B'
        and:
        label(boxRight) == 'A'

        cleanup:
        report "final-by-interact"
        log.info "After: Left:{}, Right:{}", label(boxLeft), label(boxRight)

    }

    def "should drag and drop box Left over box Right by 'interact and offset'"() {
        given:
        to DragAndDropPage
        assert label(boxLeft) == 'A'
        assert label(boxRight) == 'B'
        log.info "Before: Left:{}, Right:{}", label(boxLeft), label(boxRight)
        and:
        def source = centerOf(boxLeft)
        def target = centerOf(boxRight)
        def offset = new Point(target.x - source.x+20, target.y - source.y + 20)
        when:
        interact {
            moveToElement(boxLeft)
            pause(2000)
            clickAndHold(boxLeft)
            pause(2000)
            moveByOffset(offset.x, offset.y)
            pause(2000)
            release()
            pause(2000)
        }
        then:
        label(boxLeft) == 'B'
        and:
        label(boxRight) == 'A'

        cleanup:
        report "final-by-offset"
        log.info "Source: {}, Target: {}, Offset: {}", source, target, offset
        log.info "After: Left:{}, Right:{}", label(boxLeft), label(boxRight)
    }

    def "should drag and drop box Left over box Right by 'robot'"() {
        given:
        to DragAndDropPage
        assert label(boxLeft) == 'A'
        assert label(boxRight) == 'B'
        log.info "Before: Left:{}, Right:{}", label(boxLeft), label(boxRight)
        and:
        Robot robot = new Robot()
        Integer scale = 2 // dpi?
        Integer addressBarOffset = 75 // address bar etc..
        Integer sleepTime = 1000
        def source = centerOf(boxLeft, addressBarOffset, scale)
        def target = centerOf(boxRight, addressBarOffset, scale)
        def offset = new Point(target.x - source.x, target.y - source.y)
        when:
        robot.mouseMove(source.x, source.y)
        sleep sleepTime
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        sleep sleepTime
        robot.mouseMove(target.x, target.y)
        sleep sleepTime
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
        sleep sleepTime
        then:
        label(boxLeft) == 'B'
        and:
        label(boxRight) == 'A'

        cleanup:
        report "final-by-offset"
        log.info "Source: {}, Target: {}, Offset: {}", source, target, offset
        log.info "After: Left:{}, Right:{}", label(boxLeft), label(boxRight)
    }
}
