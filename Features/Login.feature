Feature: Login with Valid Credentials

  @sanity
  Scenario: Successful Login with Valid Credentials
    Given User Launch browser
    And opens URL "http://primusbank.qedgetech.com/"
    When User enters Email as "Admin" and Password as "Admin"
    Then Click on Login button
    
