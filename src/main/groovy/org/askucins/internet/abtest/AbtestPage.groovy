package org.askucins.internet.abtest

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class AbtestPage extends Page {
    static url = 'abtest'
    static content = {
        headerVariant { $('div#content div.example h3').first().text() }
        info { $('div#content div.example p').first().text() }
    }
    static headerVariants = ['A/B Test Control', 'A/B Test Variation 1']
    static at = {
        headerVariants.contains(headerVariant)
    }


}
