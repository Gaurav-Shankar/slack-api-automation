
# API Automation with Rest Assured

This is a small API test framework using RestAssured and Cucumber to Automate the Slack API's.

[![Build Status](https://travis-ci.org/Gaurav-Shankar/slack-api-automation.svg?branch=master)](https://travis-ci.org/Gaurav-Shankar/slack-api-automation)

## Framework

This repo has a small BDD framework using Cucumber and RestAssured to automate the Slack API's.
1.  Language: Java 8
2.  Testing framework: RestAssured
3.  BDD framework: Cucumber jvm
4.  Build tool: maven
5.  Reporting: Cucumber-reporting

	**package: src/main/java**
	This package contains the ApiClient,LOGGER,Payload generators and other utilities.
		
	**package: src/test/java**
	This package contains the TestRunner Class and the StepDefinition Class.
	
	**package: src/test/resources**
	This folder contains the cucumber feature file, and other needed property files.

## Running the Project
Pre-requisites : Java and maven should be configured in the system. If not, configure both before moving to the next step.

1. Clone the build using the below command.
 ```bash
git clone git@github.com:Gaurav-Shankar/slack-api-automation.git
```
 2. Navigate to the project directory.
 ```bash
cd slack-api-automation
```
## Before running the tests please open the project.properties file and add you slack token. The one in the file is revoked.
3. Run the following maven command to execute the tests.
```bash
mvn test
```
To view the cucumber run report, run the following command
```bash
open target/cucumber-reports/overview-features.html
```
Additionally, to view the generated log file, please navigate to log/ directory.
