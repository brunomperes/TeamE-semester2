Narrative:
In order identify a timetable slot
As a lecturer
I want to add a session to a course

Scenario: lecturer adds a session to a course
Given a session
When a session is created
Then an association between the session and course should be created
