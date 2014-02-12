package team.e.components.users;

public interface IAccessFactory {
	public UserAccess newInstance();
	public String getUserType();
}
