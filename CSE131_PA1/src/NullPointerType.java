
class NullPointerType extends PointerType {

	public NullPointerType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}

	public Type copy() 
	{
        return new NullPointerType (this.m_typeName, this.m_size);
    }
	//Need more
	
	public void setType(Type t)
	{
		if(getBaseType() != null && getBaseType().isPointer())
		{
            ((PointerType)getBaseType()).setType (t);
        }
		else
		{
            setBaseType (t);
        }
    }

    public Type getType()
    {
    	if(getBaseType() != null && getBaseType().isPointer())
    	{
            return ((PointerType)getBaseType()).getType();
        }
    	else
    	{
            return getBaseType();
        }
    }

    public String getPointerName()
    {
    	return getType().getName() + getName();
    }

    public boolean isNullPointer()
    {
        return true;
    }

    public boolean
    isAssignable(Type t)
    {
        return (t instanceof PointerGroupType);
    }

    public boolean isEquivalent(Type t)
    {
        if(t instanceof NullPointerType) 
        	return true;
        return false;
    }
}
