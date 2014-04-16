
public class StructType extends CompositeType{

	public StructType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}
	
	public Type copy () 
	{
        return new StructType (this.m_typeName, this.m_size);
    }

	public boolean
    isAssignable (Type t)
    {
        return isEquivalent(t);
    }

    public boolean
    isEquivalent (Type t)
    {
        if((t instanceof StructType) && (this.m_typeName.equals(t.m_typeName)))
            return true;
        return false;
    }
	//Need more
}
