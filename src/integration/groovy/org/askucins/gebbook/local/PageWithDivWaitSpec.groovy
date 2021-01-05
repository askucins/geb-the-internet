package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalSpec

@Slf4j
abstract class PageWithDivWaitSpec extends GebLocalSpec {

    def setupSpec() {
        log.info "Checking if configuration has not been changed"
        ConfigObject config = new ConfigSlurper().parse(this.class.classLoader.getResource('GebConfig.groovy'))
        assert config.waiting.timeout == 5
        assert config.waiting.retryInterval == 0.5
        assert config.waiting.presets.quick.timeout == 3
    }
}
