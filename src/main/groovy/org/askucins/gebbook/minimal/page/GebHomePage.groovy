package org.askucins.gebbook.minimal.page

import geb.Page
import groovy.util.logging.Slf4j
import org.askucins.gebbook.minimal.module.ManualsMenuModule

@Slf4j
class GebHomePage extends Page {
    static url = "https://gebish.org"
    static at = { title == "Geb - Very Groovy Browser Automation" }
    static content = {
        manualsMenu { module(ManualsMenuModule) }
    }
}