package org.askucins.gebbook.minimal

import groovy.util.logging.Slf4j
import org.askucins.gebbook.minimal.page.GebHomePage
import org.askucins.gebbook.minimal.page.TheBookOfGebPage

@Slf4j
class MinimalSpockWithPagesAndModulesSpec extends MinimalBaseSpec {
    def "should access The Book Of Geb via homepage"() {
        given:
        log.info "About to open GebHomePage..."
        to GebHomePage
        report "Geb-Home-Page"
        when:
        manualsMenu.open()
        manualsMenu.links.first().click()
        then:
        log.info "And now on the Book of Geb..."
        at TheBookOfGebPage
        cleanup:
        report "The-Book-Of-Geb"
    }
}
