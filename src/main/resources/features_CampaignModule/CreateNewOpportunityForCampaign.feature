Feature: I want to test the Create New Opportunity for Campaign functionality in Salesforce application

Scenario: Create New Opportunity for Campaign

Given User launch following url "https://login.salesforce.com" in "Chrome" browser
And Enter Username as "cypress@testleaf.com"
And Enter Password as "Bootcamp@123"
And Click on Login button
And Click on the toggle menu button from the left corner
And Click on View All
And Click on Sales from App launcher
And Click on the Campaign tab
And Click on the "Bootcamp by Gautam Jain" link
And Click on the New Button
And Enter Opportunity name as "Salesforce Automation by Gautam Jain"
And Select Stage as "Need Analysis"
And Select close date as "05/03/2021" and "1"
When User click on Save button
Then Verify new Opportunity in Campaign tab displayed as "Salesforce Automation by Gautam Jain"
And Click on Opportunities Tab
And Verify the newly created Opportunity in Opportunity tab displayed as "Salesforce Automation by Gautam Jain"
And Logout the Application