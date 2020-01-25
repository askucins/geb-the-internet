package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class SmokePage extends GebLocalPage {
    static url = 'smoke.html'
    static at = { title == "Geb Local" }
    static content = {
        heading { $("h2", class: "heading") }    // Contains a given class
        headingOnly { $("h2[class='heading']") } // Has only this one class
        headingNotAnother { $("h2:not(.another)") } // CSS pseudo-class
        headingNotAnotherAgain { $("h2").not(".another") }
    }
}
