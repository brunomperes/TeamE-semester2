package team.e.components.mycampus.stub;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator{
	
	private MyCampusStub myCStub;
	
	private ServiceRegistration<MyCampusStub> myCampusStubRegistion;
	
	@Override
	public void start(BundleContext context) throws Exception {
		myCStub=new MyCampusStub();
		myCampusStubRegistion=context.registerService(MyCampusStub.class, myCStub, null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		myCampusStubRegistion.unregister();
		
	}

}
