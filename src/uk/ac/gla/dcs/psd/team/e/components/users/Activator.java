package uk.ac.gla.dcs.psd.team.e.components.users;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.IAuthenticator;

public class Activator implements BundleActivator {
	
	public static BundleContext context;
	
	private static SessionStore sessionStore;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		Activator.context = context;
		
		ServiceReference<IAuthenticator> s = context.getServiceReference(IAuthenticator.class);
		IAuthenticator auth = context.getService(s);
		IFunctionRepository funcRepo = context.getService(context.getServiceReference(IFunctionRepository.class));
		sessionStore = new SessionStore(auth, funcRepo);
	}	

	@Override
	public void stop(BundleContext context)
		throws Exception {
		
	}
}