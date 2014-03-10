Narrative:
In order to not fail a course
As a student
I want to check I have signed up for all the compulsory sessions

Scenario: student checks they have signed up for all compulsory sessions
Given a course
When a student is enrolling to the course
And a session is compulsory
Then the student must been enrolled in that session
