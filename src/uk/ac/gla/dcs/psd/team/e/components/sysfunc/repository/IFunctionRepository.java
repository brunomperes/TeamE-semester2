package uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository;

public interface IFunctionRepository {
	
	public void registerFunction(Object regClass);

	public Object getFunction(Class<?> reqClass);
	
}
