package uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.impl.FunctionRepository;

public class Activator implements BundleActivator{
	
	private FunctionRepository  funcRepo;
	
	private ServiceRegistration<IFunctionRepository> funcRepoRegistion;

	@Override
	public void start(BundleContext context) throws Exception {
		funcRepo=new FunctionRepository();
		funcRepoRegistion=context.registerService(IFunctionRepository.class, funcRepo, null);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		funcRepoRegistion.unregister();
	}

}
