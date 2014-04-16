
class PointerType extends PointerGroupType{

	public PointerType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}

	public Type copy () 
	{
        return new PointerType (this.m_typeName, this.m_size);
    }
	//Need more
}
