package org.askucins.gebbook.minimal.page

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class TheBookOfGebPage extends Page {
    static at = { title.startsWith("The Book Of Geb") }
}