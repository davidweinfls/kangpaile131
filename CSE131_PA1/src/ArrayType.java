class ArrayType extends CompositeType {

	public ArrayType(String name, int size) {
		super(name, size);
		// TODO Auto-generated constructor stub
	}

	// need more
	public boolean isArray() {
		return true;
	}

	public boolean isAssignable(Type type) {

		if (isEquivalent(type)) {
			return true;
		}
		return false;
	}
	
	public Type copy()
    {
    	return new ArrayType(this.m_typeName, this.m_size);
    }
}
