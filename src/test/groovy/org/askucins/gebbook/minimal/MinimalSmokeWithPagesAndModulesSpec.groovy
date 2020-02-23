package org.askucins.gebbook.minimal

import groovy.util.logging.Slf4j
import org.askucins.gebbook.minimal.page.GebHomePage
import org.askucins.gebbook.minimal.page.TheBookOfGebPage

@Slf4j
class MinimalSmokeWithPagesAndModulesSpec extends MinimalBaseSpec {
    def "should navigate to the Book Of Geb"() {
        expect:
        drive {
            to GebHomePage
            manualsMenu.open()
            manualsMenu.links.first().click()
            at TheBookOfGebPage
        }
    }
}
