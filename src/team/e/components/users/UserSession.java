package team.e.components.users;

public class UserSession {
	
	private String sessionID;
	private UserAccess access;
	
	public UserSession(String sessionID, UserAccess access) {
		this.sessionID = sessionID;
		this.access = access;
	}
	
	public UserAccess getUserAccess() {
		return access;
	}
	
	public String getID() {
		return sessionID;
	}

}
