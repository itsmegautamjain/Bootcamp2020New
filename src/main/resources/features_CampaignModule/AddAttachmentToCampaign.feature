Feature: I want to test the Add Attachment to Campaign functionality in Salesforce application

Scenario: Add Attachment to Campaign

Given User launch following url "https://login.salesforce.com" in "Chrome" browser
And Enter Username as "cypress@testleaf.com"
And Enter Password as "Bootcamp@123"
And Click on Login button
And Click on the toggle menu button from the left corner
And Click on View All
And Click on Sales from App launcher
And Click on the Campaign tab
And Click on the "Bootcamp by Gautam Jain" link
And Click on the Upload Button
When User Select a file from local and upload it
Then Verify whether the file name is displayed as a link
And Logout the Application