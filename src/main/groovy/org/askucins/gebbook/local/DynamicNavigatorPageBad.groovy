package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class DynamicNavigatorPageBad extends DynamicNavigatorPage {
    static content = {
        item { text -> $("li", text: endsWith(text)).module(DynamicNavigatorListItem) }
    }
}
