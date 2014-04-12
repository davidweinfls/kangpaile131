public class ComparisonOp extends BinaryOp{
	public ComparisonOp (String strName)
    {
        super (strName);
    }
	
	public STO checkOperands(STO a, STO b) {
		Type aType = a.getType();
		Type bType = b.getType();
		
		//for op: ==, !=
		if (this.getName() == "==" || this.getName() == "!=") 
		{
			//check for both numeric or bool, return error: error1b
			if (!(aType instanceof NumericType && bType instanceof NumericType) &&
				!(aType instanceof BoolType && bType instanceof BoolType)) 
			{
				return new ErrorSTO(Formatter.toString(ErrorMsg.error1b_Expr,
						aType.getName(), this.getName(), bType.getName()));
			} 
			else if ( a.isConst() && b.isConst() ) 
			{
				boolean result = false;
				switch (this.getName()) 
				{
					case "==":
					if (aType.isFloat() || bType.isFloat())
						result = (((ConstSTO) a).getFloatValue() == ((ConstSTO) b)
								.getFloatValue());
					else
						result = (((ConstSTO) a).getIntValue() == ((ConstSTO) b)
								.getIntValue());
					break;
				 
					case "!=":
					if (aType.isFloat() || bType.isFloat())
						result = (((ConstSTO) a).getFloatValue() != ((ConstSTO) b)
								.getFloatValue());
					else
						result = (((ConstSTO) a).getIntValue() != ((ConstSTO) b)
								.getIntValue());
					break;
				}
				return new ConstSTO(Boolean.toString(result), new BoolType(
						"bool", 4));
			} else
				return new ExprSTO("result", new BoolType("bool", 4));

		}// for op: ==, !=
		
		// op: <, >, <=, >=
		if (!(aType instanceof NumericType)) 
		{
			// if A is not Numeric, return error message, error1n, numeric
			// expected
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr,
					aType.getName(), this.getName()));
		} else if (!(bType instanceof NumericType)) {
			// if B is not Numeric, return error message, error1n, numeric
			// expected
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr,
					bType.getName(), this.getName()));
		} else if ( (a instanceof ConstSTO) && (b instanceof ConstSTO) ) {
			// return ExprSTO or ConstSTO of bool type
			boolean result = false;
			switch (this.getName()) 
			{
			case "<":
				result = ((ConstSTO) a).getValue() < ((ConstSTO) b).getValue();
				break;
			case ">":
				result = ((ConstSTO) a).getValue() > ((ConstSTO) b).getValue();
				break;
			case "<=":
				result = ((ConstSTO) a).getValue() <= ((ConstSTO) b).getValue();
				break;
			case ">=":
				result = ((ConstSTO) a).getValue() >= ((ConstSTO) b).getValue();
				break;
			}
			return new ConstSTO(new Boolean(result).toString(), new BoolType(
					"bool", 4));
		}
		else 
			return new ExprSTO("result", new IntType("int", 4));
		
	
	
	}//end of checkOperands
	

}