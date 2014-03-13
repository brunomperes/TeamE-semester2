package uk.ac.gla.dcs.psd.team.e.components.users;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;

public class Activator implements BundleActivator {
	
	public static BundleContext context;
	
	private static SessionStore sessionStore;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		Activator.context = context;
		IFunctionRepository funcRepo = context.getService(context.getServiceReference(IFunctionRepository.class));
		sessionStore = new SessionStore(context, funcRepo);
	}	

	@Override
	public void stop(BundleContext context)
		throws Exception {
		
	}
}