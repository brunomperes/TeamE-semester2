package team.e.components.sysfunc.timetable;


public class Session implements IIdentifiable {

	private String id;
	private String name;
	private int frequency;
	private int durationMinutes;
	private int durationWeeks;
	private boolean compulsory;
	
	//Needed for MyCampusStub. Won't be needed when we get this info from mycampus directly
	public Session(String id, String name,
			int frequency, int durationMinutes, int durationWeeks,
			boolean compulsory) {
		this.id = id;
		this.name = name;
		this.frequency = frequency;
		this.durationMinutes = durationMinutes;
		this.durationWeeks = durationWeeks;
		this.compulsory = compulsory;
	}

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
