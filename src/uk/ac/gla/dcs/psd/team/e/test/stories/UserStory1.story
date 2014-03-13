Narrative:
In order to identify teaching sessions
As a lecturer
I want import a session from mycampus

Scenario: lecturer imports a lab session from mycampus
Given access to mycampus
When there are courses present on mycampus
And they are not already on the database
Then the courses should be copied to the database and PSD3 course would be present.

Scenario: lecturer imports a lab session from mycampus
Given access to mycampus
When there are courses present on mycampus
And they are not already on the database
Then the courses should be copied to the database and NS3 course would be present.