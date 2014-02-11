package team.e.components.users;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import team.e.components.users.auth.IAuthenticator;

public class Activator implements BundleActivator {
	
	public static BundleContext context;
	
	private static SessionStore sessionStore;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		Activator.context = context;
		
		IAuthenticator auth = context.getService(context.getServiceReference(IAuthenticator.class)); 
		sessionStore = new SessionStore(auth);
	}	

	@Override
	public void stop(BundleContext context)
		throws Exception {
		
	}
}