package org.askucins.internet.draganddrop

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec

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
    }

    def "should drag and drop box Left over box Right"() {
        given:
        to DragAndDropPage
        expect:
        true
    }
}