Feature: Login functionality

  Scenario: User logs in successfully
    Given I open the login page
    When I enter "student" as username
    And I enter "Password123" as password
    And I click on the login button
    Then I should be redirected to the dashboard
