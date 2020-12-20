package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class SmokePage extends GebLocalPage {
    static url = 'smoke.html'
    static at = { title == "Geb Local" }
    static content = {
        heading { $("h3", class: "heading") }    // Contains a given class
        headingOnly { $("h3[class='heading']") } // Has only this one class
        headingNotAnother { $("h3:not(.another)") } // CSS pseudo-class
        headingNotAnotherAgain { $("h3").not(".another") }

        rangesAndIndexes { $('#ranges-and-indexes') }
        attributeAndTextMatching { $('#attribute-and-text-matching') }
        navigatorsAreIterable { $('#navigators-are-iterable') }
        navigatorsAreIterableAnother { $('#navigators-are-iterable-another') }
        navigatorsAreIterableAnotherAgain { $('#navigators-are-iterable-another-again') }
        equalsAndHashcode { $('#equals-and-hashcode') }

        findingAndFiltering { $('#finding-and-filtering') }
        composition { $('#composition') }
        pElement { String pClass -> composition.$('p', class: pClass) }

    }
}
