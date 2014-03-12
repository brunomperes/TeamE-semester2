package uk.ac.gla.dcs.psd.team.e.components.lecturer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import uk.ac.gla.dcs.psd.team.e.components.administrator.AdminAccessFactory;
import uk.ac.gla.dcs.psd.team.e.components.lecturer.LecturerAccess;
import uk.ac.gla.dcs.psd.team.e.components.users.IAccessFactory;

public class Activator implements BundleActivator{
	
	private ServiceRegistration<IAccessFactory> registration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		registration=context.registerService(IAccessFactory.class, new LecturerAccessFactory(), null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();
	}

}
