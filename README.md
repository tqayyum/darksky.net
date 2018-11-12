# Intro
This projects is used to automate tests for web/mobile
## Installation

1. Install version of Java 8.0
2. Install and configure Maven in your bash_profile or `brew install mvn`
3. Download and install intelliJ idea
4. Install node `brew install node`
5. Install Appium `npm install -g appium`
6. Install Carthage `brew install carthage`
7. Install webdriver agent from here `https://github.com/facebook/WebDriverAgent`
6. Install xcode from mac app store 
8. Install android studio and setup sdk path in your bash profile 
    `export ANDROID_HOME=/Users/your_username/Library/Android/sdk,
    export PATH=$PATH:$ANDROID_HOME/platform-tools,
    export PATH=$PATH:$ANDROID_HOME/tools`
9. Create Android emulator from AVD
10. clone this repo
11. import this project in your intelliJ idea as `Maven` project

## Usage

1. Open terminal
2. CD to the project directory:
3. Goto config.properties file and make required changes before running the test
4. enter: `mvn clean test` to run the test (Note: you can run individual tags using this command `mvn test -Dcucumber.options="--tags @darksky-1"`)
5. enter: `mvn site` to generate the HTML Allure report 
6. goto this path in project directory to view the report `/target/site/allure-maven-plugin/index.html`

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

1. Tahir Qayyum