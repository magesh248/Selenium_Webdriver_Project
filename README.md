# Selenium_Webdriver_Project

This is a test framework which uses Selenium for browser automation uses other dependencies:
-----------------------------------------------------------------------------------------------------------------------------------
TestNG - used for testing framework
Selenium API
Apache Commons
Jackson
Extent Reports
Maven
Maven profiling integration
Java
Setup for framework - uses Page Object Model for Design Pattern
-------------------------------------------------------------------------------------------------------------------------------------
TestConfiguration package - BaseTest - used for setting up before and after functions.

* SetupDriver - this helps to different drivers like chrome.firefox and edge which will trigger using browserType key.
* PropertyReaderHelper - helps to read the global config file
* ExtentReportHelper - used to create the extent report object (single object is created and used for multiple test scenarios) 

--------------------------------------------------------------------------------------------------------------------------------------

Each page for the application has its class (pages package) used PageObjectModel
*Login page
*DashBoard page

--------------------------------------------------------------------------------------------------------------------------------------

Utils package we have listener class to retry the failed testcases
i) ListenerHelper - uses ITestListener - which helps in creating the report as per different functions

--------------------------------------------------------------------------------------------------------------------------------------

Log4j-log4j is a tool to help the programmer output log statements to a variety of output targets.
The Log4j class is used for print and store the output in specific path

TestNG - priority, description.

---------------------------------------------------------------------------------------------------------------------------------------
