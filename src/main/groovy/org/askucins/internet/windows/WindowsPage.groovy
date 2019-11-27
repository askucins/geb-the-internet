package org.askucins.internet.windows

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class WindowsPage extends Page {
    static url = 'windows'
    static content = {
        header { $('div#content div.example h3').first().text() }
        newWindowLink { $('div.example a') }
    }
    static at = {
        title == 'The Internet'
        header == 'Opening a new window'
        newWindowLink.attr('href').endsWith('/windows/new')
        newWindowLink.attr('target') == '_blank'
        newWindowLink.text() == 'Click Here'
    }
}
