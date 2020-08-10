# test-abc3-domo

Repository to store all the automation test script which includes E2E Scenario, DOMO BI Reporting and Data Migration UI.

This project uses the Gradle build system.You don't need an IDE to build and execute it but IntelliJ IDEA is recommended.

Note: you need Gradle 4.6.1 to build the tests. If you're getting compilation issues, make sure you're not using other version of Gradle

## Quick start
1. Download the project code, preferably using git clone.
2. In IntelliJ IDEA, select File | Open... and point to the ./build.gradle file.
3. Open the Plugins Repository (File Menu | Settings | Plugins) and make sure you have installed the Lombok plugin. (For more Information click here)
4. Ensure that annotation processing is enabled (File Menu | Settings | Build, Execution, Deployment | Annotation Processors): Enable annotation processing checkbox should be selected.
5. Tests are in src/test/java/testcase
6. Page Class for UI is located in src/main/java/pagetest under the specific app module
7. Data is provided via ini file located in src/main/resources

8. Check out the relevant code: This project consists of 3 modules:
    E2E test cases (E2E User Based Scenario cover Uno App and Payment Gateway UI)
    Domo (BI Reporting implementation)
    DataMigration (DataMigration automation)
    GreenScreen Data capturing
    
9. Retrieving of data from Greenscreen and then verifying that in uno app for validation purposes.
    Provide location/member and clubnumber/membernumber in DM.CSV
    Script will execute to collect data from green screen for location/member and provided clubnumber/membernumber into ini 
    file "greenscreen.ini"
    Data collected from green screen will validate on Uno app 
    Technology used to collect data from greenscreen:AutoHotKey
    XML to fetch data from greenscreen -> GreenScreenTest.xml
    XML to validate data collected from greenscreen to Uno app based on functionality-> ex: CashStorageTest.xml
        
10. Run Xmls for Test cases Execution: Xmls located under src/test/resources:
            Major Xmls are:
                            E2E Xmls are in folder : unoapp_xmls
                            DataMigrationTest.xml
                            DBPackage.xml                

                    

## Samples
You can run the tests from the commandline using the Gradle wrapper.

Run all the tests for xmls configured in build.gradle:
1. Using command : gradle test

Note:
1. Configurations are made to run test cases in browser when running on local machine
2. Tests will run in headless browser when running on Jenkins CI tool.

## Supported WebDriver configurations

| buildEnv \ browser  | `chrome` *(default)* | `browser`          |
|---------------------|----------------------|--------------------|
| `local` *(default)* | :heavy_check_mark:   | :heavy_check_mark: |
| `jenkins`           | :heavy_check_mark:   | :x:                |

## Authentication configurations when test cases run on local

|Module             |  buildEnv         |Environment variable |ini file            |Vault              |
|-------------------|-------------------|-------------------- | -------------------|-------------------|
| E2E               |`local` *(default)*|:x:                  | :heavy_check_mark: | :x:               |
| Domo BI Tool UI   |`local` *(default)*|:heavy_check_mark:   | :x:                | :x:               |
| Data Migration UI |`local` *(default)*|:heavy_check_mark:   | :x:                | :x:               |
| E2E               |`jenkins`          |:x:                  | :heavy_check_mark: | :x:               |
| Domo BI Tool UI   |`jenkins`          |:x:                  |:x:                 | :heavy_check_mark:|
| Data Migration UI |`jenkins`          |:x:                  |:x:                 | :heavy_check_mark:|


## Test Report
1. After the test run completed, test results are generated in target\allure-results folder
2. Execute command "gradlew allureReport" to get desired platform
3. Execute command "gradlew allureserve" to get allure report in default browser
4. Jira-Xray API are implemented which generates Test execution with all the relevant test cases which are run.
5. Test execution Id can be obtained from the console output (it will be generated automatically after the test run completed)

For more Information about Allure report click [here](https://docs.qameta.io/allure/)

For more Information about Xray Rest API, used to create Xray Execution Results, click [here](https://confluence.xpand-it.com/display/XRAYCLOUD/REST+API)

##Ares-Live Reporting
Setup:-
Navigate to testastra.com
Project creation 
Go to dashboard URL
Login to dashboard and create a project 
Copy API details i.e. Project Key and User Key of the created project, project_ name and ws _name.

Config changes in Framework:-
ARES dashboard: Name of the Dashboard. 
token/UserToken/UserKey: A token will be generated upon creating project in dashboard for every user, which is user specific
ProjectKey/projectId: A project key will get generated upon creating project in dashboard that can be used while posting test results to dashboard, which is unique to every project.
ws_name: workspace name, you can get it from dashboard homepage (Dashboard URL)
 


