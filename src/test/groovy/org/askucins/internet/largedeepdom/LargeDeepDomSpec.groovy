package org.askucins.internet.largedeepdom

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import spock.lang.Unroll

@Slf4j
class LargeDeepDomSpec extends InternetSpec {
    def "should open page"() {
        expect:
        to LargeDeepDomPage
    }

    def "should find sections labels"() {
        given:
        to LargeDeepDomPage
        expect:
        sectionLabel*.text() == ['No Siblings', 'Siblings', 'Table']
    }

    def "should find sibling parents"() {
        given:
        to LargeDeepDomPage
        expect:
        siblingParent.size() == 50
    }

    @Unroll
    def "should find specific family (#id)"() {
        given:
        to LargeDeepDomPage
        def parent = siblingParentById(id)
        when:
        def children = childrenOf(parent)
        then:
        children.size() == 2
        where:
        id             | _
        'sibling-1.1'  | _
        'sibling-10.1' | _
        'sibling-47.1' | _
        'sibling-50.1' | _
    }

    @Unroll
    def "should family (#id) has three members"() {
        given:
        to LargeDeepDomPage
        when:
        def family = familyById(id)
        then:
        family.size() == 3
        where:
        id             | _
        'sibling-1.1'  | _
        'sibling-10.1' | _
        'sibling-47.1' | _
        'sibling-50.1' | _
    }

    @Unroll
    def "should a family (#id) share the same tier (#tier)"() {
        given:
        to LargeDeepDomPage
        def family = familyById(id)
        when:
        def tiers = family.collect { tierOfFamilyMember(it) }
        then:
        tiers.sort().unique() == [tier]
        where:
        id             | tier
        'sibling-1.1'  | 'tier-1'
        'sibling-10.1' | 'tier-10'
        'sibling-47.1' | 'tier-47'
        'sibling-50.1' | 'tier-50'
    }

    @Unroll
    def "should a family (#id) have correct 'items'"() {
        given:
        to LargeDeepDomPage
        def family = familyById(id)
        when:
        def items = family.collect { itemOfFamilyMember(it) }
        then:
        items.sort() == ['item-1', 'item-2', 'item-3']
        where:
        id             | _
        'sibling-1.1'  | _
        'sibling-10.1' | _
        'sibling-47.1' | _
        'sibling-50.1' | _
    }

    @Unroll
    def "should children of family (#id) have correct 'items'"() {
        given:
        to LargeDeepDomPage
        def children = childrenById(id)
        when:
        def items = children.collect { itemOfFamilyMember(it) }
        then:
        items.sort() == ['item-2', 'item-3']
        where:
        id             | _
        'sibling-1.1'  | _
        'sibling-10.1' | _
        'sibling-47.1' | _
        'sibling-50.1' | _
    }

    @Unroll
    def "should find correct table header (#position)"() {
        given:
        to LargeDeepDomPage
        expect:
        tableHeaderAt(position) == position.toString()
        where:
        position | _
        1        | _
        10       | _
        47       | _
        50       | _
    }

    @Unroll
    def "should fine correct cell at row: #row, column: #column"() {
        given:
        to LargeDeepDomPage
        expect:
        tableCellAtRowInColumnOf(row, column) == cell
        where:
        row | column || cell
        1   | 1      || '1.1'
        1   | 47     || '1.47'
        47  | 47     || '47.47'
        47  | 1      || '47.1'
        13  | 45     || '13.45'
        50  | 50     || '50.50'
    }
}
