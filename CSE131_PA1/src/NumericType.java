abstract class NumericType extends BasicType{

	public NumericType(String strName, int size) {
		super(strName, size);
	}

	public boolean	isNumericType ()	{ return true; }
	
	public Type copy()
	{
		return null;
	}
}