
public class BooleanOp extends BinaryOp{
	public BooleanOp (String strName)
    {
        super (strName);
    }
	
	public STO checkOperands(STO a, STO b) {
		Type aType = a.getType();
		Type bType = b.getType();
		
		// for op: op: &&, ||
		// check for both numeric or bool, return error: error1b
		if (!(aType instanceof BoolType)) 
		{
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr,
					aType.getName(), this.getName(), "bool"));
		} 
		else if (!(bType instanceof BoolType)) 
		{
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr,
					bType.getName(), this.getName(), "bool"));
		} 
		else if (a.isConst() && b.isConst()) 
		{
			boolean result = false;
			double val = 0.0;
			switch (this.getName()) 
			{
			case "&&":
				result = (((ConstSTO) a).getBoolValue() && ((ConstSTO) b)
						.getBoolValue());
				break;

			case "||":
				result = (((ConstSTO) a).getBoolValue() || ((ConstSTO) b)
						.getBoolValue());
				break;
			}
			if (result == true)
				val = 1.0;
			else
				val = 0.0;
			return new ConstSTO("result", new BoolType("bool", 4), val);
		} 
		else
			return new ExprSTO("result", new BoolType("bool", 4));

	}
	

}
