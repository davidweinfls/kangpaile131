
public class BooleanOp extends BinaryOp{
	public BooleanOp (String strName)
    {
        super (strName);
    }
	
	public STO checkOperands(STO a, STO b) {
		return a;
	}
	

}
