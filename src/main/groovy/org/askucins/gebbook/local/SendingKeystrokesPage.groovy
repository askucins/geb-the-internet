package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class SendingKeystrokesPage extends GebLocalPage {
    static url = 'sending-keystrokes.html'
    static at = { title == 'Sending Keystrokes' }
    static content = {
        inputText { $('input', type: 'text') }
        inputDemo { $('input.demo') }
        inputAnother { $('input.another') }
        inputEnabler { $('input.enabler') }
        anySelector { $('select') }
        petSelector { $('select#pet-select') }
        plantSelector { $('select#plant-select') }
        textMe { $('textarea#text-me') }
    }
}
