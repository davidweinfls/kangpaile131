import java.util.Vector;


class FunctionPointerType extends PointerGroupType{

	public FunctionPointerType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
		m_byRef = false;
		m_params = new Vector<VarSTO>();
		m_returnType = null;
	}
	
	public
    FunctionPointerType (String name, Type returnType, Boolean byReference, Vector<VarSTO> params) 
	{
        super (name, 4);
        m_params = new Vector<VarSTO>();
        setReturnType (returnType);
        setByRef (byReference);
        setParams (params);
    }
	
	public String getName()
	{
		if(isAlias()) return m_alias;
		String paramNames = "";
		if (m_params.size() > 0)
		{
			if (!m_params.get(0).isRef())
			{
				paramNames = m_params.get(0).getType().getName() +
						" " + m_params.get(0).getName();
			}
			else
			{
				paramNames = m_params.get(0).getType().getName() +
						" &" + m_params.get(0).getName();
			}
			for (int i = 1; i < m_params.size(); i++)
			{
				if (!m_params.get(i).isRef())
				{
					paramNames = paramNames + ", " + m_params.get(i).getType().getName() +
							" " + m_params.get(i).getName();
				}
				else
				{
					paramNames = paramNames + ", " + m_params.get(i).getType().getName() +
							" &" + m_params.get(i).getName();
				}
			}
		}
		if( !getByRef() )
			return "funcptr : " + m_returnType.getName() + " (" + paramNames + ")";
		else
			return "funcptr : " + m_returnType.getName() + " & (" + paramNames + ")";
		}

	//Need more
	public boolean isFunctionPointerType()
	{
		return true;
	}
	
	public void
    setFuncName (String name) 
	{
        m_funcName = name;
    }

    public String
    getFuncName () 
    {
        return m_funcName;
    }
    
    public void
    setReturnType (Type typ)
    {
        m_returnType = typ;
    }

    public Type
    getReturnType ()
    {
        return m_returnType;
    }
    
    public void
    setByRef (boolean ref) 
    {
        m_byRef = ref;
    }

    public boolean
    getByRef () 
    {
        return m_byRef;
    }
	
    public void
    setParams (Vector<VarSTO> p) 
    {
        m_params.addAll (p);
    }

    public Vector<VarSTO>
    getParams () 
    {
        return m_params;
    }
    
    public Type copy () 
    {
        return new FunctionPointerType (this.m_typeName, this.m_returnType, this.m_byRef, this.m_params);
    }
    
    public boolean isAssignable(Type t)
    {
    	if((t instanceof FunctionPointerType) &&
    		m_returnType.isAssignable(((FunctionPointerType) t).getReturnType()) &&
    		m_byRef == ((FunctionPointerType) t).getByRef() &&
    		compareParam(t))
    	{
            return true;
        }
        return false;
    }
    
	public boolean isEquivalent(Type t)
	{
		if((t instanceof FunctionPointerType) &&
			m_byRef == ((FunctionPointerType) t).getByRef() && 
			m_returnType.isEquivalent(((FunctionPointerType) t).getReturnType())  && 
			compareParam(t)) 
		{
			return true;
		}
		return false;
	}
    
	//compare paramList. used in isEquivalent(), isAssignable()
    public boolean compareParam(Type t)
    {
    	Vector<VarSTO> paramList = ((FunctionPointerType) t).getParams();
    	
    	if(m_params.size() != paramList.size()) return false;
    	
    	for(int i = 0; i < paramList.size(); i++)
    	{
    		VarSTO x = m_params.get(i);
    		VarSTO y = paramList.get(i);
			if(!(x.getType().isEquivalent(y.getType()))
					|| x.isRef() != y.isRef())
			{
				return false;
			}
    	}
    	return true;
    }

    //fields
	private String m_funcName;
	private Type m_returnType;
	private boolean m_byRef;
	private Vector<VarSTO> m_params;
	
}
