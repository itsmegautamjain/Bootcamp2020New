Feature: I want to test the Edit Campaign functionality in Salesforce application

Scenario: Edit Campaign

Given User launch following url "https://login.salesforce.com" in "Chrome" browser
And Enter Username as "cypress@testleaf.com"
And Enter Password as "Bootcamp@123"
And Click on Login button
And Click on the toggle menu button from the left corner
And Click on View All
And Click on Sales from App launcher
And Click on the Campaign tab
And Click on the "Bootcamp by Gautam Jain" link
And Click on the Detail tab
And Select end Date as "05/03/2021" and "4"
And Update the Expected Revenue as "1000000"
And Update the Budget Cost in Campaign as "100000"
When User click on Save button
Then verify the updated Values displayed as "1000000" and "100000" and "4"
And Logout the Application