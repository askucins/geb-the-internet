import geb.spock.GebReportingSpec
import groovy.util.logging.Slf4j
import org.askucins.SmokePage

@Slf4j
class SmokeSpec extends GebReportingSpec {
    def "should display title section"() {
        when:
        log.info "About to open a smoke page"
        browser.go(SmokePage.url)
        SmokePage smokePage = browser.page(SmokePage)
        log.info "Smoke page open"
        then:
        smokePage.titleContent.text() == 'httpstat.us'
    }
}