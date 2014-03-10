Narrative:
In order to not fail a course
As a student
I want to check I have signed up for all the compulsory sessions

Scenario: student enrols in the PSD3 course
Given a PSD3 course
When a student is enrolling to the PSD3 course
And a session is compulsory
Then the student must enrol in that session
