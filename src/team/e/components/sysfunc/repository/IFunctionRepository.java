package team.e.components.sysfunc.repository;

public interface IFunctionRepository {
	
	public void registerFunction(Class<?> regClass);

	public Object getFunction(Class<?> reqClass);
	
}
