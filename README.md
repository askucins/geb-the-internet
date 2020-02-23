Learning Geb with The Internet (version 0.57.0 - 14, July 2019)
=======

# An excerpt from [https://github.com/tourdedave/the-internet](https://github.com/tourdedave/the-internet_)

An example application that captures prominent and ugly functionality found on the web. Perfect for writing automated acceptance tests against.

Deployed and available at [http://the-internet.herokuapp.com](http://the-internet.herokuapp.com).

## Available Examples:

+ Done! [A/B Testing](http://the-internet.herokuapp.com/abtest)
+ Done! [Add/Remove Elements](http://the-internet.herokuapp.com/add_remove_elements/)
+ Done! [Basic Auth](http://the-internet.herokuapp.com/basic_auth) (user and pass: admin)
+ Done! [Broken Images](http://the-internet.herokuapp.com/broken_images)
+ Done! [Challenging DOM](http://the-internet.herokuapp.com/challenging_dom)
+ Done! [Checkboxes](http://the-internet.herokuapp.com/checkboxes)
+ [Context Menu](http://the-internet.herokuapp.com/context_menu)
+ [Digest Authentication](http://the-internet.herokuapp.com/digest_auth) (user and pass: admin)
+ [Disappearing Elements](http://the-internet.herokuapp.com/disappearing_elements)
+ [Drag and Drop](http://the-internet.herokuapp.com/drag_and_drop)
+ [Dropdown](http://the-internet.herokuapp.com/dropdown)
+ [Dynamic Content](http://the-internet.herokuapp.com/dynamic_content)
+ [Dynamic Controls](http://the-internet.herokuapp.com/dynamic_controls)
+ [Dynamic Loading](http://the-internet.herokuapp.com/dynamic_loading)
+ [Entry Ad](http://the-internet.herokuapp.com/entry_ad)
+ [Exit Intent](http://the-internet.herokuapp.com/exit_intent)
+ [File Download](http://the-internet.herokuapp.com/download)
+ [File Upload](http://the-internet.herokuapp.com/upload)
+ [Floating Menu](http://the-internet.herokuapp.com/floating_menu)
+ [Forgot Password](http://the-internet.herokuapp.com/forgot_password)
+ [Form Authentication](http://the-internet.herokuapp.com/login)
+ [Frames](http://the-internet.herokuapp.com/frames)
+ [Geolocation](http://the-internet.herokuapp.com/geolocation)
+ [Horizontal Slider](http://the-internet.herokuapp.com/horizontal_slider)
+ [Hovers](http://the-internet.herokuapp.com/hovers)
+ [Infinite Scroll](http://the-internet.herokuapp.com/infinite_scroll)
+ W-I-P [Inputs](http://the-internet.herokuapp.com/inputs)
+ [JQuery UI Menus](http://the-internet.herokuapp.com/jqueryui/menu)
+ [JavaScript Alerts](http://the-internet.herokuapp.com/javascript_alerts)
+ [JavaScript onload event error](http://the-internet.herokuapp.com/javascript_error)
+ [Key Presses](http://the-internet.herokuapp.com/key_presses)
+ Done! [Large & Deep DOM](http://the-internet.herokuapp.com/large)
+ Done! [Multiple Windows](http://the-internet.herokuapp.com/windows)
+ [Nested Frames](http://the-internet.herokuapp.com/nested_frames)
+ [Notification Messages](http://the-internet.herokuapp.com/notification_message)
+ Done! [Redirect Link](http://the-internet.herokuapp.com/redirector)
+ [Secure File Download](http://the-internet.herokuapp.com/download_secure)
+ [Shifting Content](http://the-internet.herokuapp.com/shifting_content)
+ [Slow Resources](http://the-internet.herokuapp.com/slow)
+ [Sortable Data Tables](http://the-internet.herokuapp.com/tables)
+ [Status Codes](http://the-internet.herokuapp.com/status_codes)
+ Done! [Typos](http://the-internet.herokuapp.com/typos)
+ [WYSIWYG Editor](http://the-internet.herokuapp.com/tinymce)

## Getting Started with The Internet

Install your dependencies:
```bash
bundle install
```

Start the server:
```bash
rackup
```

Load the page you want to see in your browser:
```text
http://localhost:9292/url_path
```
    
## Docker image

- A Docker Image of the-internet ([link](https://hub.docker.com/r/gprestes/the-internet/))

# Notes

## Gradle
See more at: https://guides.gradle.org/building-groovy-libraries/

### Init
```
gradle init --type groovy-library
```

### Passing a configuration from gradle to JVM
Inspired by [https://stackoverflow.com/questions/28985395/gradle-gebconfig-groovy-parameterized](https://stackoverflow.com/questions/28985395/gradle-gebconfig-groovy-parameterized)

In ```build.gradle``` pass the gradle project property to system property:
```groovy
tasks.withType(Test) {
    systemProperty 'org.askucins.webdriver', project.findProperty('webdriver')
    // [...]
}
```

Then in the ```GebConfig.groovy``` select your webdriver based on the passed system property:
```groovy
import org.openqa.selenium.firefox.FirefoxDriver

import static org.askucins.utils.WebDriverConfiguration.chromeDriver

switch (System.getProperty('org.askucins.webdriver')) {
    case 'firefox':
        driver = { new FirefoxDriver() }
        break
    case 'chrome':
        driver = { chromeDriver([headless: false]) }
        break
    default:
        driver = { chromeDriver([headless: true]) }
}
```

and if you want to e.g. run execute tests with firefox you may run this:
```bash
gw test -Pwebdriver=firefox
```

#### Update 2020-02-23
Although that works just fine, one can use rather the built-in concept of GebConfig.environments, so then in ```build.gradle``` there would be:
```groovy
tasks.withType(Test) {
    systemProperty 'geb.env', project.findProperty('webdriver') ?: 'chrome'
    // [...]
}
```
And then in the ```GebConfig.groovy```:
```groovy
environments {
    firefox {
        atCheckWaiting = 1
        driver = { new FirefoxDriver() }
    }
    firefoxHeadless {
        atCheckWaiting = 1
        driver = { customizedFirefoxDriver([headless: true]) }
    }
    chrome {
        driver = { customizedChromeDriver([headless: false]) }
    }
    chromeHeadless {
        driver = { customizedChromeDriver([headless: true]) }
    }
}
```

With that setup if the gradle project property 'webdriver' is not defined that 'chrome' environment will be used, 
while when it is defined - an appropriate environment will be picked up, e.g.
```bash
gw test -Pwebdriver=firefox
```  

## Geb

### Finding vs filtering

* A method `$(...)` (or its alias `find(...)`) searches in depth, looking for descendants
* Methods `filter(...)` and `not(...)` search in breadth, limiting the found content.

And a quote from [The Book of Geb](https://gebish.org/manual/current/):
> The `find()` and `$()` *methods* support the exact same argument types as the $() *function*.
> The `filter()` , `not()` , `has()` and `hasNot()` *methods* have the same signatures - 
> they accept: a selector string, a predicates map or both. 
> These methods return a new navigator object that represents the new content.

# Questions

* Can webdriver access a JS script from the page source?

