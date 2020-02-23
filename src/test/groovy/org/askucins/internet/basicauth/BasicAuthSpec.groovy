package org.askucins.internet.basicauth

import com.browserup.bup.BrowserUpProxy
import com.browserup.bup.BrowserUpProxyServer
import com.browserup.bup.proxy.auth.AuthType
import geb.driver.CachingDriverFactory
import groovy.util.logging.Slf4j
import org.askucins.internet.InternetSpec
import org.openqa.selenium.UnhandledAlertException
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Unroll

import static org.askucins.utils.CustomizedFirefoxDriver.customizedFirefoxDriver

@Ignore
@Slf4j
class BasicAuthSpec extends InternetSpec {
    static correctDomain = System.getProperty('geb.build.baseUrl').toURI().host

    @Shared
    BrowserUpProxy proxy

    void createDriverWithAuthorizationProxy(String domain, String username, String password, AuthType authType) {
        proxy = new BrowserUpProxyServer()
        proxy.autoAuthorization(domain, username, password, authType)
        proxy.start()
        driver = customizedFirefoxDriver([proxy: proxy])
    }

    def setupSpec() {
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
    }

    def cleanup() {
        resetBrowser()
        CachingDriverFactory.clearCacheAndQuitDriver()
        proxy.stop()
        driver.quit()
    }

    def "should pass basic auth with correct credentials"() {
        when:
        createDriverWithAuthorizationProxy(correctDomain, 'admin', 'admin', AuthType.BASIC)
        then:
        to BasicAuthPage
        cleanup:
        report "Basic Auth passed test"
    }

    @Unroll
    def "should not pass basic auth with incorrect credentials (D:#domain|U:#username|P:#password)"() {
        when:
        createDriverWithAuthorizationProxy(domain, username, password, AuthType.BASIC)
        to BasicAuthPage
        then:
        thrown(UnhandledAlertException)
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