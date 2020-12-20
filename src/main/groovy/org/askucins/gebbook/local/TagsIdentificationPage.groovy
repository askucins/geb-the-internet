package org.askucins.gebbook.local

import groovy.util.logging.Slf4j
import org.askucins.gebbook.GebLocalPage

@Slf4j
class TagsIdentificationPage extends GebLocalPage {
    static url = 'tags-identification.html'
    static at = { title == 'Tags identification' }
    static content = {}
}
