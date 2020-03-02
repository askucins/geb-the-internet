package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class CssPropertiesPage extends GebLocalPage {
    static url = 'css-properties.html'
    static at = { title == 'Css properties' }
    static content = {}
}
