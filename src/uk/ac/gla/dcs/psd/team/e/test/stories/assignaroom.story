Narrative:
In order to record room bookings
As an administrator
I want to assign a room to a timetable slot

Scenario: administrator assigns a room to a timetable slot
Given a session
When a room is available during the sessions timeslot
And when the session doesn't have an assigned room
Then the available room is assigned to the session
