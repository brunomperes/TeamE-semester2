Narrative:
In order to reduce the number of sessions
As a lecturer
I want to specify that a session is a one off, recurs weekly or fortnightly

Scenario: lecturer specifies the frequency of a session
Given a session
When a lecturer creates the session
Then the frequency of the session is stored
