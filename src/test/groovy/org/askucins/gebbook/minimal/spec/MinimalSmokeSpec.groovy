package org.askucins.gebbook.minimal.spec

import geb.Browser
import groovy.util.logging.Slf4j

@Slf4j
class MinimalSmokeSpec extends MinimalBaseSpec {
    def "should open the Gebish page"() {
        expect:
        Browser.drive {
            go "http://gebish.org"
            report "Geb-home-page"
            assert title == "Geb - Very Groovy Browser Automation"
            $("div.menu a.manuals").click()
            waitFor { !$("#manuals-menu").hasClass("animating") }
            $("#manuals-menu a").first().click()
            assert title.startsWith("The Book Of Geb")
        }
    }
}