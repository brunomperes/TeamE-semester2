package uk.ac.gla.dcs.psd.team.e.components.lecturer;

import uk.ac.gla.dcs.psd.team.e.components.users.IAccessFactory;

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
