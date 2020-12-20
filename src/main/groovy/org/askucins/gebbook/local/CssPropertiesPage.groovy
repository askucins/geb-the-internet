package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class CssPropertiesPage extends GebLocalPage {
    static url = 'css-properties.html'
    static at = { title == 'Css properties' }
    static content = {}
}
