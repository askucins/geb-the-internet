package org.askucins.internet.basicauth

import com.browserup.bup.BrowserUpProxy
import com.browserup.bup.BrowserUpProxyServer
import com.browserup.bup.proxy.auth.AuthType
import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import org.openqa.selenium.UnhandledAlertException
import spock.lang.Shared
import spock.lang.Unroll
import spock.util.environment.RestoreSystemProperties

import static org.askucins.utils.CustomizedFirefoxDriver.customizedFirefoxDriver

@RestoreSystemProperties
@Slf4j
class BasicAuthSpec extends InternetSpec {
    @Shared
    String correctDomain
    @Shared
    BrowserUpProxy proxy

    BrowserUpProxy runProxyWithAuthorization(String domain, String username, String password, AuthType authType) {
        proxy = new BrowserUpProxyServer()
        proxy.autoAuthorization(domain, username, password, authType)
        proxy.start()
        proxy
    }

    def setupSpec() {
        System.setProperty('geb.build.baseUrl', 'http://the-internet.herokuapp.com/') //GOTCHA proxy over http!
        correctDomain = System.getProperty('geb.build.baseUrl').toURI().host
        log.info "Reference host: {}", correctDomain
    }

    def cleanup() {
        driver?.quit()
        proxy?.stop()
    }

    def "should pass basic auth with correct credentials"() {
        when:
        proxy = runProxyWithAuthorization(correctDomain, 'admin', 'admin', AuthType.BASIC)
        driver = customizedFirefoxDriver([proxy: proxy])
        browser.config.atCheckWaiting = false
        then:
        to BasicAuthPage
        cleanup:
        report "Basic Auth passed test"
    }

    @Unroll
    def "should not pass basic auth with incorrect credentials (D:#domain|U:#username|P:#password)"() {
        given:
        proxy = runProxyWithAuthorization(domain, username, password, AuthType.BASIC)
        driver = customizedFirefoxDriver([proxy: proxy])
        browser.config.atCheckWaiting = false // Gotcha!
        when:
        to BasicAuthPage
        then:
        thrown(UnhandledAlertException) // Gotcha! If atCheckWaiting is true there is a WaitTimeoutException instead.
        cleanup:
        report "Basic Auth failed test of $tc"

        where:
        tc   | domain           | username | password
        '01' | correctDomain    | 'adminx' | 'admin'
        '02' | correctDomain    | 'admin'  | 'adminx'
        '03' | correctDomain    | 'adminx' | 'adminx'
        '04' | 'no.such.domain' | 'admin'  | 'admin'
    }
}