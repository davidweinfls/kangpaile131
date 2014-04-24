import java.util.Vector;


public class StructType extends CompositeType{

	public StructType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}
	
	public StructType (String name, int size, Vector<STO> list)
    {
        super (name, size);
        fieldList = list;
    }
	
	public boolean isStructType()
	{
		return true;
	}
	
	public Type copy () 
	{
        return new StructType (m_typeName, m_size, fieldList);
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
    
    public Vector<STO> getFieldList()
    {
    	return fieldList;
    }
    
    public void setFieldList(Vector<STO> list)
    {
    	fieldList = list;
    }
    
    public void setSize(int size)
    {
    	m_size = size;
    }
    
    private Vector<STO> fieldList;
}
