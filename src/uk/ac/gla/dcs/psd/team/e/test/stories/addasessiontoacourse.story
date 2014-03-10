Narrative:
In order identify a timetable slot
As a lecturer
I want to add a session to a course

Scenario: lecturer adds a lab to the PSD3 course
Given the PSD3 course
When a lab is created
Then an association between the lab and the PSD3 course should be created

Scenario: lecturer adds a lecture to the NS3 course
Given the NS3 course
When a lecture is created
Then an association between the lecture and the NS3 course should be created

Scenario: lecturer adds a tutorial to the AP3 course
Given the AP3 course
When a tutorial is created
Then an association between the tutorial and the AP3 course should be created