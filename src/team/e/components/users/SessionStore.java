package team.e.components.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import team.e.components.users.auth.AuthResult;
import team.e.components.users.auth.IAuthenticator;

public class SessionStore {
	
	private Map<String, UserSession> userSessions;
	private IAuthenticator authenticator;
	
	public SessionStore(IAuthenticator authenticator) {
		userSessions = new HashMap<String, UserSession>();
		this.authenticator = authenticator; 
	}

	public String login(String username, String password) {
		AuthResult result = authenticator.auth(username, password);
		UserAccess access;
		
		if (result.success && (access = getAccess(result.userType)) != null) {
			UserSession newSession = new UserSession(UUID.randomUUID().toString(), access);  
			userSessions.put(newSession.getID(), newSession);
			return newSession.getID();
		}
		
		return null;
	}
	
	public boolean logoff(String sessionID) {
		return userSessions.remove(sessionID) != null;
	}
	
	/** Get UserAccess object of the given userType */
	private UserAccess getAccess(String userType){
		Collection<UserAccess> accessObjects = getAccessObjects();
		for (UserAccess access : accessObjects) {
			if (access.getUserType() == userType) {
				return access;
			}
		}
		return null;
	}
	
	/** Get all available UserAccess implementations */
	private Collection<UserAccess> getAccessObjects() {
		
		/* TODO: If access services are instances of UserAccess, then there's only one kind of access per
		 * application and not per UserSession. Also, it doesn't look like a service can be a class. */
		
		Collection<ServiceReference<Class<? extends UserAccess>>> refs;
		Collection<Class<? extends UserAccess>> services = new ArrayList<Class<? extends UserAccess>>();
		UserAccess service;
		
		try {
			refs = Activator.context.getServiceReferences(UserAccess.class, null);
		} catch (InvalidSyntaxException e) {
			return new ArrayList<UserAccess>();
		}
		
		for (ServiceReference<UserAccess> ref : refs) {
			service = Activator.context.getService(ref);
			if (service != null) {
				services.add(service);
			}
		}
		
		return services;
	}
	
}
