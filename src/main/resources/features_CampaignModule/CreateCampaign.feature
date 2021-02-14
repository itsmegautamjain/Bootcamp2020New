Feature: I want to test the Create Campaign functionality in Salesforce application

Scenario: Create Campaign

Given User launch following url "https://login.salesforce.com" in "Chrome" browser
And Enter Username as "cypress@testleaf.com"
And Enter Password as "Bootcamp@123"
And Click on Login button
And Click on the toggle menu button from the left corner
And Click on View All
And Click on Sales from App launcher
And Click on the Campaign tab
And Click on the New Button
And Enter Campaign name as "Bootcamp by Gautam Jain"
And Select start Date as "05/03/2021" and "1"
And Select end Date as "05/03/2021" and "2"
When User click on Save button  
Then Verify Newly created campaign is correct and displayed as "Bootcamp by Gautam Jain"
And Logout the Application