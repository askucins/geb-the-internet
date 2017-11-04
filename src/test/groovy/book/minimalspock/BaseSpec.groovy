package book.minimalspock

import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import spock.util.environment.RestoreSystemProperties

@Slf4j
@RestoreSystemProperties
class BaseSpec extends GebSpec {

    def setupSpec() {
        def sp = System.properties

        sp.'geb.build.reportsDir' = sp.'geb.build.reportsDir' ?: "reports"
        log.info "Additional Geb reports in: ${sp.'geb.build.reportsDir'}"

        sp.'webdriver.chrome.driver' = sp.'webdriver.chrome.driver' ?: '/opt/webdriver/chromedriver'
        log.info "Path to chromedriver: ${sp.'webdriver.chrome.driver'}"

        sp.'webdriver.gecko.driver' = sp.'webdriver.gecko.driver' ?: '/opt/webdriver/geckodriver'
        log.info "Path to geckodriver: ${sp.'webdriver.gecko.driver'}"
    }
}
