package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
abstract class DynamicNavigatorPage extends GebLocalPage {
    static url = 'dynamic-navigator.html'
    static at = {
        title == 'Dynamic Navigator'
    }
}
