
abstract class BasicType extends Type{

	public BasicType(String strName, int size) {
		super(strName, size);
		// TODO Auto-generated constructor stub
	}
	public boolean isAssignable(Type t){
		if(t instanceof BasicType){
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
	public boolean	isBasicType ()	{ return true; }

}
