Learning Geb with The Internet (version 0.58.0 - 10, February 2020)
=======

Examples of [Geb](https://gebish.org/) test automation of various popular use cases available, well... on the Internet :)
A list of such use cases has been collected by [Dave Heaffner](https://github.com/tourdedave) 
and it's available on [https://github.com/tourdedave/the-internet](https://github.com/tourdedave/the-internet).

# Available examples from [The Internet](https://github.com/tourdedave/the-internet)

+ Done! [A/B Testing](http://the-internet.herokuapp.com/abtest)
+ Done! [Add/Remove Elements](http://the-internet.herokuapp.com/add_remove_elements/)
+ Done! [Basic Auth](http://the-internet.herokuapp.com/basic_auth) (user and pass: admin)
+ Done! [Broken Images](http://the-internet.herokuapp.com/broken_images)
+ Done! [Challenging DOM](http://the-internet.herokuapp.com/challenging_dom)
+ Done! [Checkboxes](http://the-internet.herokuapp.com/checkboxes)
+ [Context Menu](http://the-internet.herokuapp.com/context_menu)
+ [Digest Authentication](http://the-internet.herokuapp.com/digest_auth) (user and pass: admin)
+ [Disappearing Elements](http://the-internet.herokuapp.com/disappearing_elements)
+ Done? [Drag and Drop](http://the-internet.herokuapp.com/drag_and_drop) - problem...
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
+ [Shadow DOM](http://the-internet.herokuapp.com/shadowdom)
+ [Shifting Content](http://the-internet.herokuapp.com/shifting_content)
+ [Slow Resources](http://the-internet.herokuapp.com/slow)
+ [Sortable Data Tables](http://the-internet.herokuapp.com/tables)
+ [Status Codes](http://the-internet.herokuapp.com/status_codes)
+ Done! [Typos](http://the-internet.herokuapp.com/typos)
+ [WYSIWYG Editor](http://the-internet.herokuapp.com/tinymce)

## Alternatively: run The Internet examples locally
* Install [rvm](https://rvm.io/) 
* Install ruby 2.4.1
    ```bash
    rvm install ruby-2.4.1
    ```
* Install bundler
    ```bash
    gem install bundler
    ```
* Checkout [The Internet](https://github.com/tourdedave/the-internet)
* Install dependencies
    ```bash
    bundle install
    ```
* Start the server:
    ```bash
    rackup
    ```
* Pages will be available locally:
    ```text
    http://localhost:9292/url_path
    ```

# Notes, how-tos etc.

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
> The `filter()`, `not()`, `has()` and `hasNot()` *methods* have the same signatures - 
> they accept: a selector string, a predicate map or both. 
> These methods return a new navigator object that represents the new content.

# Questions

* Can webdriver access a JS script from the page source?

