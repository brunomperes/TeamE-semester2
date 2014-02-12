package team.e.components.student;

import team.e.components.users.IAccessFactory;
import team.e.components.users.UserAccess;

public class StudentAccessFactory implements IAccessFactory {

	@Override
	public UserAccess newInstance() {
		return new StudentAccess();
	}

	@Override
	public String getUserType() {
		return "student";
	}
	
}
