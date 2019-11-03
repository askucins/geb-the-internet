package org.askucins.internet.brokenimages

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec

@Slf4j
class BrokenImagesSpec extends InternetSpec {

    Integer getHttpStatus(String uri) {
        try {
            uri.toURL().openConnection().with {
                requestMethod = 'HEAD'
                connect()
                responseCode
            }
        } catch (e) {
            log.warn "Error: ${e.stackTrace}"
        }
    }

    def "should find some broken images"() {
        when:
        to BrokenImagesPage
        then:
        images.size() == 3
        when:
        Map statuses = images.collect { imageUri(it) }.collectEntries { [(it): getHttpStatus(it.toString())] }
        then:
        statuses.values().grep { it != 200 }.size() == 2
        cleanup:
        log.debug "Result: " + statuses
    }
}