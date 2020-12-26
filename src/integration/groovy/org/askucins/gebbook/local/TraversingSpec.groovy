package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

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
        then:
        verifyAll {
            $('p').next().size() == 6
            $('p').next() == $($('p.a'), $('p.b'), $('p.c', 0..1), $('p.d'), $('p.e'))
        }
        and: "Found: ą->c[0], a->c[0], b->c[0], c[0]->c[1] - hence 4 elements, as expected"
        verifyAll {
            $('p').next('.c').size() == 4
            $('p').next('.c') == $($('p.c', 0), $('p.c', 0), $('p.c', 0..1))
            $('p').next(class: 'c') == $($('p.c', 0), $('p.c', 0), $('p.c', 0..1))
            $('p').next('p', class: 'c') == $($('p.c', 0), $('p.c', 0), $('p.c', 0..1))
        }
        cleanup:
        log.info "Result: {}", $('p').next()*.text()
        log.info "Result: {}", $('p').next('.c')*.text()
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
            $('p').parent('.c') == $('div.c')
            $('p').parent(class: 'c') == $('div.c')
            $('p').parent('div', class: 'c') == $('div.c')

            $('p').closest('.b') == $('div.b')
            $('p').closest(class: 'b') == $('div.b')
            $('p').closest('div', class: 'b') == $('div.b')

            $('p').closest('.a') == $('div.a')
            $('p').closest(class: 'a') == $('div.a')
            $('p').closest('div', class: 'a') == $('div.a')

            $('p').parent('div') == $('div.c')
            $('p').closest('div') == $('div.c')
            $('p').closest('.cls') == $('div.b')
            !$('p').parent('.cls')
        }
        and:
        verifyAll {
            $('p').parent() == $('div.c')
            $('p').closest('p').size() == 0
            $('p').parents().first() == $('p').parent()
            $('p').parents()[0] == $('p').parent()
            $('p').parents()[1] == $('p').parent().parent()
            $('p').parents()[2] == $('p').parent().parent().parent()
            $('p').parents()[3] == $('p').parent().parent().parent().parent()
            $('p').parents()[4] == $('p').parent().parent().parent().parent().parent()
            $('p').parents() == $(
                $('p').parent(),
                $('p').parent().parent(),
                $('p').parent().parent().parent(),
                $('p').parent().parent().parent().parent(),
                $('p').parent().parent().parent().parent().parent(),
            )
            $('p').parents()[0] == $('div.c')
            $('p').parents()[1] == $('div.b')
            $('p').parents()[2] == $('div.a')
            $('p').parents()[3] == $('body')
            $('p').parents()[4] == $('html')
            $('p').parents() == $('div.c').add('div.b').add('div.a').add('body').add('html')
            $('p').parents() == $($('div.c'), $('div.b'), $('div.a'), $('body'), $('html'))
            // Difference between parents(...) and closest(...)
            $('p').parents('div') == $($('div.c'), $('div.b'), $('div.a'))
            $('p').closest('div') == $('div.c')
            $('p').parentsUntil('body') == $($('div.c'), $('div.b'), $('div.a'))
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
