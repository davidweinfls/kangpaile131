
public class NumericType extends Type{

	public NumericType(String strName, int size) {
		super(strName, size);
		// TODO Auto-generated constructor stub
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
	
	public boolean isNumericTypee()	{ return false; }
}
