
public class BoolType extends BasicType{

	public BoolType(String strName, int size) {
		super(strName, size);
		// TODO Auto-generated constructor stub
	}
	//Need more
	public boolean isBoolType(){
		return true;
	}
	
	public boolean
    isAssignable (Type t)
    {
        return isEquivalent(t);
    }

    public boolean
    isEquivalent (Type t)
    {
        if (t instanceof BoolType)
            return true;
        return false;
    }
	
    public Type copy()
    {
    	return new BoolType(this.m_typeName, this.m_size);
    }
	
	
}
