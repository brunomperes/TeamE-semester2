Narrative:
In order for students to complete a course
As a administrator
I want to ensure there are no timetable clashes between the courses compulsory sessions

Scenario: administrator checks for timetable clashes of the compulsory sessions for the NS3 course
Given a timetable
And a NS3 course
When the administrator checks for timetable clashes for that course
Then the time slots for NS3 compulsory sessions should be checked to ensure they don't clash with another compulsory session

Scenario: administrator checks for timetable clashes for the compulsory sessions for the PSD3 course
Given a timetable
And a PSD3 course
When the administrator checks for timetable clashes for that course
Then the time slots for PSD3 compulsory sessions should be checked to ensure they don't clash with another compulsory session