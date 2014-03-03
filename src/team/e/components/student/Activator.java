package team.e.components.student;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import team.e.components.users.IAccessFactory;

public class Activator implements BundleActivator{
	
	private ServiceRegistration<IAccessFactory<StudentAccess>> registration;
	
	@Override
	@SuppressWarnings("unchecked")

	public void start(BundleContext context) throws Exception {
		 registration=context.registerService((Class<IAccessFactory<StudentAccess>>) Class.forName("team.e.components.users.IAccessFactory"), new StudentAccessFactory(), null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();
	}

}
