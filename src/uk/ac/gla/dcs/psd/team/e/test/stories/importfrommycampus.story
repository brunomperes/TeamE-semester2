Narrative:
In order to identify teaching sessions
As a lecturer
I want import a session from mycampus

Scenario: lecturer imports a lab session from mycampus
Given access to mycampus
When the lab session is present on mycampus
And the lab session is not already on the database
Then the lab session should be copied to the database

Scenario: lecturer imports a tutorial session from mycampus
Given access to mycampus
When the tutorial session is present on mycampus
And the tutorial session is not already on the database
Then the tutorial session should be copied to the database