package org.askucins.internet.draganddrop

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec

@Slf4j
class DragAndDropSpec extends InternetSpec {
    def "should open DragAndDrop page"() {
        expect:
        to DragAndDropPage
    }
}
