Narrative:
In order to know when sessions happen
As a lecturer
I want to be able to view the details of every timetable slot in a session

Scenario: lecturer views details of a tutorial timetable slot
Given the tutorial timetable slot
When a lecturer requests information about the tutorial timetable slot
Then the details of the tutorial timetable slot are given to them

Scenario: lecturer views details of a lab timetable slot
Given the lab timetable slot
When a lecturer requests information about the lab timetable slot
Then the details of the tutorial lab slot are given to them
