# Roof Stacks Case Study

This is a project for testing RESTful APIs using RestAssured framework.

## Libraries
- Java version 1.8
- RestAssured
- TestNG
- Jackson Mapper
- Extent Reports

## Project Structure
The project structure is organized as follows:

- [Tests](src/test/java/tests): Contains the test classes.
- [Reports](src/test/report): Contains generated test reports.
- [POM](pom.xml): Maven project configuration file.
- [pojos](src/main/java/pojos): The package where the JSON models are kept.
- [TestBase](src/main/java/testBase/TestBase.java): Where variables and reporting methods reside.

## Description
In this project, positive and negative scenarios were written for listing users, 
getting user by ID, creating user, updating user information, and deleting user.

All tests can also be run with the testNG.xml file.

NOTE 1 :
Since this is a Mock API, Endpoints give fixed responses,
so some requests do not receive the expected response.
here the tests were written assuming that the expected answers obtained.

NOTE 2 : [These](src/test/report/) reports can be opened in a browser to view them visually
if downloaded. 
This way graph of tests, failed tests can be displayed.

