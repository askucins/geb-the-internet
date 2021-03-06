import geb.spock.GebReportingSpec
import groovy.util.logging.Slf4j
import org.askucins.SmokePage

@Slf4j
class SmokeSpec extends GebReportingSpec {
    def "should display title section"() {
        when:
        to SmokePage
        then:
        titleInContent.text() == 'httpstat.us'
    }
}