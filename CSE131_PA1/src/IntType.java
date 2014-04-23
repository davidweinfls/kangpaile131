class IntType extends NumericType {

	public IntType(String strName, int size) {
		super(strName, size);
		// TODO Auto-generated constructor stub
	}

	// need more
	public boolean isInt() {
		return true;
	}

	public boolean isAssignable(Type type)
	{
		if(type instanceof IntType || type instanceof FloatType)
		{
			return true;
        }
		else
		{
			return false;
        }
	}
	
	public boolean isEquivalentTo(Type t)
    {
		if 	(t instanceof IntType)
			return true;
        return false;
    }

	public boolean isIntType() {
		return true;
	}
	
	public Type copy()
	{
		return new IntType(this.m_typeName, this.m_size);
	}
}
