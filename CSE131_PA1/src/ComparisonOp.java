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
			if(aType instanceof PointerType || bType instanceof PointerType)
			{
					if ((aType instanceof NullPointerType) ||
	                        (bType instanceof NullPointerType))
					{
						return new ExprSTO("result", new BoolType("bool", 4));
					}
					else if ((aType instanceof NullPointerType) &&
	                    (bType instanceof NullPointerType))
					{
						return new ConstSTO ("nullptr", new NullPointerType("nullptr", 4));
					}
					else if (!aType.isEquivalent (bType))
					{
						return new ErrorSTO(Formatter.toString(ErrorMsg.error17_Expr,
								this.getName(), aType.getName(), bType.getName()));
					}
					else if (!(aType.isPointerType()) || !(bType.isPointerType()))
					{
						return new ErrorSTO(Formatter.toString(ErrorMsg.error1b_Expr,
	                    aType.getName(), this.getName(), bType.getName()));
					}
					else
					{
						return new ExprSTO("result", new BoolType("bool", 4));
					}
			}
			else if(aType instanceof FunctionPointerType ||
	                bType instanceof FunctionPointerType)
			{
				if((aType instanceof NullPointerType) ||
						(bType instanceof NullPointerType))
				{
					return new ExprSTO("result", new BoolType("bool", 4));
				}
				else
				{
					return new ErrorSTO(Formatter.toString(
							ErrorMsg.error1b_Expr, aType.getName(),
							this.getName(), bType.getName()));
				}
			}
			else if(aType instanceof NullPointerType || bType instanceof NullPointerType)
			{
                if(!aType.isEquivalent(bType))
                {
                    return new ErrorSTO(Formatter.toString(ErrorMsg.error17_Expr,
                     this.getName(), aType.getName(), bType.getName()));
                }
                else if(a.isConst() && b.isConst())
                {
                    boolean b_value = false;
                    if(this.getName() == "==")
                        b_value = (((ConstSTO) a).getValue() == ((ConstSTO) b).getValue());
                    else
                        b_value = (((ConstSTO) a).getValue() != ((ConstSTO) b).getValue());
                    return new ConstSTO (Boolean.toString(b_value), new BoolType("bool", 4));
                }
                else
                {
                    return new ExprSTO("result", new BoolType("bool", 4));
                }
            }
			//check for both numeric or bool, return error: error1b
			else if (!(aType instanceof NumericType && bType instanceof NumericType) &&
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
					if (aType.isFloatType() || bType.isFloatType())
						result = (((ConstSTO) a).getFloatValue() == ((ConstSTO) b)
								.getFloatValue());
					else
						result = (((ConstSTO) a).getIntValue() == ((ConstSTO) b)
								.getIntValue());
					break;
				 
					case "!=":
					if (aType.isFloatType() || bType.isFloatType())
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
			return new ExprSTO("result", new BoolType("bool", 4));
		
	
	
	}//end of checkOperands
	

}