import java.util.Vector;

//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class FuncSTO extends STO
{
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	FuncSTO (String strName)
	{
		super (strName, new FunctionPointerType(strName, 4));
		setReturnType (null);
        m_byRef = false;
        m_params = new Vector<VarSTO>();
        m_overloadFuncList = new Vector<FunctionPointerType> ();
        ((FunctionPointerType) getType()).setFuncName(strName);
                // You may want to change the isModifiable and isAddressable                      
                // fields as necessary
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public boolean
	isFunc () 
	{ 
		return true;
                // You may want to change the isModifiable and isAddressable                      
                // fields as necessary
	}


	//----------------------------------------------------------------
	// This is the return type of the function. This is different from 
	// the function's type (for function pointers).
	//----------------------------------------------------------------
	public void
	setReturnType (Type typ)
	{
		m_returnType = typ;
		((FunctionPointerType)getType()).setReturnType(typ);
	}

	public Type
	getReturnType ()
	{
		return m_returnType;
	}
	
	public boolean getRef()
	{
		return m_byRef;
	}
	
	public void setRef(boolean ref)
	{
		m_byRef = ref;
		((FunctionPointerType) getType()).setByRef (ref);
	}
	
	public Vector<VarSTO> getParams()
	{
		return m_params;
	}
	
	public void setParams(Vector<VarSTO> param)
	{
		m_params.addAll(param);
		((FunctionPointerType) getType()).setParams (param);
	}
	
	public void addOverloadFunction(FunctionPointerType functionType)
	{
		if (m_overloadFuncList.size() == 0) 
		{
			m_overloadFuncList.addElement ((FunctionPointerType) getType());
        }
		m_overloadFuncList.addElement (functionType);
	}
	
	public Vector<FunctionPointerType> getOverloadFunctionList()
	{
		return m_overloadFuncList;
	}
	
	public boolean isOverloaded()
	{
		if(m_overloadFuncList.size() > 1)
			return true;
		else
			return false;
	}
	
	public FunctionPointerType checkOverload(Vector v)
	{
		for(int i = 0; i < m_overloadFuncList.size(); i++)
		{
			//get overloaded functinonpointer
			FunctionPointerType funcType = m_overloadFuncList.get(i);
			Vector<VarSTO> paramList =  funcType.getParams();
			
			if(v.size() == paramList.size())
			{
				for(int j = 0; j < paramList.size(); j++)
				{
					STO p = (STO)v.get(i);
					VarSTO param = paramList.get(i);
					Type pType = p.getType();
					Type paramType = param.getType();
					
					//if parameter is by reference, need to check two cases
					if(param.isRef())
					{
						if(!(pType.isEquivalent(paramType)))
						{
							return null;
						}
						else if(!p.isModLValue() && !pType.isArrayType())
						{
							return null;
						}
					}
					else
					{
						if(!(pType.isEquivalent(paramType)))
						{
							return null;
						}
					}
				}
				return funcType;
			}
			
		}
		return null;
	}
	
	public boolean setOverloadedParams(Vector<VarSTO> param)
	{
		boolean check = false;

		for (int i = 0; i < m_overloadFuncList.size() - 1; i++)
		{
			FunctionPointerType funcPtr = m_overloadFuncList.get(i);
			Vector<VarSTO> p = funcPtr.getParams();
			check = false;

			if (p.size() == param.size())
			{
				for (int j = 0; j < p.size(); j++)
				{
					VarSTO a = p.get(j);
					VarSTO b = param.get(j);
					Type aType = a.getType();
					Type bType = b.getType();
					//check pass only if param and p have different type
					if (!(aType.isEquivalent(bType)))
					{
						check = true;
					}
				}
			}
			//size is not equal. is not duplicate
			else
			{
				check = true;
			}

			//remove last overload funcptr
			if(!check)
			{
				m_overloadFuncList
						.removeElementAt(m_overloadFuncList.size() - 1);
				return check;
			}
		}

		StringBuilder id = new StringBuilder();
		id.append("._" + getName());
		for (int i = 0; i < param.size(); i++)
		{
			VarSTO var = param.elementAt(i);
			id.append(var.getType().getName() + "_" + (i + 1));
		}

		//update new param list
		(m_overloadFuncList.get(m_overloadFuncList.size() - 1))
				.setParams(param);
		(m_overloadFuncList.get(m_overloadFuncList.size() - 1))
				.setFuncName(id.toString());
		return check;
	}

//----------------------------------------------------------------
//	Instance variables.
//----------------------------------------------------------------
	private Type 		m_returnType;
	private boolean	m_byRef;
	private Vector<VarSTO> m_params;
	private Vector<FunctionPointerType> m_overloadFuncList;
}
