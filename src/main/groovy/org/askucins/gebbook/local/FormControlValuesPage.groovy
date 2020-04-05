package org.askucins.gebbook.local

import geb.module.Select
import groovy.util.logging.Slf4j

@Slf4j
class FormControlValuesPage extends GebLocalPage {
    static url = 'form-control-values.html'
    static at = {
        title == 'Form control values'
    }
    static content = {
        artist { $('form#example-of-select').artist() }
        // Hmm... it's quit weird, that Geb does not have an API for that:
        artistText { artist.$('option', value: artist.value()).text() }
        // Except it is not :) There is API which wraps both:
        artistModule { artist.module(Select) }
    }
}
