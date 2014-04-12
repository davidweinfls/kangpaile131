class IntType extends NumericType {

	public IntType(String strName, int size) {
		super(strName, size);
		// TODO Auto-generated constructor stub
	}

	// need more
	public boolean isInt() {
		return true;
	}

	public boolean isAssignable(Type type) {
		if (isEquivalent(type)) {
			return true;
		}
		if (type.getClass() == FloatType.class) {
			return true;
		}

		return false;
	}

	public boolean isIntType() {
		return true;
	}
}
