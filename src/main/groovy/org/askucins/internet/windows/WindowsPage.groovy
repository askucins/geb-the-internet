package org.askucins.internet.windows

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class WindowsPage extends InternetPage {
    static url = 'windows'
    static content = {
        newWindowLink { $('div.example a') }
    }
    static at = {
        header == 'Opening a new window'
        newWindowLink.attr('href').endsWith('/windows/new')
        newWindowLink.attr('target') == '_blank'
        newWindowLink.text() == 'Click Here'
    }
}
