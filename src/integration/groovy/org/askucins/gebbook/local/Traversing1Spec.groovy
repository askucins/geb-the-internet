package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class Traversing1Spec extends GebLocalSpec {

    def "should open traversing-1 page"() {
        expect:
        to Traversing1Page
        cleanup:
        report("Traversing-1")
    }

    def "should traversing work"() {
        when:
        to Traversing1Page
        then:
        verifyAll {
            $('p.d').previous() == $('p.c')
            $('p.e').prevAll() == $('p.c').add('p.d')
            $('p.d').next() == $('p.e')
            $('p.c').nextAll() == $('p.d').add('p.e')
            $('p.d').parent() == $('div.b')
            $('p.c').siblings() == $('p.d').add('p.e')
            $('div.a').children() == $('div.b').add('div.f')
        }
        and: //TODO review this...
        false
    }
}
