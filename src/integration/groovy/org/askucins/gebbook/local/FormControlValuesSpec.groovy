package org.askucins.gebbook.local

import geb.error.RequiredPageValueNotPresent
import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec
import org.openqa.selenium.Keys

@Slf4j
class FormControlValuesSpec extends GebLocalSpec {

    def "should open form-control-values page"() {
        expect:
        to FormControlValuesPage
    }

    def "should control 'select' element"() {
        given:
        to FormControlValuesPage

        expect:
        artist() instanceof Navigator
        and: "value of the first option, by default"
        artist().value() == '1'

        when: 'selecting by a matching value'
        artist = '1'
        then:
        artist() == '1'
        and:
        artistText == 'Ima Robot'

        when: 'selecting by a matching value with an argument coercion'
        artist = 2
        then:
        artist() == '2'
        and:
        artistText == 'Edward Sharpe and the Magnetic Zeros'

        when: 'selecting by matching text of given option'
        artist = 'Alexander'
        then:
        artist() == '3'
        and:
        artistText == 'Alexander'

        when:
        artist = 'There is no such one'
        then:
        thrown(IllegalArgumentException)
    }

    def "should control 'select' element by Select module"() {
        given:
        to FormControlValuesPage

        expect:
        artistModule() instanceof Navigator
        and: "value of the first option, by default"
        artistModule().getSelected() == '1'
        and:
        artistModule().selected == '1'

        when: 'selecting by a matching value'
        artistModule = '1'
        then:
        artistModule() == '1'
        and:
        artistModule.selectedText == 'Ima Robot'

        when: 'selecting by a matching value with an argument coercion'
        artistModule = 2
        then:
        artistModule() == '2'
        and:
        artistModule.selectedText == 'Edward Sharpe and the Magnetic Zeros'

        when: 'selecting by matching text of given option'
        artistModule = 'Alexander'
        then:
        artistModule() == '3'
        and:
        artistModule.selectedText == 'Alexander'

        when: 'selecting by a matching value - () style'
        artistModule.setSelected('1')
        then:
        artistModule.getSelected() == '1'
        and:
        artistModule.selectedText == 'Ima Robot'

        when:
        artistModule = 'There is no such one'
        then:
        thrown(IllegalArgumentException)
    }

    def "should control 'multiple select' element"() {
        given:
        to FormControlValuesPage

        when: 'selecting by a matching value'
        genres = ['2', '3']
        then:
        genres() == ['2', '3']
        and:
        genresText == ['Chiptunes', 'Electroclash']

        when: 'selecting by a matching value with an argument coercion'
        genres = [1, 4, 5]
        then:
        genres() == ['1', '4', '5']
        and:
        genresText == ['Alt folk', 'G-Funk', 'Hair metal']

        when: 'selecting by matching text of given option'
        genres = ['Alt folk', 'Hair metal']
        then:
        genres() == ['1', '5']
        and:
        genresText == ['Alt folk', 'Hair metal']

        when: 'deselecting all'
        genres = []
        then:
        genres() == []
        and:
        genresText == []

        when:
        genres = [1, 6]
        then:
        thrown(IllegalArgumentException)
    }

    def "should control 'multiple select' element by Select module"() {
        given:
        to FormControlValuesPage

        when: 'selecting by a matching value'
        genresModule = ['2', '3']
        then:
        genresModule() == ['2', '3']
        and:
        genresModule.selectedText == ['Chiptunes', 'Electroclash']

        when: 'selecting by a matching value with an argument coercion'
        genresModule = [1, 4, 5]
        then:
        genresModule() == ['1', '4', '5']
        and:
        genresModule.selectedText == ['Alt folk', 'G-Funk', 'Hair metal']

        when: 'selecting by matching text of given option'
        genresModule = ['Alt folk', 'Hair metal']
        then:
        genresModule() == ['1', '5']
        and:
        genresModule.selectedText == ['Alt folk', 'Hair metal']

        when: 'selecting by matching text of given option - () style'
        genresModule.setSelected(['Alt folk', 'Hair metal'])
        then:
        genresModule.getSelected() == ['1', '5']
        and:
        genresModule.getSelectedText() == ['Alt folk', 'Hair metal']

        when: 'deselecting all'
        genresModule = []
        then:
        genresModule() == []
        and:
        genresModule.selectedText == []

        when:
        genresModule = [1, 6]
        then:
        thrown(IllegalArgumentException)
    }

    def "should control 'checkbox' element"() {
        given:
        to FormControlValuesPage

        when:
        pet = true
        then:
        pet == 'dog' // Gotcha! since it's checked the value of 'value' field is returned
        and:
        pet() == 'dog'

        when:
        pet = false
        then:
        pet().value() == null
        and:
        pet.value() == null
    }

    def "should control 'checkbox' element by Checkbox module"() {
        given:
        to FormControlValuesPage

        when:
        petModule.check()
        then:
        petModule.isChecked()
        and:
        petModule() == 'dog'

        when:
        petModule.uncheck()
        then:
        petModule.isUnchecked()
        and:
        petModule.value() == null

    }

    def "should control multiple checkboxes"() {
        given:
        to FormControlValuesPage

        when: "assigned by a value"
        pets = 'dog'
        then:
        pets()*.value().size() == 3
        and:
        pets()*.value() == ['dog', null, null]

        when: "assigned by a label"
        pets = 'Canis familiaris'
        then:
        pets()*.value() == ['dog', null, null]
        and:
        pets.filter(value: 'dog').first().value()
        and:
        petsOf('dog')
        and:
        !pets.filter(value: 'lizard').first().value()

        when: "This does not seem working, strange.."
        petsOf('lizard')
        then:
        thrown(RequiredPageValueNotPresent)

        when: "assigned by values"
        pets = ['dog', 'lizard']
        then:
        pets()*.value() == ['dog', null, 'lizard']

        when: "assigned by labels"
        pets = ['Canis familiaris', 'Lacerta']
        then:
        pets()*.value() == ['dog', null, 'lizard']

    }

    def "should control multiple checkboxes by Checkbox module"() {
        given:
        to FormControlValuesPage

        when:
        petsModule('dog').check()
        then:
        petsModule('dog').isChecked()
        and:
        petsModule('lizard').isUnchecked()
    }

    def "should control 'radio' element"() {
        given:
        to FormControlValuesPage

        expect:
        site()*.value() == [null, null]

        when:
        site = 'current'
        then:
        site == 'current'
        and:
        site()*.value() == ['current', null]

        when: "setting by label"
        site = 'Search Google'
        then:
        site == 'google'
        and:
        site()*.value() == [null, 'google']

        when: "setting by label"
        site = 'Search this site'
        then:
        site == 'current'
        and:
        site()*.value() == ['current', null]

        when:
        site = 'google'
        then:
        site == 'google'
        and:
        site()*.value() == [null, 'google']
    }

    def "should control 'text' element"() {
        given:
        to FormControlValuesPage

        when:
        language = "Hello, world!"
        then:
        language == "Hello, world!"
        and:
        language() == "Hello, world!"
        and:
        language.value() == "Hello, world!"

        when:
        description = 'This is Ala.\nAla ma kota.'
        then:
        description == 'This is Ala.\nAla ma kota.'
        and:
        description() == 'This is Ala.\nAla ma kota.'
        and:
        description.value() == 'This is Ala.\nAla ma kota.'

        when:
        description() << " Even more! "
        then:
        description == 'This is Ala.\nAla ma kota. Even more! '

        when:
        description() << Keys.BACK_SPACE
        then:
        description == 'This is Ala.\nAla ma kota. Even more!'

    }

    def "should control 'text' element by TextInput module"() {
        given:
        to FormControlValuesPage

        when:
        languageModule = "Hello, world!"
        then:
        languageModule == "Hello, world!"
    }

    def "should control 'input file' element"() {
        given:
        to FormControlValuesPage
        File csv = new File(this.class.classLoader.getResource('test.csv').toURI())

        when:
        csvFile = csv.absolutePath
        then:
        csvFile
        and:
        csvFile.value() == "C:\\fakepath\\${csv.name}" // Gotcha! See https://stackoverflow.com/questions/4851595/how-to-resolve-the-c-fakepath
        cleanup:
        report("To be uploaded")

    }
}
