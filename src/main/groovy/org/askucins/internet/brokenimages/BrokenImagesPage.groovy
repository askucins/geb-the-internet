package org.askucins.internet.brokenimages

import groovy.util.logging.Slf4j
import org.askucins.internet.InternetPage

@Slf4j
class BrokenImagesPage extends InternetPage {
    static url = 'broken_images'
    static content = {
        images { $('div#content div.example img') }
        imageUri { image -> image.attr('src') }
    }
    static at = {
        header == 'Broken Images'
    }
}
