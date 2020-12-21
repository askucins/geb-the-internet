package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class DynamicNavigatorPageGood extends DynamicNavigatorPage {
    static content = {
        items { $("li").moduleList(DynamicNavigatorListItem) } //Gotcha!
        itemByText { text -> $("li", text: endsWith(text), dynamic: true).module(DynamicNavigatorListItem) }
    }
}
