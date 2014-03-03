package team.e.components.student;

import team.e.components.users.IAccessFactory;

public class StudentAccessFactory implements IAccessFactory <StudentAccess> {

	@Override
	public StudentAccess newInstance() {
		return new StudentAccess();
	}

	@Override
	public String getUserType() {
		return "student";
	}
	
}
