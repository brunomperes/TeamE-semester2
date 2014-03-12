Narrative:
In order for students to complete a course
As a administrator
I want to ensure there are no timetable clashes between the courses compulsory sessions

Scenario: administrator checks for timetable clashes of the compulsory sessions when creating the NS3 course
Given a timetable
And a NS3 course
When the NS3 course added to the timetable
Then the time slots for NS3 compulsory sessions should be checked to ensure they don't clash with another compulsory session

Scenario: administrator checks for timetable clashes when creating the PSD3 course
Given a timetable
And a PSD3 course
When the PSD3 course is added to the timetable
Then the time slots for PSD3 compulsory sessions should be checked to ensure they don't clash with another compulsory session