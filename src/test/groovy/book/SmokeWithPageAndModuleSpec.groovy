package book

import geb.Module
import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class ManualsMenuModule extends Module {
    static content = {
        toggle { $("div.menu a.manuals") }
        linksContainer { $("#manuals-menu") }
        links { linksContainer.find("a") }
    }

    void open() {
        log.info "About to click in the menu..."
        toggle.click()
        waitFor { !linksContainer.hasClass("animating") }
    }
}

@Slf4j
class GebHomePage extends Page {
    static url = "http://gebish.org"
    static at = { title == "Geb - Very Groovy Browser Automation" }
    static content = {
        manualsMenu { module(ManualsMenuModule) }
    }
}

@Slf4j
class TheBookOfGebPage extends Page {
    static at = { title.startsWith("The Book Of Geb") }
}

@Slf4j
class SmokeWithPageAndModuleSpec extends BaseSpec {
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
