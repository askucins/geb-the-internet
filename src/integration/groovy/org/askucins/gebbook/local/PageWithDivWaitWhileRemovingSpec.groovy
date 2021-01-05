package org.askucins.gebbook.local

import groovy.util.logging.Slf4j

@Slf4j
class PageWithDivWaitWhileRemovingSpec extends PageWithDivWaitSpec {

    def "should pass with 'wait' when removing element (wait: true)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(500, 4000)

        when:
        addNewFile()
        then:
        addedFilesWait
        and:
        addedFilesWait.size() == 1

        when:
        report "before-removing-file"
        removeNewFile(0)
        then:
        addedFilesNotPresent
        cleanup:
        report "after-removing-file"
    }

    def "should pass with 'wait' when removing only one element and keeping others (wait: true)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(500, 4000)

        when:
        addNewFile()
        addNewFile()
        addNewFile()
        addNewFile()
        addNewFile()
        then:
        addedFilesWait
        and:
        addedFilesCount(5)

        when:
        report "before-removing-file"
        removeNewFile(2)
        then:
        addedFilesCount(4)
        cleanup:
        report "after-removing-file"
    }

    def "should pass with 'wait' when removing one element by one (wait: true)"() {
        given:
        to PageWithDivPage
        js.configTimeouts(500, 4000)

        when:
        addNewFile()
        addNewFile()
        addNewFile()
        then:
        addedFilesWait
        and:
        addedFilesCount(3)

        when:
        removeNewFile(0)
        then:
        addedFilesCount(2)
        when:
        removeNewFile(0)
        then:
        addedFilesCount(1)
        when:
        removeNewFile(0)
        then:
        addedFilesCount(0)

        cleanup:
        report "after-removing-files-one-by-one"
    }
}
