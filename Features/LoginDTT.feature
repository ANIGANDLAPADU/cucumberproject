
Feature: Login Data Driven
@sanity
  Scenario Outline: Login Data Driven
    Given User Launch browser
    And opens URL "http://primusbank.qedgetech.com/"
    When User enters Email as "<email>" and Password as "<password>"
    Then Click on Login button
   

    Examples: 
      | email                     | password |
      | Admin                     | Admin    |
      | Admin                     | Admin     |
