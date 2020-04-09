package org.askucins.internet.largedeepdom

import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class LargeDeepDomPage extends InternetPage {
    static url = 'large'
    static content = {
        sectionLabel { $('h4') }
        siblings { $('div#siblings') }
        siblingParent { siblings.$('div.parent', id: ~/sibling-.*/) }
        siblingParentById { id -> siblingParent.filter(id: id).first() }
        childrenOf { Navigator parent -> parent.children()[(0..1)] }
        childrenById { id ->
            Navigator parent = siblingParentById(id)
            childrenOf(parent)
        }
        familyById { id ->
            Navigator parent = siblingParentById(id)
            Navigator children = childrenOf(parent)
            //[parent] + children // Just a list
            $(parent, children)   //Navigator composed of 3 parts
        }
        tierOfFamilyMember { member -> member.classes().grep { it =~ /tier/ }.first() }
        itemOfFamilyMember { member -> member.classes().grep { it =~ /item/ }.first() }

        theTable { $('table#large-table') }
        tableHead { theTable.$('thead') }
        tableHeader { tableHead.$('th') }
        tableHeaderAt { Integer position -> tableHeader[(position - 1)].first().text() }
        tableBody { theTable.$('tbody') }
        tableRows { tableBody.$('tr') }
        tableCellAtRowInColumnOf { Integer row, Integer col ->
            tableRows[(row - 1)].first().$('td')[(col - 1)].first().text()
        }

    }

    static at = {
        header == 'Large & Deep DOM'
    }
}
