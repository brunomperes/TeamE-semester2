package team.e.components.sysfunc.timetable;

import java.util.Collection;

public class Session {

	private String id;
	private String name;
	private Collection<String> timetableSlotIDs;
	private int frequency;
	private int durationMinutes;
	private int durationWeeks;
	private boolean compulsory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<String> getTimetableSlotIDs() {
		return timetableSlotIDs;
	}

	public void setTimetableSlotIDs(Collection<String> timetableSlotIDs) {
		this.timetableSlotIDs = timetableSlotIDs;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public int getDurationWeeks() {
		return durationWeeks;
	}

	public void setDurationWeeks(int durationWeeks) {
		this.durationWeeks = durationWeeks;
	}

	public boolean isCompulsory() {
		return compulsory;
	}

	public void setCompulsory(boolean compulsory) {
		this.compulsory = compulsory;
	}

}
