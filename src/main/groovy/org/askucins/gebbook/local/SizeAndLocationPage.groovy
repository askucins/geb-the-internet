package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class SizeAndLocationPage extends GebLocalPage {
    static url = 'size-and-location.html'
    static at = { title == 'Size and location' }
}
