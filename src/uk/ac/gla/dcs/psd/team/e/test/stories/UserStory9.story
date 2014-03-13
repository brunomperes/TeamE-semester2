Narrative:
In order for students to complete a course
As a administrator
I want to ensure there are no timetable clashes between the courses compulsory sessions

Scenario: administrator checks for timetable clashes of the compulsory sessions for the NS3 course
Given a timetable
And a NS3 course
When the administrator checks for timetable clashes for that course
Then if there is a clash between the NS3 compulsory session and another compulsory session then the administrator should be told when there is a clash

Scenario: administrator checks for timetable clashes for the compulsory sessions for the PSD3 course
Given a timetable
And a PSD3 course
When the administrator checks for timetable clashes for that course
Then if there is a clash between the PSD3 compulsory session and another compulsory session then the administrator should be told when there is a clash