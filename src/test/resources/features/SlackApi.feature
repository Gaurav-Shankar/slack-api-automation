@ValidateSlackAPI
Feature: Validate Slack API for Channel CRUD

  @Channel
  Scenario: Channel CRUD Operation
    Given I create a new Channel via "channels.create" method
    And I Join the newly created Channel via "channels.join" method
    Then Rename the Channel via "channels.rename" method
    And I List all Channels via "channels.list" method
    Then I Validate if the Channel name has changed successfully
    And I Archive the Channel via "channels.archive" method
    Then I Validate if the Channel is archived successfully via "channels.list" method
