
class VoidType extends Type {

	public VoidType(String strName, int size) {
		super(strName, size);
		// TODO Auto-generated constructor stub
	}

	//need more
	
	public boolean	isVoidType ()	{ return true; }
	
	public Type copy(){return null;}
	
	public boolean isAssignable(Type t)
	{
        return isEquivalent(t);
    }

    public boolean isEquivalent(Type t)
    {
        if(t.isVoidType())
        	return true;
        else
        	return false;
    }
}
