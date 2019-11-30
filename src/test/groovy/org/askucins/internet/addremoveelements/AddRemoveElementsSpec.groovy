package org.askucins.internet.addremoveelements

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll

@Slf4j
class AddRemoveElementsSpec extends InternetSpec {
    def "should not be any added elements when started"() {
        when:
        to AddRemoveElementsPage
        then:
        elements.size() == 0
        cleanup:
        report "AddRemoveElements empty page"
    }

    def "should single adding element increase the number of visible elements by one"() {
        given:
        to AddRemoveElementsPage
        when:
        add()
        then:
        elements.size() == 1
        cleanup:
        report "AddRemoveElements one element"
    }

    def "should adding and removing elements from beginning work"() {
        given:
        to AddRemoveElementsPage
        when:
        add()
        add()
        deleteFirst()
        add()
        deleteFirst()
        deleteFirst()
        add()
        add()
        deleteFirst()
        then:
        elements.size() == 1
    }

    def "should adding and removing random elements work"() {
        given:
        to AddRemoveElementsPage
        when:
        add()
        add()
        add()
        deleteRandom()
        add()
        deleteRandom()
        deleteRandom()
        add()
        deleteRandom()
        add()
        deleteRandom()
        then:
        elements.size() == 1
    }

    @Unroll
    def "should multiple adding element increase the number of visible elements respectively (#attempts)"() {
        given:
        to AddRemoveElementsPage
        when:
        (0..<attempts).each { add() }
        then:
        elements.size() == attempts
        cleanup:
        report "AddRemoveElements $attempts element/s"
        where:
        attempts | _
        1        | _
        5        | _
        10       | _
        50       | _
    }

    @Unroll
    def "should adding and removing last element keeps the number of displayed elements intact (#attempts)"() {
        given:
        to AddRemoveElementsPage
        (0..<attempts).each { add() }
        when: "added one more"
        add()
        then:
        elements.size() == attempts + 1
        when: "removing that last added"
        report "AddRemoveElements before removing that last one ($attempts element/s)"
        deleteLast()
        then:
        elements.size() == attempts
        cleanup:
        report "AddRemoveElements after removing that last one ($attempts element/s)"
        where:
        attempts | _
        1        | _
        5        | _
        10       | _
        50       | _
    }

    @Unroll
    def "should adding and removing first element keeps the number of displayed elements intact (#attempts)"() {
        given:
        to AddRemoveElementsPage
        (0..<attempts).each { add() }
        when: "added one more"
        add()
        then:
        elements.size() == attempts + 1
        when: "removing the first element"
        report "AddRemoveElements before removing the first one ($attempts element/s)"
        deleteFirst()
        then:
        elements.size() == attempts
        cleanup:
        report "AddRemoveElements after removing the first one ($attempts element/s)"
        where:
        attempts | _
        1        | _
        5        | _
        10       | _
        50       | _
    }

    @Unroll
    def "should adding and removing random element keeps the number of displayed elements intact (#attempts)"() {
        given:
        to AddRemoveElementsPage
        (0..<attempts).each { add() }
        when: "added one more"
        add()
        then:
        elements.size() == attempts + 1
        when: "removing a random element"
        report "AddRemoveElements before removing the random one ($attempts element/s)"
        deleteRandom()
        then:
        elements.size() == attempts
        cleanup:
        report "AddRemoveElements after removing the random one ($attempts element/s)"
        where:
        attempts | _
        1        | _
        5        | _
        10       | _
        50       | _
    }

    @Unroll
    def "should adding and removing all elements from the end leaving zero elements (#attempts)"() {
        when:
        to AddRemoveElementsPage
        (0..<attempts).each { add() }
        then:
        elements.size() == attempts
        when:
        (0..<attempts).each { deleteLast() }
        then:
        elements.size() == 0
        where:
        attempts | _
        1        | _
        5        | _
        10       | _
        50       | _
    }

    @Unroll
    def "should adding and removing all elements from the beginning leaving zero elements (#attempts)"() {
        when:
        to AddRemoveElementsPage
        (0..<attempts).each { add() }
        then:
        elements.size() == attempts
        when:
        (0..<attempts).each { deleteFirst() }
        then:
        elements.size() == 0
        where:
        attempts | _
        1        | _
        5        | _
        10       | _
        50       | _
    }

    @Unroll
    def "should adding and removing all elements randomly leaving zero elements (#attempts)"() {
        when:
        to AddRemoveElementsPage
        (0..<attempts).each { add() }
        then:
        elements.size() == attempts
        when:
        (0..<attempts).each { deleteRandom() }
        then:
        elements.size() == 0
        where:
        attempts | _
        1        | _
        5        | _
        10       | _
        50       | _
    }
}