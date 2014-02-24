package team.e.components.lecturer;

import team.e.components.users.IAccessFactory;

public class LecturerAccessFactory implements IAccessFactory<LecturerAccess> {

	@Override
	public LecturerAccess newInstance() {
		return new LecturerAccess();
	}

	@Override
	public String getUserType() {
		return "lecturer";
	}
	
}
