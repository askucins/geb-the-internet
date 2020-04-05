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
}
