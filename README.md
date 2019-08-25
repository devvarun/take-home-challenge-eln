# take-home-challenge-eln
Take home challenge - Labforward
A simple Java REST endpoint which returns the frequency of a given word and any similar words in notebook entry text.

## Prerequisites

Followning softwares should be installed to download, build & run this project locally 
- GIT (Click https://git-scm.com/downloads to download/install git on your machine) 
- Java 8 or higher (Latest version on Java can be found at https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- Maven (https://maven.apache.org/download.cgi)

## Getting Started

Download/clone the source code using git
git clone https://github.com/devvarun/take-home-challenge-eln.git 

*Once done source code can be found under src/main/java/ directory

- Build project 
Open bash/cmd at the root of the project *take-home-challenge-eln 
Execute **mvn clean install** to download project dependencies and create exeutable jar file  

- Run project
Navigate to the target directory in command line/bash
Excute **java -jar take-home-challenge-eln-0.0.1-SNAPSHOT.jar**

*By default this boot application will run on 8080 port, to change the port edit server.port property of application.properties file

## UI to test the endpoint

Once the application is running we can use interactive api-explorer.html to test the endpoint for diffrent set of inputs

http://localhost:8080/api-explorer.html


## Authors

* **Dev** 

