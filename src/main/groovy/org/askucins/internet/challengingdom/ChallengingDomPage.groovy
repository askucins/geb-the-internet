package org.askucins.internet.challengingdom

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage
import org.openqa.selenium.InvalidArgumentException

@Slf4j
class ChallengingDomPage extends InternetPage {
    static url = 'challenging_dom'
    static content = {
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
        buttonNormal { $('a.button').not('.alert').not('.success').first() }
        buttonAlert { $('a.button.alert').first() }
        buttonSuccess { $('a.button.success').first() }
        buttonOf(to: ChallengingDomPage) {
            switch (it) {
                case 'blue': buttonNormal; break
                case 'red': buttonAlert; break
                case 'green': buttonSuccess; break
                default: throw new InvalidArgumentException()
            }
        }
        tableHeader { $('div table thead tr th') }
        tableHeaderOf { label -> tableHeader.filter(text: label) }
        tableRows { $('div table tbody tr') }
        tableCellOf { Integer row, Integer col -> tableRows[(row)].$('td')[(col)] }
        actionOf { Integer row -> tableCellOf(row, 6) }
        actionEditOn { Integer row -> actionOf(row).$('a', href: '#edit') }
        actionDeleteOn { Integer row -> actionOf(row).$('a', href: '#delete') }
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
