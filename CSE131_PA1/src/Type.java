//---------------------------------------------------------------------
// This is the top of the Type hierarchy. You most likely will need to
// create sub-classes (since this one is abstract) that handle specific
// types, such as IntType, FloatType, ArrayType, etc.
//---------------------------------------------------------------------

abstract class Type
{
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	Type (String strName, int size)
	{
		setName(strName);
		setSize(size);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public String
	getName ()
	{
		if(isAlias()) return m_alias;
		return m_typeName;
	}

	public void
	setName (String str)
	{
		m_typeName = str;
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public int
	getSize ()
	{
		return m_size;
	}

	private void
	setSize (int size)
	{
		m_size = size;
	}

	public boolean isAlias()
	{
		return m_isAlias;
	}
	
	public void setAlias(String name)
	{
		m_alias = name;
		m_isAlias = true;
	}
	
	public Type copy()
	{
		return null;
	}

	//----------------------------------------------------------------
	//	It will be helpful to ask a Type what specific Type it is.
	//	The Java operator instanceof will do this, but you may
	//	also want to implement methods like isNumeric(), isInt(),
	//	etc. Below is an example of isInt(). Feel free to
	//	change this around.
	//----------------------------------------------------------------
	
	public boolean	isArrayType ()			{ return false; }
	public boolean	isStructType ()     	{ return false; }
	public boolean	isPointerGroupType ()     	{ return false; }
	public boolean	isPointerType ()    	{ return false; }
	public boolean	isNullPointerType ()		{ return false; }
	public boolean	isFunctionPointerType ()		{ return false; }
	public boolean  isChar ()       	{ return false; }
	
	public boolean isAssignable(Type t) { return false; }
	public boolean isEquivalent(Type t) { return false; }
	
	public boolean	isBasicType ()	{ return false; }
	public boolean  isNumericType()	{ return false; }
	public boolean	isCompositeType ()	{ return false; }
	public boolean	isVoidType ()	{ return false; }
	public boolean	isFloatType()	{ return false; }
	public boolean 	isIntType()		{ return false; }
	public boolean 	isBoolType()	{ return false;}

	//----------------------------------------------------------------
	//	Name of the Type (e.g., int, bool, or some typedef
	//----------------------------------------------------------------
	protected String  	m_typeName;
	protected int		m_size;
	protected String	m_alias;
	protected boolean 	m_isAlias;
}

