package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class TraversingSpec extends GebLocalSpec {

    def "should open traversing-1 page"() {
        expect:
        to Traversing1Page
        cleanup:
        report("Traversing-1")
    }

    def "should traversing-1 work"() {
        when:
        to Traversing1Page
        then: "should find content around p.d"
        verifyAll {
            $('p.d').previous() == $('p.c') // previous sibling
            $('p.e').prevAll() == $('p.c').add('p.d') // all previous siblings
            $('p.d').next() == $('p.e') // next sibling
            $('p.c').nextAll() == $('p.d').add('p.e') // all next siblings
            $('p.d').parent() == $('div.b')
            $('p.c').siblings() == $('p.d').add('p.e')
            $('div.a').children() == $('div.b').add('div.f')
        }
    }

    def "should open traversing-2 page"() {
        expect:
        to Traversing2Page
        cleanup:
        report("Traversing-2")
    }

    def "should traversing-2 work"() {
        when:
        to Traversing2Page
        then: "This is weird and I don't get it - especially, that it works that way!"
        verifyAll {
            $('p').next() == $('p.b').add('p.c')
            $('p').next('.c') == $('p.c').add('p.c') // WTF??
            $('p').next(class: 'c') == $('p.c').add('p.c')
            $('p').next('p', class: 'c') == $('p.c').add('p.c')
        }
        and:
        verifyAll {
            $('p').size() == 3
            $('p') == $('p.a').add('p.b').add('p.c')
            $('p').next('.c') == $($('p.c'), $('p.c')) // WTF??
            $('p').next('.c').size() == 2 // WTF?
        }
    }

    def "should open traversing-3 page"() {
        expect:
        to Traversing3Page
        cleanup:
        report("Traversing-3")
    }

    def "should traversing-3 work"() {
        when:
        to Traversing3Page
        then:
        verifyAll {
            $('p').parent('.b') == $('div.b')
            $('p').parent(class: 'b') == $('div.b')
            $('p').parent('div', class: 'b') == $('div.b')

            $('p').closest('.a') == $('div.a')
            $('p').closest(class: 'a') == $('div.a')
            $('p').closest('div', class: 'a') == $('div.a')

            $('p').closest('.b') == $('div.b')
            $('p').closest(class: 'b') == $('div.b')
            $('p').closest('div', class: 'b') == $('div.b')
        }
        and:
        verifyAll {
            $('p').parent() == $('div.b')
            $('p').closest('p').size() == 0
            $('p').parents().first() == $('p').parent()
            $('p').parents()[0] == $('p').parent()
            $('p').parents()[1] == $('p').parent().parent()
            $('p').parents()[2] == $('p').parent().parent().parent()
            $('p').parents()[3] == $('p').parent().parent().parent().parent()
            $('p').parents() == $(
                $('p').parent(),
                $('p').parent().parent(),
                $('p').parent().parent().parent(),
                $('p').parent().parent().parent().parent(),
            )
            $('p').parents()[0] == $('div.b')
            $('p').parents()[1] == $('div.a')
            $('p').parents()[2] == $('body')
            $('p').parents()[3] == $('html')
            $('p').parents() == $('div.b').add('div.a').add('body').add('html')
            $('p').parents() == $($('div.b'), $('div.a'), $('body'), $('html'))
            // Difference between parents(...) and closest(...)
            $('p').parents('div') == $($('div.b'), $('div.a'))
            $('p').closest('div') == $('div.b')
            $('p').parentsUntil('body') == $($('div.b'), $('div.a'))
        }
    }

    def "should open traversing-4 page"() {
        expect:
        to Traversing4Page
        cleanup:
        report("Traversing-4")
    }

    def "should traversing-4 work"() {
        when:
        to Traversing4Page
        then:
        verifyAll {
            $('.a').nextUntil('.d') == $('div.b').add('div.c')
            $('.a').nextUntil(class: 'd') == $('div.b').add('div.c')
            $('.a').nextUntil('div', class: 'd') == $('div.b').add('div.c')
        }
    }
}
