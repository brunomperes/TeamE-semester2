Narrative:
In order identify a timetable slot
As a lecturer
I want to add a session to a course

Scenario: lecturer adds a lab session to the PSD3 course
Given the PSD3 course
When a lab session is created
Then an association between the lab session and the PSD3 course should also be created

Scenario: lecturer adds a lecture session to the NS3 course
Given the NS3 course
When a lecture session is created
Then an association between the lecture session and the NS3 course should also be created

Scenario: lecturer adds a tutorial session to the AP3 course
Given the AP3 course
When a tutorial session is created
Then an association between the tutorial session and the AP3 course should also be created