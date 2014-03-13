package uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.impl;

import java.util.ArrayList;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;


public class FunctionRepository implements IFunctionRepository{
	
	private ArrayList<Object> objects;
	
	public FunctionRepository(){
		objects=new ArrayList<Object>();
	}
	
	public void registerFunction(Object regObj){
		objects.add(regObj);
	}

	public Object getFunction(Class<?> reqClass){
		for (Object regObj : objects) {
			if (reqClass.isInstance(regObj)) {
				return regObj;
			}
		}
		return null;
	}
	
}