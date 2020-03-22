package org.askucins.gebbook.local

import geb.Module
import groovy.util.logging.Slf4j

@Slf4j
class BasicFormModule extends Module {
    static content = {
        basicForm { $('form#basic-form').basicForm() }
    }
}

@Slf4j
class FormsPage extends GebLocalPage {
    static url = 'forms.html'
    static at = { title == 'Forms' }
    static content = {
        basicForm { $('form#basic-form').basicForm() }
        basicFormModule { module BasicFormModule }
    }
}
