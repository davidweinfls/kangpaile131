
class PointerType extends PointerGroupType{

	public PointerType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}

	public Type copy () 
	{
        return new PointerType (this.m_typeName, this.m_size);
    }
	
	public boolean isPointer()
	{
		return true;
	}
	
	public int getNumOfStars()
	{
		return num_of_stars;
	}
	
	public void setType (Type t) 
	{
        if (getBaseType() != null && getBaseType().isPointer()) 
        {
            ((PointerType) getBaseType()).setType (t);
        } 
        else 
        {
            setBaseType (t);
        }
        //setName (getType().getName() + m_typeName);
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
        return isEquivalent (t);
    }
	
	public boolean isEquivalent(Type t)
	{
		if( (t instanceof PointerType) &&
				getNumOfStars() == ((PointerType) t).getNumOfStars () &&
	            getType().isEquivalent(((PointerType) t).getType())  )
			return true;
		else
			return false;
	}
	
	//Need more
	private int num_of_stars;
}
