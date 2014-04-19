class ArrayType extends CompositeType {

	public ArrayType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayType(String name, int size, int arraySize)
	{
		super(name, size);
		setArraySize(arraySize);
		setBaseType(null);
	}
	
	public ArrayType(String name, int size, int arraySize, Type baseType)
	{
		super (baseType.getName() + "[" + arraySize + "]",
		         arraySize * baseType.getSize());
		setArraySize(arraySize);
		setBaseType(baseType);
	}

	// need more
	public boolean isArray() {
		return true;
	}

	public boolean isAssignable(Type type) {

		if (isEquivalent(type)) {
			return true;
		}
		else if((type.isPointer() && 
				((PointerType) type).getBaseType().isEquivalent(getBaseType())))
			return true;
		return false;
	}
	
	public boolean isEquivalent(Type type)
	{
		if (type.isArray() && ((ArrayType)type).getSize() == getSize() &&
			((ArrayType) type).getBaseType().isEquivalent(getBaseType()))
			return true;
		else
			return false;
	}
	
	public Type copy()
    {
    	return new ArrayType(this.m_typeName, this.m_size);
    }
	
	public int getArraySize()
	{
		return m_arraySize;
	}
	
	public void setArraySize(int s)
	{
		m_arraySize = s;
	}
	
	private int m_arraySize;
}
