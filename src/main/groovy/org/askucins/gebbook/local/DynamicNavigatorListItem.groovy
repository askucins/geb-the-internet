package org.askucins.gebbook.local

import geb.Module
import groovy.util.logging.Slf4j

@Slf4j
class DynamicNavigatorListItem extends Module {
    static content = {
        textElement { $("span") }
        upButton { $("button", text: "⇑") }
    }

    void moveUpBy(int count) {
        count.times {
            log.info "Above to move berry {}. time", it + 1
            upButton.click()
            log.info "Moved {}. time", it + 1
        }
    }

    @Override
    String text() {
        textElement.text()
    }
}
