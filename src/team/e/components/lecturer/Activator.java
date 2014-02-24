package team.e.components.lecturer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import team.e.components.users.IAccessFactory;
import team.e.components.lecturer.LecturerAccess;

public class Activator implements BundleActivator{
	
	private ServiceRegistration<IAccessFactory<LecturerAccess>> registration;
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(BundleContext context) throws Exception {
		registration=context.registerService( (Class<IAccessFactory<LecturerAccess>>) Class.forName("team.e.components.users.IAccessFactory<team.e.components.lecturer.LecturerAccess>"), new LecturerAccessFactory(), null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();
	}

}
