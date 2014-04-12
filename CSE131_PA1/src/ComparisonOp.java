public class ComparisonOp extends BinaryOp{
	public ComparisonOp (String strName)
    {
        super (strName);
    }
	
	public STO checkOperands(STO a, STO b) {
		return a;
	}
	

}