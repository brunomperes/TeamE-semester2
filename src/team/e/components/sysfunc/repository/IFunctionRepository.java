package team.e.components.sysfunc.repository;

public interface IFunctionRepository {
	
	public void registerFunction(Object regClass);

	public Object getFunction(Class<?> reqClass);
	
}
