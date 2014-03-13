Narrative:
In order to not fail a course
As a student
I want to check I have signed up for all the compulsory sessions

Scenario: student checks the PSD3 course
Given a PSD3 course
When a session in the PSD3 course is compulsory
And the student has not signed up for that session
Then the student must be told which sessions they still need to sign up for

Scenario: student checks the PL3 course
Given a PL3 course
When a session in the PL3 course is compulsory
And the student has not signed up for that session
Then the student must be told which sessions they still need to sign up for
