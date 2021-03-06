# Introduction
This test can attempt to open the Boku payment page using selenium contintually retrying until it succeeds.

# Requirements
This relies on a few things:
1. [Maven](https://maven.apache.org/download.cgi) - you need Apache maven so that you can build this project
1. [ChromeDriver](http://chromedriver.chromium.org/downloads) - this is what enables the test to be able to launch Google Chrome. Once you have extracted this somewhere set the path of chrome driver in `G2aAddMoneyTest.java` file `System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");`
1. Your G2A sso cookie.  Try going to the [G2A Pay Page](https://pay.g2a.com/funds/add) and once logged in you need grab the content of the `g2aSSO` cookie and update it in `G2aAddMoneyTest.java` .  To do this I use the [EditThisCookie](https://chrome.google.com/webstore/detail/edit-this-cookie/fngmhnnpilhplaeedifhccceomclgfbg) Google Chrome extension.
```java
        driver.manage()
                .addCookie(new Cookie.Builder("g2aSSO",
                        "<paste your g2aSSO cookie here>")
                                .domain(".g2a.com").build());
```

# Using
I recommend running the Test in Eclipse using the eclipse testng plugin, but you could also run it directly from the command line with `mvn clean test`