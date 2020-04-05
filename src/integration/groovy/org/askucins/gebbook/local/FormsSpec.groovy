package org.askucins.gebbook.local

import geb.navigator.Navigator
import groovy.util.logging.Slf4j

@Slf4j
class FormsSpec extends GebLocalSpec {

    def "should open forms page"() {
        expect:
        to FormsPage
    }

    def "should manage forms by 'name' property - shortcuts"() {
        when:
        to FormsPage
        then:
        $('form#basic-form').basicForm == 'testing'
        when:
        $('form#basic-form').basicForm = 'goodness'
        then:
        $('form#basic-form').basicForm == 'goodness'

    }

    // These above are literary shortcuts for these:
    def "should manage forms by 'name' property"() {
        given:
        to FormsPage
        when:
        $('form#basic-form').find('input', name: 'basicForm').value('goodness')
        then:
        $('form#basic-form').find('input', name: 'basicForm').value() == 'goodness'
        and:
        $('form#basic-form').$('input', name: 'basicForm').value() == 'goodness'
    }

    //Gotcha! When use parenthesis and when not.
    def "should carefully use parenthesis or not in form addressing"() {
        when:
        to FormsPage
        then: "with parenthesis - this is a navigator"
        $('form#basic-form').basicForm() instanceof Navigator
        and: "like"
        $('form#basic-form').basicForm() == $('form#basic-form').find('input', name: 'basicForm')
        and: "without parenthesis - this is a value"
        $('form#basic-form').basicForm == 'testing'
        and: "like"
        $('form#basic-form').basicForm == $('form#basic-form').find('input', name: 'basicForm').value()
    }

    // Page or Module DSL - this is a little tricky but 100% gebish
    def "should manage forms by 'name' property - page or module dsl"() {
        given:
        to FormsPage
        when:
        dslBasicForm = 'foobar' // Gotcha! This will be rendered to dslBasicForm.value('foobar')
        then:
        dslBasicForm == 'foobar'
        and:
        dslBasicFormModule.dslBasicForm == 'foobar'

        when:
        dslBasicFormModule.dslBasicForm = 'barfoo' // Gotcha! This will be rendered to dslBasicForm.value('foobar')
        then:
        dslBasicFormModule.dslBasicForm == 'barfoo'
        and:
        dslBasicForm == 'barfoo'
    }
}
