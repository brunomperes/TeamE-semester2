package uk.ac.gla.dcs.psd.team.e.components.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.AuthResult;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.IAuthenticator;

public class SessionStore {
	
	private Map<String, UserSession> userSessions;
	private BundleContext context;
	private IFunctionRepository funcRepo;
	
	public SessionStore(BundleContext context, IFunctionRepository funcRepo) {
		userSessions = new HashMap<String, UserSession>();
		this.context = context;
		this.funcRepo = funcRepo;
	}

	public String login(String username, String password) {
		AuthResult result = getAuthenticator().auth(username, password);
		UserAccess access;
		
		if (result.success && (access = getAccess(result.userType)) != null) {
			access.setUsername(username);
			access.setFuncRepo(funcRepo);
			UserSession newSession = new UserSession(UUID.randomUUID().toString(), access);  
			userSessions.put(newSession.getID(), newSession);
			return newSession.getID();
		}
		
		return null;
	}
	
	public boolean logoff(String sessionID) {
		return userSessions.remove(sessionID) != null;
	}
	
	private IAuthenticator getAuthenticator() {
		ServiceReference<IAuthenticator> s = context.getServiceReference(IAuthenticator.class);
		IAuthenticator auth = context.getService(s);
		return auth;
	}
	
	/** Get UserAccess object of the given userType */
	private UserAccess getAccess(String userType){
		Collection<IAccessFactory> accessServices = getAccessServices();
		for (IAccessFactory factory : accessServices) {
			if (factory.getUserType().toLowerCase().equals(userType.toLowerCase())) {
				return factory.newInstance();
			}
		}
		return null;
	}
	
	/** Get all available IAccessFactory implementations */
	private Collection<IAccessFactory> getAccessServices() {
		
		Collection<ServiceReference<IAccessFactory>> refs;
		Collection<IAccessFactory> services = new ArrayList<IAccessFactory>();
		IAccessFactory service;
		
		try {
			refs = Activator.context.getServiceReferences(IAccessFactory.class, null);
		} catch (InvalidSyntaxException e) {
			return new ArrayList<IAccessFactory>();
		}
		
		for (ServiceReference<IAccessFactory> ref : refs) {
			service = Activator.context.getService(ref);
			if (service != null) {
				services.add(service);
			}
		}
		
		return services;
	}
	
}
