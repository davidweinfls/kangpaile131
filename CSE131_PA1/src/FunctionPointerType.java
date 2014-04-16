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

	//Need more
	public boolean isFunctionPointer()
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

    //fields
	private String m_funcName;
	private Type m_returnType;
	private boolean m_byRef;
	private Vector<VarSTO> m_params;
	
}
