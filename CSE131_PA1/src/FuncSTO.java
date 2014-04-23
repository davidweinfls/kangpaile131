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
        m_funcType = new Vector<FunctionPointerType> ();
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
	
	public void setFuncType(FunctionPointerType functionType)
	{
		if (m_funcType.size() == 0) 
		{
            m_funcType.addElement ((FunctionPointerType) getType());
        }
        m_funcType.addElement (functionType);
	}
	
	public Vector<FunctionPointerType> getFuncType()
	{
		return m_funcType;
	}

//----------------------------------------------------------------
//	Instance variables.
//----------------------------------------------------------------
	private Type 		m_returnType;
	private boolean	m_byRef;
	private Vector<VarSTO> m_params;
	private Vector<FunctionPointerType> m_funcType;
}
