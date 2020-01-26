package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class SmokeSpec extends GebLocalSpec {

    def "should open smoke page"() {
        expect:
        to SmokePage
        cleanup:
        report("Smoke-page")
    }

    def "should find all elements matching to a given class"() {
        when:
        to SmokePage
        then:
        heading.size() == 2
        and:
        heading*.text() == ['This is heading.', 'This is another heading.']
    }

    def "should find element only a given class"() {
        when:
        to SmokePage
        then:
        headingOnly.size() == 1
        and:
        headingOnly.text() == 'This is heading.'
    }

    def "should find all elements not containing a given class"() {
        when:
        to SmokePage
        then:
        headingNotAnother.size() == 1
        and:
        headingNotAnother.text() == 'This is heading.'
    }

    def "should find all elements not containing a given class - another approach"() {
        when:
        to SmokePage
        then:
        headingNotAnotherAgain.size() == 1
        and:
        headingNotAnotherAgain.text() == 'This is heading.'
    }

    def "should ranges and indexes work"() {
        when:
        to SmokePage
        then:
        verifyAll(rangesAndIndexes) {
            $("p", 0).text() == 'a'
            $("p", 2).text() == 'c'
            $("p", 0..2)*.text() == ['a', 'b', 'c']
            $("p", 0..1)*.text() == ['a', 'b']
            $("p", 1..2)*.text() == ['b', 'c']
        }
    }

    def "should attributes and text matching work"() {
        when:
        to SmokePage
        then:
        verifyAll(attributeAndTextMatching) {
            $('p', attr1: 'a').size() == 2
            $('p', attr2: 'c').size() == 1
            $('p', attr1: 'x').size() == 0
            $('p', attr1: 'a', attr2: 'c').text() == 'p2'
            $('p', attr1: 'a', attr2: 'x').size() == 0
            $('p', attr1: 'a', attr3: 'b').size() == 0
            $('p', text: 'p1').text() == 'p1'
            $('p', text: 'p').size() == 0
            $('p', text: ~/p/).size() == 0 // Gotcha! Entire string is matched!
            $('p', text: ~/p./).size() == 2
        }
        and:
        verifyAll {
            attributeAndTextMatching.$('p', text: contains('p1')).text() == 'p1'
            attributeAndTextMatching.$('p', attr2: 'c', text: contains('p')).text() == 'p2'
            attributeAndTextMatching.$('p', text: startsWith('p')).size() == 2
            attributeAndTextMatching.$('p', attr1: 'a', text: endsWith('2')).text() == 'p2'
            attributeAndTextMatching.$('p', text: ~/.2/).size() == 2
            // Gotcha! Either a string or pattern!
            attributeAndTextMatching.$('p', text: containsWord('ala')).size() == 1
            attributeAndTextMatching.$('p', text: contains('ala')).size() == 2
            attributeAndTextMatching.$('p', text: containsWord(~/ala/)).size() == 1
            attributeAndTextMatching.$('p', text: contains(~/ala/)).size() == 2
            attributeAndTextMatching.$('p', text: containsWord(~/a.a/)).size() == 1
            attributeAndTextMatching.$('p', text: contains(~/a.a/)).size() == 2
            attributeAndTextMatching.$('p', text: contains(~/\d/)).size() == 4
        }
    }

    def "should navigators work as iterable"() {
        when:
        to SmokePage
        then:
        verifyAll {
            navigatorsAreIterable.$('div').max { it.text() }.text() == '5'
            navigatorsAreIterable.$('div')*.text().max() == '5'
            navigatorsAreIterable.$('div')*.text() == ['1', '3', '1\n5', '5', '4'] // Gotcha!
            navigatorsAreIterable.$('div')*.text().collect {
                it.replaceAll(~/(?xms)\s/, '').toInteger()
            }.max() == 15
        }
        and:
        verifyAll {
            navigatorsAreIterableAnother.$('div')*.text() == ['1', '3', '1\n5', '4'] // Gotcha!
            navigatorsAreIterableAnother.$('div')*.text().collect {
                it.replaceAll(~/(?xms)\s/, '').toInteger()
            }.max() == 15
        }
        and:
        verifyAll {
            navigatorsAreIterableAnotherAgain.$('div')*.text() == ['1', '3', '5', '4'] // Gotcha!
            navigatorsAreIterableAnotherAgain.$('div')*.text().collect {
                it.replaceAll(~/(?xms)\s/, '').toInteger()
            }.max() == 5
        }
    }
}
