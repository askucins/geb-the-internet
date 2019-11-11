package org.askucins.internet.challengingdom

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class ChallengingDomPage extends Page {
    static url = 'challenging_dom'
    static content = {
        header { $('div#content div.example h3').first().text() }
        answerCanvasById { $('div canvas#canvas').firstElement() }
        answerCanvasByJs { js.canvas.canvas }
        /*
            TODO - find out what is the difference:
            js.canvas.canvas - a WebElement representing that canvas - what we really need.
            js.canvas - this is the canvas itself I guess...
         */
        answerCanvasBy {
            switch (it) {
                case 'JS': answerCanvasByJs; break
                case 'ID': answerCanvasById; break
                default: answerCanvasById
            }
        }
    }
    static at = {
        header == 'Challenging DOM'
    }

    byte[] extractImageFromCanvas(def canvas) {
        // The output starts with 'data:image/png;base64,' - hence that 22 is to be stripped off
        byte[] canvasBase64 = browser.driver.executeScript("return arguments[0].toDataURL('image/png').substring(22);", canvas)
        Base64.decoder.decode(canvasBase64)
    }
}
