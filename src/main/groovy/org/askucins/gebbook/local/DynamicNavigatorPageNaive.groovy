package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

// This works perfectly fine, however it does not have any Geb smart-ass features of modules.

@Slf4j
class DynamicNavigatorPageNaive extends DynamicNavigatorPage {
    static content = {
        items { $("li") }
        itemByText { text -> $("li", text: endsWith(text)) }
        textOfItem { item -> item.$("span") }
        upButton { item -> item.$("button", text: "⇑") }
        downButton { item -> item.$("button", text: "⇓") }
    }

    void moveUpBy(String text, Integer count) {
        count.times {
            log.info "Above to move berry {} {}. time UP", textOfItem(itemByText(text)).text(), it + 1
            upButton(itemByText(text)).click()
            log.info "Moved {} {}. time", textOfItem(itemByText(text)).text(), it + 1
        }
    }

    void moveDownBy(String text, Integer count) {
        count.times {
            log.info "Above to move berry {} {}. time DOWN", textOfItem(itemByText(text)).text(), it + 1
            downButton(itemByText(text)).click()
            log.info "Moved {} {}. time", textOfItem(itemByText(text)).text(), it + 1
        }
    }

    List<String> giveMeThoseBerries() {
        items.collect { textOfItem(it).text() }
    }
}
