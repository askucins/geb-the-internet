package org.askucins.internet.brokenimages

import geb.Page
import groovy.util.logging.Slf4j

@Slf4j
class BrokenImagesPage extends Page {
    static url = 'broken_images'
    static content = {
        header { $('div#content div.example h3').first().text() }
        images { $('div#content div.example img') }
        imageUri { image -> image.attr('src') }
    }
    static at = {
        header == 'Broken Images'
    }
}
