package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class Traversing2Page extends GebLocalPage {
    static url = 'traversing-2.html'
    static at = { title == "Traversing 2" }
    static content = {}
}
