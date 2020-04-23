package org.askucins.internet.draganddrop

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class DragAndDropPage extends InternetPage {
    static url = 'drag_and_drop'
    static at = {
        header == 'Drag and Drop'
    }
    static content = {
        boxes { $('div#columns') }
        boxLeft { boxes.$('div#column-a') }
        boxRight { boxes.$('div#column-b') }
        label { it.$('header').text() }
        boxOfLabel { boxes.$('div.column').filter(text: it) }
    }
}