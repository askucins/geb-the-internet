package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class SmokePage extends GebLocalPage {
    static url = 'geb-the-internet/smoke.html'
    static at = { title == "Geb Local" }
    static content = {}
}
