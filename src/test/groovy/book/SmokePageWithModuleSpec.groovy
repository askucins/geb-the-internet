package book

import geb.Browser
import geb.Module
import geb.Page

class ManualsMenuModule extends Module {
    static content = {
        toggle { $("div.menu a.manuals") }
        linksContainer { $("#manuals-menu") }
        links { linksContainer.find("a") }
    }

    void open() {
        toggle.click()
        waitFor { !linksContainer.hasClass("animating") }
    }
}

class GebHomePage extends Page {
    static url = "http://gebish.org"
    static at = { title == "Geb - Very Groovy Browser Automation" }
    static content = {
        manualsMenu { module(ManualsMenuModule) }
    }
}

class TheBookOfGebPage extends Page {
    static at = { title.startsWith("The Book Of Geb") }
}

class SmokePageWithModuleSpec extends GebSpec {
    def "should navigate to the Book Of Geb"() {
        expect:
        Browser.drive {
            to GebHomePage
            manualsMenu.open()
            manualsMenu.links.first().click()
            at TheBookOfGebPage
        }
    }
}