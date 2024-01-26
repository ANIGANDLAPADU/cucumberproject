Feature: Title of your feature
  @sanity
  Scenario Outline: Title of your scenario outline
    Given Open the browser and Enter URL as "http://primusbank.qedgetech.com/"
    When I check for the "<Excel_Row>" in step

    Examples: 
      |Excel_Row|
      |1|
      |2|
      |3|
