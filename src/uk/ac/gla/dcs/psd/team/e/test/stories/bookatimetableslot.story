Narrative:
In order take a course
As a student
I want to book a timetable slot for each session

Scenario: student books a timetable slot for a the NS3 compulsory sessions
Given a NS3 course
And a student
When enrolling in the NS3 course
Then for each compulsory session in the NS3 course an association between the student and the chosen timetable slot for that session must be created

Scenario: student books a timetable slot for a the PSD3 compulsory sessions
Given a PSD3 course
And a student
When enrolling in the PSD3 course
Then for each compulsory session in the PSD3 course an association between the student and the chosen timetable slot for that session must be created
