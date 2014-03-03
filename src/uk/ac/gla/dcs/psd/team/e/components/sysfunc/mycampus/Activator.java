package uk.ac.gla.dcs.psd.team.e.components.sysfunc.mycampus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import uk.ac.gla.dcs.psd.team.e.components.mycampus.IMyCampus;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;

public class Activator implements BundleActivator{

	private MyCampusFunctions myCFunc;

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
