package book

import org.slf4j.LoggerFactory
import spock.lang.Shared
import spock.lang.Specification
import geb.Browser

// TODO @Slf4j not used b/c https://github.com/spockframework/spock/issues/491
class SmokeSpec extends Specification {
    @Shared
    def log = LoggerFactory.getLogger(this.class)

    def setupSpec() {
        log.info "Select an alternative chromedriver"
        System.properties.'webdriver.chrome.driver' = '/opt/webdriver/chromedriver'
        log.info System.properties.'geb.build.reportsDir'.toString()
    }

    def "should open the Gebish page"() {
        expect:
        Browser.drive {
            go "http://gebish.org"
            report "Geb-home-page"
            assert title == "Geb - Very Groovy Browser Automation"
        }
    }
}