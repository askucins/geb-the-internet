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
        headerNav { $('div#content div.example h3') }
        boxes { $('div#columns') }
        boxOfLabel { boxes.$('div.column').filter(text: it) }
        boxLeft { $('div#column-a') }
        boxRight { $('div#column-b') }
        label { it.$('header').text() }
    }
}
