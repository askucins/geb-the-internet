package org.askucins.gebbook.local

import geb.error.SingleElementNavigatorOnlyMethodException
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
class CssPropertiesSpec extends GebLocalSpec {
    def "should open focus page"() {
        expect:
        to CssPropertiesPage
        cleanup:
        report("Focus")
    }

    def "should return css properties"() {
        when:
        to CssPropertiesPage
        then:
        verifyAll {
            $('div.example-a').css('float') == 'left'
            $('div.example-b').css('background-color') == 'rgba(0, 128, 0, 1)'
            $('div.example-a').css('visibility') == 'collapse'
            $('div.example-b').css('visibility') == 'visible'
        }
    }

    def "should css() throw an error when applied to a non-sole navigator"() {
        given:
        to CssPropertiesPage
        when:
        $('div').css('columns')
        then:
        SingleElementNavigatorOnlyMethodException e = thrown()
        and:
        $('div')[0].css('visibility') == 'collapse'
        and:
        $('div')[1].css('visibility') == 'visible'

    }
}
