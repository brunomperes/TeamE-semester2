package team.e.components.sysfunc.repository;

import java.lang.reflect.Method;

public interface IFunctionRepository {
	
	public <T> void registerFunction(Class<T> regClass);

	public <T> Object getFunction(Class<T> reqClass);
	
}
