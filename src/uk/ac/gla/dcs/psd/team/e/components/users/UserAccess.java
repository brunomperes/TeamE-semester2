package uk.ac.gla.dcs.psd.team.e.components.users;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;

public abstract class UserAccess {
	private String username;
	private IFunctionRepository funcRepo;

	public IFunctionRepository getFuncRepo() {
		return funcRepo;
	}

	public void setFuncRepo(IFunctionRepository funcRepo) {
		this.funcRepo = funcRepo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
