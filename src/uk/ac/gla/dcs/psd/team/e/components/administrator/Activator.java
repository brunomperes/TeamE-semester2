package uk.ac.gla.dcs.psd.team.e.components.administrator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import uk.ac.gla.dcs.psd.team.e.components.users.IAccessFactory;

public class Activator implements BundleActivator{
	
	private ServiceRegistration<IAccessFactory<AdminAccess>> registration;
	
	@Override
	@SuppressWarnings("unchecked")
	public void start(BundleContext context) throws Exception {
		registration=context.registerService((Class<IAccessFactory<AdminAccess>>) Class.forName("IAccessFactory<AdminAccess>"), new AdminAccessFactory(), null);
	}
	
	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();
	}

}
