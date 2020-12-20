package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class Traversing1Page extends GebLocalPage {
    static url = 'traversing-1.html'
    static at = { title == "Traversing 1" }
    static content = {}
}
