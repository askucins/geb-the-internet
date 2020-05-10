package org.askucins.internet

import geb.Page
import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import org.openqa.selenium.Point

@Slf4j
abstract class InternetPage extends Page {
    static content = {
        header { $('div#content div.example h3').first().text() }
    }
    static at = {
        title == 'The Internet'
    }

    static Point centerOf(Navigator navigator, Integer addressBarOffset = 0, Integer deviceScale = 1) {
        new Point((navigator.x + navigator.width.intdiv(2)) * deviceScale, (navigator.y + navigator.height.intdiv(2) + addressBarOffset) * deviceScale)
    }

}
