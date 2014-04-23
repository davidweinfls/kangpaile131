
class PointerType extends PointerGroupType{

	public PointerType(String name, int size) {
		super(name, size);
		num_of_stars = 1;
		// TODO Auto-generated constructor stub
	}
	
	public PointerType(String name, int size, Type baseType)
	{
		super(name, size);
		setBaseType(baseType);
		if(baseType.isPointer())
			num_of_stars = ((PointerType)baseType).getNumOfStars()+1;
	}

	public Type copy() 
	{
        return new PointerType (m_typeName, m_size, getBaseType());
    }
	
	public boolean isPointer()
	{
		return true;
	}
	
	public int getNumOfStars()
	{
		return num_of_stars;
	}
	
	public void setType(Type t) 
	{
        if (getBaseType() != null && getBaseType().isPointer()) 
        {
            ((PointerType)getBaseType()).setType(t);
        } 
        else 
        {
            setBaseType(t);
        }
        String temp = getBaseType().getName();
        setName (temp + m_typeName);
    }

    public Type getType() 
    {
        if (getBaseType() != null && getBaseType().isPointer()) 
        {
            return ((PointerType) getBaseType()).getType();
        } 
        else 
        {
            return getBaseType();
        }
    }
	
	public boolean isAssignable(Type t)
    {
        return isEquivalent(t);
    }
	
	public boolean isEquivalent(Type t)
	{
		if( (t instanceof PointerType) &&
				getNumOfStars() == ((PointerType) t).getNumOfStars() &&
	            getType().isEquivalent(((PointerType) t).getType())  )
			return true;
		else
			return false;
	}
	
	private int num_of_stars;
}
