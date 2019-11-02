package org.askucins.gebbook.minimal.module

import geb.Module
import groovy.util.logging.Slf4j

@Slf4j
class ManualsMenuModule extends Module {
    static content = {
        toggle { $("div.menu a.manuals") }
        linksContainer { $("#manuals-menu") }
        links { linksContainer.find("a") }
    }

    void open() {
        log.info "About to click in the menu..."
        toggle.click()
        waitFor { !linksContainer.hasClass("animating") }
    }
}
