package org.askucins.gebbook.local

import geb.Module
import org.slf4j.Logger
import org.slf4j.LoggerFactory

// TODO for some reason Geb does not understand that 'log' via annotation in modules!
//@Slf4j
class DynamicNavigatorListItem extends Module {

    //TODO as a workaround added logger explicitly
    Logger log = LoggerFactory.getLogger("DynamicNavigatorListItem")

    static content = {
        textOfItem { $("span") }
        upButton { $("button", text: "⇑") }
        downButton { $("button", text: "⇓") }
    }

    void moveUpBy(int count) {
        count.times {
            log.info "Above to move berry {}. time UP", it + 1
            upButton.click()
            log.info "Moved {}. time", it + 1
        }
    }

    void moveDownBy(int count) {
        count.times {
            log.info "Above to move berry {}. time DOWN", it + 1
            downButton.click()
            log.info "Moved {}. time", it + 1
        }
    }

    @Override
    String text() {
        textOfItem.text()
    }
}
