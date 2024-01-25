Feature:Login with Valid Credentials using DataTable

@sanity
Scenario: Login with Valid Credentails Using DataTable
Given User Open The browser
And User Enter URL as "http://primusbank.qedgetech.com/"
When User Enter Details
|Email|Admin|
|Password|Admin|
Then Click on Enter Button