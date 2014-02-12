package team.e.components.administrator;

import team.e.components.users.IAccessFactory;
import team.e.components.users.UserAccess;

public class AdminAccessFactory implements IAccessFactory {

	@Override
	public UserAccess newInstance() {
		return new AdminAccess();
	}

	@Override
	public String getUserType() {
		return "admin";
	}
	
}
