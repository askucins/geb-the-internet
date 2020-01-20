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
        count.times { upButton.click() }
    }

    @Override
    String text() {
        textElement.text()
    }
}
