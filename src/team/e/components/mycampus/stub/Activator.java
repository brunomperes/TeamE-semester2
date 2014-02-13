package team.e.components.mycampus.stub;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import team.e.components.db.IDatabase;
import team.e.components.mycampus.IMyCampus;
import team.e.components.sysfunc.repository.IFunctionRepository;
import team.e.components.sysfunc.repository.impl.FunctionRepository;
import team.e.components.users.auth.IAuthenticator;

public class Activator implements BundleActivator{
	
	private MyCampusStub myCStub;
	
	private ServiceRegistration<IMyCampus> myCampusStubRegistion;
	private ServiceRegistration<IAuthenticator> authResultRegistration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		IFunctionRepository funcRepo=context.getService(context.getServiceReference(FunctionRepository.class));
		IDatabase db=context.getService(context.getServiceReference(IDatabase.class));
		myCStub=new MyCampusStub(db,funcRepo);
		myCampusStubRegistion=context.registerService(IMyCampus.class, myCStub, null);
		
		authResultRegistration=context.registerService(IAuthenticator.class, myCStub, null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		myCampusStubRegistion.unregister();
		
		authResultRegistration.unregister();
	}

}