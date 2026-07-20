@Regression

Feature: User Registration

  Background:

    Given User launches browser and enters the url
    And User Clicks on MyAccount then Register button

  Rule: New users should be able to register

    Scenario: Successful Registration

      When User navigates to Registration page
      And User enters valid First Name
      And User enters valid Last Name
      And User enters valid Email
      And User enters valid Telephone
      And User enters valid Password
      And User confirms valid Password
      And User accepts Privacy Policy
      And User clicks Register

      Then Registration should be successful and Success message should be displayed

  Rule: Registration should fail for duplicate email

    Scenario Outline: Register with existing email

      When User navigates to Registration page
      And User enters First Name "<firstname>"
      And User enters Last Name "<lastname>"
      And User enters Email "<email>"
      And User enters Telephone "<telephone>"
      And User enters Password "<password>"
      And User confirms Password "<password>"
      And User clicks Register

      Then Warning message should be displayed
      But User account should not be created

      Examples:

        | firstname | lastname | email                | telephone    | password      |
        | John      | Smith    | john@test.com        | 2252252252   | Password123   |
        | Test      | User     | testuser@eclipse.com | 2252252252   | testuser@123  |