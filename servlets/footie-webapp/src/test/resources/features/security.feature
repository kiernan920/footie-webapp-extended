Feature: TRS rest interface POST test

  Scenario: When I check the correct username and password the response will be the type of user
    When query the username and password
    Then the response is json output '{"response":"administrator"}'

