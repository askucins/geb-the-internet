package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class FormControlValuesSpec extends GebLocalSpec {

    def "should open form-control-values page"() {
        expect:
        to FormControlValuesPage
    }

    def "should control 'select' element"() {
        given:
        to FormControlValuesPage

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
        pet() == 'dog'

        when:
        pet = false
        then:
        pet().value() == null
    }
}
