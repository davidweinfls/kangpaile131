//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

abstract class STO
{
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	STO (String strName)
	{
		this(strName, null);
	}

	public 
	STO (String strName, Type typ)
	{
		setName(strName);
		setType(typ);
		setIsAddressable(false);
		setIsModifiable(false);
	}
	
	public
    STO (String strName, Type typ, boolean addressable, boolean modifiable)
    {
        setName(strName);
        setType(typ);
        setIsAddressable(addressable);
        setIsModifiable(modifiable);
    }


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public String
	getName ()
	{
		return m_strName;
	}

	private void
	setName (String str)
	{
		m_strName = str;
	}

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public Type
	getType ()
	{
		return	m_type;
	}

	public void
	setType (Type type)
	{
		m_type = type;
	}


	//----------------------------------------------------------------
	// Addressable refers to if the object has an address. Variables
	// and declared constants have an address, whereas results from 
	// expression like (x + y) and literal constants like 77 do not 
	// have an address.
	//----------------------------------------------------------------
	public boolean
	getIsAddressable ()
	{
		return	m_isAddressable;
	}

	protected void
	setIsAddressable (boolean addressable)
	{
		m_isAddressable = addressable;
	}

	//----------------------------------------------------------------
	// You shouldn't need to use these two routines directly
	//----------------------------------------------------------------
	private boolean
	getIsModifiable ()
	{
		return	m_isModifiable;
	}

	protected void
	setIsModifiable (boolean modifiable)
	{
		m_isModifiable = modifiable;
	}


	//----------------------------------------------------------------
	// A modifiable L-value is an object that is both addressable and
	// modifiable. Objects like constants are not modifiable, so they 
	// are not modifiable L-values.
	//----------------------------------------------------------------
	public boolean
	isModLValue ()
	{
		return	getIsModifiable() && getIsAddressable();
	}

	private void
	setIsModLValue (boolean m)
	{
		setIsModifiable(m);
		setIsAddressable(m);
	}
	
	public void setGlobal () 
	{
        m_global = true;
    }

    public boolean getGlobal () 
    {
        return m_global;
    }
    
    public VariableBox<STO, STO> getArray()
    {
    	return m_array;
    }
    
    public void setArray (VariableBox<STO, STO> v) 
    {
        m_array = v;
    }
    
    public String getGlobalOffset()
    {
    	return m_global_offset;
    }
    
    public void setGlobalOffset(String offset)
    {
    	m_global_offset = offset;
    }
    
    public int getOffset()
    {
    	return m_offset;
    }
    
    public void setOffset(int offset)
    {
    	m_offset = offset;
    }
    
    public String getBase()
    {
    	return m_base;
    }
    
    public void setBase(String b)
    {
    	m_base = b;
    }
    
    // set x, %l0
    // add %g0, %l0, %l0
	public String getAddress()
	{
		StringBuilder s = new StringBuilder();

		if (m_global_offset != "")
			s.append("set" + "\t" + getGlobalOffset() + ", %%l0\n");
		else
			s.append("set" + "\t" + getOffset() + ", %%l0\n");

		s.append("\tadd" + "\t" + getBase() + ", %%l0, %%l0\n");
		return s.toString();
	}

	//for project2:
	public boolean getIsArray()
	{
		return isArray;
	}
	
	public void setIsArray()
	{
		isArray = true;
	}
	
	//for project2:
	public boolean getIsStructField()
	{
		return isStructField;
	}
		
	public void setIsStructField()
	{
		isStructField = true;
	}
	
	//for project2:
	public boolean getIsDeref()
	{
		return isDeref;
	}
	
	public void setIsDeref()
	{
		isDeref = true;
	}
	
	//for project2:
	public boolean getIsTypeDef()
	{
		return isTypeDef;
	}
	
	public void setIsTypeDef()
	{
		isTypeDef = true;
	}
	
	//for project2:
	public STO getPointer()
	{
		return m_pointer;
	}
	
	public void setPointer(STO ptr)
	{
		m_pointer = ptr;
	}
	
	//for project2: 
	public STO getStruct()
	{
		return m_struct;
	}
		
	//store struct sto into a struct field. in order to check recursive types
	public void setStruct(STO s)
	{
		m_struct = s;
	}
	
	//for project2:
	public int getFieldOffset()
	{
		return m_field_offset;
	}
	
	public void setFieldOffset(int s)
	{
		m_field_offset = s;
	}
	
	
	//for project2:
	//used in DoDesignator_Dot()
	public STO copy()
	{
		VarSTO retSTO = new VarSTO(this.getName(), this.getType());
		
		retSTO.setBase(getBase());
		retSTO.setGlobalOffset(getGlobalOffset());
		retSTO.setOffset(getOffset());
		
		if(getGlobal())
		{
			retSTO.setGlobal();
		}
		
		if(getIsArray()) 
		{
			retSTO.setIsArray();
			retSTO.setArray(getArray());
		}
		
		if(getIsStructField())
		{
			retSTO.setIsStructField();
			retSTO.setFieldOffset(getFieldOffset());
			//TODO: set struct retSTO.setStruct(getStruct());
		}
		
		if(getIsDeref())
		{
			retSTO.setIsDeref();
		}
		
		if(getPointer() != null)
		{
			retSTO.setPointer(getPointer());
		}
		
		if(this.isVar() && ((VarSTO)this).isRef())
		{
			retSTO.setRef();
		}
		return retSTO;
	}

	//----------------------------------------------------------------
	//	It will be helpful to ask a STO what specific STO it is.
	//	The Java operator instanceof will do this, but these methods 
	//	will allow more flexibility (ErrorSTO is an example of the
	//	flexibility needed).
	//----------------------------------------------------------------
	public boolean	isVar () 	{ return false; }
	public boolean	isConst ()	{ return false; }
	public boolean	isExpr ()	{ return false; }
	public boolean	isFunc () 	{ return false; }
	public boolean	isTypedef () 	{ return false; }
	public boolean	isError () 	{ return false; }


	//----------------------------------------------------------------
	// 
	//----------------------------------------------------------------
	private String  	m_strName;
	private Type		m_type;
	private boolean		m_isAddressable;
	private boolean		m_isModifiable;
	private boolean 	m_global = false;
	private VariableBox<STO, STO>	m_array;
	private String		m_base;
	private int			m_offset;
	private String 		m_global_offset = "";
	
	//for project2: 
	private boolean		isArray = false;
	private boolean 	isStructField = false;
	private boolean 	isDeref = false;
	private boolean		isTypeDef = false;
	private STO			m_pointer;
	private STO			m_struct;
	private int 		m_field_offset;
}
