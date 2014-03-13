package uk.ac.gla.dcs.psd.team.e.components.users;

public interface IAccessFactory <TypeAccess extends UserAccess> {
	public TypeAccess newInstance();
	public String getUserType();
}
