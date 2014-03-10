Narrative:
In order to identify teaching sessions
As a lecturer
I want import a session from mycampus

Scenario: lecturer imports a session from mycampus
Given access to mycampus
When a session is present on mycampus
And the session is not already on the database
Then the session should be copied to the database
