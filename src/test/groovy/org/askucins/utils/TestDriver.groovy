package org.askucins.utils

enum TestDriver {
    FIREFOX('firefox'),
    CHROME('chrome'),
    CHROMEHEADLESS('chromeHeadless')
    private String name

    TestDriver(String name) {
        this.name = name
    }

    String toString() {
        name
    }
}