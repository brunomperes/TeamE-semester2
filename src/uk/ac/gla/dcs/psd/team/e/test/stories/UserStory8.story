Narrative:
In order to record room bookings
As an administrator
I want to assign a room to a timetable slot

Scenario: administrator assigns BO720 to a timetable slot
Given a timetable slot
And a room
When the timetable slot doesn't have an assigned room
Then the timetable slot should be assigned to BO720

Scenario: administrator changes location to D315 in a timetable slot
Given a timetable slot
And a room
When the timetable slot doesn't has an assigned room
Then the timetable slot should be assigned to D315