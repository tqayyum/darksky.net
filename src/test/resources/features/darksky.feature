@darksky-regression
Feature: Darksky search Feature

  Background:
    Given I am on darksky website homepage

  @darksky-1
  Scenario: Verify that the current temp versus low and high
    When I clear search text field
    And I enter an 10001 into the search field
    And I click on search magnifying glass
    Then Verify current temperature is between low and high

  @darksky-2
  Scenario: Verify timeline is displayed in correct format
    Then I verify timeline is displayed with two hours incremented