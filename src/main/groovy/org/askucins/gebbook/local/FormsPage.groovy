package org.askucins.gebbook.local

import geb.Module
import groovy.util.logging.Slf4j

@Slf4j
class BasicFormModule extends Module {
    static content = {
        dslBasicForm { $('form#basic-form').basicForm() } // Gotcha! Shortcut to Navigator
    }
}

@Slf4j
class FormsPage extends GebLocalPage {
    static url = 'forms.html'
    static at = { title == 'Forms' }
    static content = {
        dslBasicForm { $('form#basic-form').basicForm() } // Gotcha! Shortcut to Navigator
        dslBasicFormModule { module BasicFormModule }
    }
}


// Gotcha! Please notice, that if you only read those values,
// then you don't need those ending ().
// However when you want to change values, you will need those parentheses,
// because you need to return a Navigator object.