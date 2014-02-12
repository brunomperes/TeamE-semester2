package team.e.components.lecturer;

import team.e.components.users.IAccessFactory;
import team.e.components.users.UserAccess;

public class LecturerAccessFactory implements IAccessFactory {

	@Override
	public UserAccess newInstance() {
		return new LecturerAccess();
	}

	@Override
	public String getUserType() {
		return "lecturer";
	}
	
}
