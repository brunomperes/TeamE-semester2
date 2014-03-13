Narrative:
In order to reduce the number of sessions
As a lecturer
I want to specify that a session is a one off, recurs weekly or fortnightly

Scenario: lecturer specifies the frequency of a lab to be weekly
Given a lab session
When a lecturer creates the session
Then the frequency of the session is stored as weekly

Scenario: lecturer specifies the frequency of a tutorial to be fortnightly
Given a tutorial session
When a lecturer creates the session
Then the frequency of the session is stored as fortnightly

Scenario: lecturer specifies the frequency of a seminar to be a one off
Given a seminar session
When a lecturer creates the session
Then the frequency of the session is stored as a one off