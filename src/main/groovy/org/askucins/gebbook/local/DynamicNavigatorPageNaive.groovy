package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class DynamicNavigatorPageNaive extends DynamicNavigatorPage {
    static content = {
        items { $("li") }
        item { text -> $("li", text: endsWith(text)) }
        textElement { item -> item.$("span") }
        upButton { item -> item.$("button", text: "⇑") }
    }

    void moveUpBy(String text, Integer count) {
        count.times {
            log.info "Above to move berry {} {}. time", textElement(item(text)).text(), it + 1
            upButton(item(text)).click()
            log.info "Moved {} {}. time", textElement(item(text)).text(), it + 1
        }
    }

    List<String> giveMeThoseBerries() {
        items.collect { textElement(it).text() }
    }
}
