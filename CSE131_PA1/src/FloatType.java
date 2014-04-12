class FloatType extends NumericType {

	public FloatType(String strName, int size) {
		super(strName, size);
		// TODO Auto-generated constructor stub
	}

	// Need more

	public boolean isFloat() {

		return true;
	}

	public boolean isAssignable(Type type) {
		if (isEquivalent(type)){
			return true;
		}
		if (type.getClass() == FloatType.class) {
			return true;
		}

		return false;
	}
	public boolean isFloatType() {
		return true;
	}
}
