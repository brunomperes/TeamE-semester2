Narrative:
In order for students to complete a course
As a administrator
I want to ensure there are no timetable clashes between courses

Scenario: administrator checks for timetable clashes when creating the NS3 course
Given a timetable
And a NS3 course
When the NS3 course is created
Then the timeslots for NS3 should be checked to ensure they don't clash with another timeslot

Scenario: administrator checks for timetable clashes when creating the PSD3 course
Given a timetable
And a PSD3 course
When the PSD3 course is created
Then the timeslots for PSD3 should be checked to ensure they don't clash with another timeslot