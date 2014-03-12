package uk.ac.gla.dcs.psd.team.e.components.db.fake;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;

public class Activator implements BundleActivator {

	private ServiceRegistration<IDatabase> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		registration = context
				.registerService(
						IDatabase.class,
						new Database(), null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();
	}

}