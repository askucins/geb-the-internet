package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class DynamicNavigatorPage extends GebLocalPage {
    static url = 'dynamic-navigator.html'
    static content = {
        item { text -> $("li", text: endsWith(text)).module(DynamicNavigatorListItem) }
    }
    static at = {
        title == 'Dynamic Navigator'
    }
}
