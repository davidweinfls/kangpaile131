abstract class CompositeType extends Type {

	public CompositeType(String name, int size) {
		super(name, size);

	}

	public boolean isAssignable(Type t){
		if(t instanceof CompositeType){
			return true;
		}
		return false;		
	}
	public boolean isEquivalent(Type t){
		if(this.getClass() == t.getClass()){
			return true;
		}
		return false;		
	}
	public boolean	isCompositeType ()	{ return true; }
	
	public Type copy(){return null;}
	
	public void setBaseType (Type t) 
	{
        m_baseType = t;
    }

    public Type getBaseType () 
    {
        return m_baseType;
    }
	
	private Type m_baseType;

}