package team.e.components.users.auth;

public interface IAuthenticator {
	
	AuthResult auth(String username, String password);

}
