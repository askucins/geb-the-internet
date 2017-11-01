package book

import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
class GebSpec extends Specification {

    def setupSpec() {
        def sp = System.properties

        sp.'webdriver.chrome.driver' = sp.'webdriver.chrome.driver' ?: '/opt/webdriver/chromedriver'
        log.info "Path to chromedriver: ${sp.'webdriver.chrome.driver'}"

        sp.'geb.build.reportsDir' = sp.'geb.build.reportsDir' ?: "reports"
        log.info "Additional Geb reports in: ${sp.'geb.build.reportsDir'}"
    }
}