package team.e.components.sysfunc.repository.impl;

import java.util.ArrayList;

import team.e.components.sysfunc.repository.IFunctionRepository;


public class FunctionRepository implements IFunctionRepository{
	
	private ArrayList<Object> objects;
	
	public FunctionRepository(){
		objects=new ArrayList<Object>();
	}
	
	public void registerFunction(Object regClass){
		objects.add(regClass);
	}

	public Object getFunction(Class<?> reqClass){
		return objects.get(objects.indexOf(reqClass));
	}
	
}