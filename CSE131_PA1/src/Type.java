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
		return m_typeName;
	}

	private void
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


	//----------------------------------------------------------------
	//	It will be helpful to ask a Type what specific Type it is.
	//	The Java operator instanceof will do this, but you may
	//	also want to implement methods like isNumeric(), isInt(),
	//	etc. Below is an example of isInt(). Feel free to
	//	change this around.
	//----------------------------------------------------------------
	public boolean	isInt ()			{ return false; }
	
	public boolean  isBool()			{ return false;	}
	public boolean	isFloat ()			{ return false; }
	public boolean	isNumeric ()		{ return false; }
	public boolean	isBasic ()			{ return false; }
	public boolean	isVoid ()			{ return false; }
	public boolean	isComposite ()		{ return false; }
	public boolean	isArray ()			{ return false; }
	public boolean	isStruct ()     	{ return false; }
	public boolean	isPtrGrp ()     	{ return false; }
	public boolean	isPointer ()    	{ return false; }
	public boolean	isNullPtr ()		{ return false; }
	public boolean	isFuncPtr ()		{ return false; }
	public boolean  isChar ()       	{ return false; }
	
	public boolean isAssignable(Type t) { return false; }
	public boolean isEquivalent(Type t) { return false; }
	
	public boolean	isBasicType ()	{ return false; }
	public boolean  isNumericType()	{ return false; }
	public boolean	isCompositeType ()	{ return false; }
	public boolean	isVoidType ()	{ return false; }

	//----------------------------------------------------------------
	//	Name of the Type (e.g., int, bool, or some typedef
	//----------------------------------------------------------------
	private String  	m_typeName;
	private int		m_size;
}
