package book

import org.slf4j.LoggerFactory
import spock.lang.Shared
import spock.lang.Specification
import geb.Browser
import spock.util.environment.RestoreSystemProperties

// TODO @Slf4j not used b/c https://github.com/spockframework/spock/issues/491
@RestoreSystemProperties
class SmokeSpec extends Specification {
    @Shared
    def log = LoggerFactory.getLogger(this.class)

    def setupSpec() {
        def sp = System.properties

        sp.'webdriver.chrome.driver' = sp.'webdriver.chrome.driver' ?: '/opt/webdriver/chromedriver'
        log.info "Path to chromedriver: ${sp.'webdriver.chrome.driver'}"

        sp.'geb.build.reportsDir' = sp.'geb.build.reportsDir' ?: "reports"
        log.info "Additional Geb reports in: ${sp.'geb.build.reportsDir'}"
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