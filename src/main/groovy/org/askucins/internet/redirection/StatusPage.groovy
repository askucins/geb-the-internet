package org.askucins.internet.redirection

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class StatusPage extends InternetPage {
    static url = 'status_codes'
    static at = {
        header == 'Status Codes'
    }
}
