This repo. serves as notes for myself as I went through the Udemy course [Selenium WebDriver with Java -Basics to Advanced Frameworks](https://www.udemy.com/course/selenium-real-time-examplesinterview-questions/)

## Dependencies
- Java
- Maven (https://maven.apache.org/)

## Setup

### Update Login Data
- Change `src/test/java/data/logins.sample.json` to `logins.json`
- Add actual logins to `logins.json` following sample format

### Java and Maven
- Add `JAVA_HOME` environment variable
- Add Maven `bin` folder to `PATH`

### Logins Data
- Rename logins.sample.json data file to logins.json
- Add your own login credentials

## Run Tests
### Command Line / Terminal
**You can run all the tests by running:**

```bash
mvn clean test
```

**You can execute a specific test class directly from the command line:**

```bash
mvn clean test -Dtest="syssu.SubmitOrderTests"
```

**You can execute a specific test within a class directly from the command line:**

```bash
mvn clean test -Dtest="syssu.SubmitOrderTests#submitOrder"
```

**You can run a specific XML file using:**

```bash
mvn clean test -Dsurefire.suiteXmlFiles=./testng.xml
```
**You can create/run Maven tests by profile:**

Modify the `<profiles>` section within `./pom.xml` creating profiles. See existing examples. 

Run example `Regression` profile

```bash
mvn clean test -P Regression
```

Note: I'm not sure why but I had to create two profiles with unique IDs otherwise it appeared to run all the tests as if I just ran `mvn clean test`.


## FAQ
### Windows: CMD vs PS
If you run into any issues running mvn commands on Windows then try using CMD instead of PowerShell.