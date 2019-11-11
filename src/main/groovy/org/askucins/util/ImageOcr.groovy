package org.askucins.util

import net.sourceforge.tess4j.Tesseract

class ImageOcr {
    static String extractTextFromImage(byte[] image) {
        File imageFile = new File('/tmp', 'image.png')
        imageFile.withOutputStream {
            it.write(image)
        }
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(System.getProperty('org.askucins.tesseract'))
        tesseract.doOCR(imageFile)
    }
}
