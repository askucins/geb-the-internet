package org.askucins.gebbook.local

import geb.module.Checkbox
import geb.module.MultipleSelect
import geb.module.Select
import geb.module.TextInput
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

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

        genres { $('form#example-of-multiple-select').genres() }
        genresText {
            genres.value().collect { genres.$('option', value: it).text() }
        }
        // or via a module
        genresModule { genres.module(MultipleSelect) }

        pet { $('form#example-of-checkbox').pet() }
        // or via a module
        petModule { pet.module(Checkbox) }

        pets { $('form#example-of-multiple-checkboxes').pet() }
        petsOf { pets.filter(value: it)?.first()?.value() }
        // or via a module
        petsModule { pets.filter(value: it).module(Checkbox) }

        site { $('form#example-of-radio').site() }

        language { $('form#example-of-text').language() }
        description { $('form#example-of-text').description() }

        languageModule { language.module(TextInput) }

        csvFile { $('form#example-of-file-upload').csvFile() }
    }
}
