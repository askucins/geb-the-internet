package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class Traversing3Page extends GebLocalPage {
    static url = 'traversing-3.html'
    static at = { title == "Traversing 3" }
    static content = {}
}
