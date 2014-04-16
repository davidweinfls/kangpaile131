
public class StructType extends CompositeType{

	public StructType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}
	
	public Type copy () 
	{
        return new StructType (this.m_typeName, this.m_size);
    }

	//Need more
}
