public class BitwiseOp extends BinaryOp{
	public BitwiseOp (String strName)
    {
        super (strName);
    }
	
	public STO checkOperands(STO a, STO b) {
		Type aType = a.getType();
		Type bType = b.getType();
		
		// op: &, ^, |
		if (!(aType instanceof IntType)) 
		{
			// if A is not Numeric, return error message, error1n, numeric
			// expected
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr,
					aType.getName(), this.getName(), "int"));
		} 
		else if (!(bType instanceof IntType)) 
		{
			// if B is not Numeric, return error message, error1n, numeric
			// expected
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr,
					bType.getName(), this.getName(), "int"));
		} 
		else if ( (a instanceof ConstSTO) && (b instanceof ConstSTO) ) 
		{
			// return ExprSTO or ConstSTO of bool type
			int result = 0;
			switch (this.getName()) 
			{
			case "|":
				result = ((ConstSTO) a).getIntValue() | 
				((ConstSTO) b).getIntValue();
				break;
			case "^":
				result = ((ConstSTO) a).getIntValue() ^ 
				((ConstSTO) b).getIntValue();
				break;
			case "&":
				result = ((ConstSTO) a).getIntValue() & 
				((ConstSTO) b).getIntValue();
				break;
			}
			return new ConstSTO("result", new IntType("int", 4), (double)result);
		}
		else 
			return new ExprSTO("result", new IntType("int", 4));
		
	
	}
	

}
