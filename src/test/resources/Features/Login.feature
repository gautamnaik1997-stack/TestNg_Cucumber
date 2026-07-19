Feature: Login
  @Sanity
  Scenario: Login with successful credentials
    Given User launches browser and enters the url
    When User Clicks on MyAccount then Login button
    And User enters email "testuser@eclipse.com" and password "testuser@123"
    And User clicks Login
    Then The title of the page should be "My Account"
    And User clicks Logout