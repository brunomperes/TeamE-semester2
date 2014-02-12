package team.e.components.administrator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import team.e.components.users.IAccessFactory;

public class Activator implements BundleActivator{
	
	private ServiceRegistration<IAccessFactory> registration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		registration=context.registerService(IAccessFactory.class, new AdminAccessFactory(), null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();
	}

}
