package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class OpenLinkWithDelayPage extends GebLocalPage {
    static url = 'open-link-with-delay.html'
    static at = { title == 'Open link with a delay' }
}
