
class NullPointerType extends PointerType {

	public NullPointerType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}

	public Type copy () 
	{
        return new NullPointerType (this.m_typeName, this.m_size);
    }
	//Need more
}
