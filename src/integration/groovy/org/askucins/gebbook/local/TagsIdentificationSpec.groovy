package org.askucins.gebbook.local

import geb.error.SingleElementNavigatorOnlyMethodException
import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
class TagsIdentificationSpec extends GebLocalSpec {

    def "should open tags-identification page"() {
        expect:
        to TagsIdentificationPage
        cleanup:
        report("TagsIdentification")
    }

    def "should identify tags by various ways"() {
        when:
        to TagsIdentificationPage
        then:
        verifyAll {
            $('.a').text() == 'a'
            $('.a').tag() == 'p'
            $('.a').@title == 'a' // value of an attribute 'title'
            $('.a').attr('title') == 'a' // value of an attribute 'title'
            $('.a').classes() == ['a', 'para']
        }
        and:
        verifyAll {
            $("p")*.text() == ["a", "b", "c"]
            $("p")*.tag() == ["p", "p", "p"]
            $("p")*.@title == ["a", "b", "c"]
            $("p")*.attr('title') == ["a", "b", "c"]
            $("p")*.classes() == [["a", "para"], ["b", "para"], ["c", "para"]]
        }
        cleanup:
        report("TagsIdentification-examples")
    }

    def "should throw an exception when text() is called on a non-sole navigator"() {
        given:
        to TagsIdentificationPage
        Navigator nav = $('.a').add('.b')
        assert nav.size() == 2
        assert nav*.text() == ['a', 'b']
        when:
        nav.text()
        then:
        SingleElementNavigatorOnlyMethodException e = thrown()
        and:
        nav*.text() == ['a', 'b']
    }

    def "should throw an exception when tag() is called on a non-sole navigator"() {
        given:
        to TagsIdentificationPage
        Navigator nav = $('.a').add('.b')
        assert nav.size() == 2
        assert nav*.text() == ['a', 'b']
        when:
        nav.text()
        then:
        SingleElementNavigatorOnlyMethodException e = thrown()
        and:
        nav*.tag() == ['p', 'p']
    }

    def "should throw an exception when @title is called on a non-sole navigator"() {
        given:
        to TagsIdentificationPage
        Navigator nav = $('.a').add('.b')
        assert nav.size() == 2
        assert nav*.text() == ['a', 'b']
        when:
        nav.@title
        then:
        SingleElementNavigatorOnlyMethodException e = thrown()
        and:
        nav*.@title == ['a', 'b']
    }

    def "should throw an exception when classes() is called on a non-sole navigator"() {
        given:
        to TagsIdentificationPage
        Navigator nav = $('.a').add('.b')
        assert nav.size() == 2
        assert nav*.text() == ['a', 'b']
        when:
        nav.classes()
        then:
        SingleElementNavigatorOnlyMethodException e = thrown()
        and:
        nav*.classes() == [['a', 'para'], ['b', 'para']]
    }
}
