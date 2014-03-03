package uk.ac.gla.dcs.psd.team.e.components.administrator;

import uk.ac.gla.dcs.psd.team.e.components.users.IAccessFactory;

public class AdminAccessFactory implements IAccessFactory<AdminAccess> {

	@Override
	public AdminAccess newInstance() {
		return new AdminAccess();
	}

	@Override
	public String getUserType() {
		return "admin";
	}
	
}
