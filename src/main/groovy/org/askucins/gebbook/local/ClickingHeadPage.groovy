package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class ClickingHeadPage extends GebLocalPage {
    static url = 'clicking-head.html'
    static at = { title == 'Clicking - head' }
    static content = {
        clicker { $('div.clicker a') }
    }
}
