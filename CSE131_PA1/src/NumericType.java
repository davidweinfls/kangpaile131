abstract class NumericType extends BasicType{

	public NumericType(String strName, int size) {
		super(strName, size);
	}
	public boolean isAssignable(Type t){
		if(t instanceof NumericType){
			return true;
		}
		return false;		
	}
	public boolean isEquivalent(Type t){
		if(this.getClass() == t.getClass()){
			return true;
		}
		return false;		
	}
	public boolean	isNumericType ()	{ return true; }
}