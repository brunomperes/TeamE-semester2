Narrative:
In order for students to complete a course
As a administrator
I want to ensure there are no timetable clashes between courses

Scenario: administrator checks for timetable clashes
Given a timetable
When a course is created
Then the timetable should be checked so that there are no timetable clashes
