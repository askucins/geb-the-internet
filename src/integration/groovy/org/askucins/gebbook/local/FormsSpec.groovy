package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class FormsSpec extends GebLocalSpec {
    def "should open forms page"() {
        expect:
        to FormsPage
    }

    def "should manage forms by 'name' property"() {
        when:
        to FormsPage
        then:
        $('form#basic-form').basicForm == 'testing'
        when:
        $('form#basic-form').basicForm = 'goodness'
        then:
        $('form#basic-form').basicForm == 'goodness'

        // These above are literary shortcuts for these:
        when:
        $('form#basic-form').find('input', name: 'basicForm').value('goodness')
        then:
        $('form#basic-form').find('input', name: 'basicForm').value() == 'goodness'
        and:
        $('form#basic-form').$('input', name: 'basicForm').value() == 'goodness'

        // And here one can catch that whole input too:
        and:
        $('form#basic-form').basicForm() == $('form#basic-form').find('input', name: 'basicForm')

        // Page or Module DSL - this is a little tricky but 100% gebish
        when:
        basicForm = 'foobar'
        then:
        basicForm == 'foobar'
        and:
        basicFormModule.basicForm == 'foobar'
    }
}
