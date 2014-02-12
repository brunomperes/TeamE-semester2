package team.e.components.sysfunc.mycampus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import team.e.components.mycampus.IMyCampus;
import team.e.components.sysfunc.repository.IFunctionRepository;
import team.e.components.sysfunc.repository.impl.FunctionRepository;

public class Activator implements BundleActivator{

	private MyCampusFunctions myCFunc;
	
	private ServiceRegistration<IMyCampus> myCampusFunctionsRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		IMyCampus myCInterface=context.getService(context.getServiceReference(IMyCampus.class));
		myCFunc=new MyCampusFunctions(myCInterface);
		IFunctionRepository funcRepo=context.getService(context.getServiceReference(IFunctionRepository.class));
		funcRepo.registerFunction(myCFunc);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}

}
