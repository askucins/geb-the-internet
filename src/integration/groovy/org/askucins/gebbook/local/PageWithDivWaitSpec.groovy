package org.askucins.gebbook.local

import geb.error.RequiredPageContentNotPresent
import geb.waiting.WaitTimeoutException
import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
class PageWithDivWaitSpec extends GebLocalSpec {

    def setupSpec() {
        log.info "Checking if configuration has not been changed"
        ConfigObject config = new ConfigSlurper().parse(this.class.classLoader.getResource('GebConfig.groovy'))
        assert config.waiting.timeout == 5
        assert config.waiting.retryInterval == 0.5
        assert config.waiting.presets.quick.timeout == 3
    }

    def "should pass with 'wait' when adding element (wait: true)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(5000, 500)
        when:
        addNewFile()
        then:
        addedFilesWait
    }

    def "should fail with 'wait' when adding element and waiting too long (wait: true)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(6000, 500)
        when:
        addNewFile()
        and:
        addedFilesWait
        then:
        WaitTimeoutException error = thrown()
    }

    def "should fail with 'wait' when adding ot required element and waiting too long (wait: true, required: false)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(6000, 500)
        expect: "no exception is thrown"
        !addedFilesWaitAndNotRequired
    }

    def "should fail with 'wait' when adding element (wait: false)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(1000, 500)
        when:
        addNewFile()
        and:
        addedFilesNoWait
        then:
        RequiredPageContentNotPresent error = thrown()
    }

    def "should pass with 'wait' when adding element with explicit waitFor (wait: false)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(1000, 500)
        when:
        addNewFile()
        then:
        waitFor { addedFilesNoWait }
    }

    def "should pass with 'wait' when adding element (wait: preset)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(3000, 500)
        when:
        addNewFile()
        then:
        addedFilesWaitPreset
    }

    def "should fail with 'wait' when adding element and waiting too long (wait: preset)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(4000, 500)
        when:
        addNewFile()
        and:
        addedFilesWaitPreset
        then:
        WaitTimeoutException error = thrown()
    }

    def "should pass with 'wait' when adding element (wait: timeout)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(4000, 500)
        when:
        addNewFile()
        then:
        addedFilesWaitTimeout
    }

    def "should fail with 'wait' when adding element and waiting too long (wait: timeout)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(5000, 500)
        when:
        addNewFile()
        and:
        addedFilesWaitTimeout
        then:
        WaitTimeoutException error = thrown()
    }

    def "should pass with 'wait' when adding element (wait: timeout-retry)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(4000, 500)
        when:
        addNewFile()
        then:
        addedFilesWaitTimeoutRetry
    }

    def "should fail with 'wait' when adding element and waiting too long (wait: timeout-retry)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(5000, 500)
        when:
        addNewFile()
        and:
        addedFilesWaitTimeoutRetry
        then:
        WaitTimeoutException error = thrown()
    }
}
