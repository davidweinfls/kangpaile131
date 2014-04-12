public class ArithmeticOp extends BinaryOp {
	public ArithmeticOp(String strName) {
		super(strName);
	}

	public STO checkOperands(STO a, STO b) {
		Type aType = a.getType();
		Type bType = b.getType();
		
		// for op: +, -, *, /
		if (!(aType instanceof NumericType)) {
			// if A is not Numeric, return error message, error1n, numeric
			// expected
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr,
					aType.getName(), this.getName()));
		} else if (!(bType instanceof NumericType)) {
			// if B is not Numeric, return error message, error1n, numeric
			// expected
			return new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr,
					bType.getName(), this.getName()));
		} else if (aType instanceof IntType && bType instanceof IntType) {
			// return ExprSTO or ConstSTO of int type
			int result = 0;
			if ((a instanceof ConstSTO) && (b instanceof ConstSTO)) {
				switch (this.getName()) {
				case "+":
					result = ((ConstSTO) a).getIntValue()
							+ ((ConstSTO) b).getIntValue();
					break;
				case "-":
					result = ((ConstSTO) a).getIntValue()
							- ((ConstSTO) b).getIntValue();
					break;
				case "*":
					result = ((ConstSTO) a).getIntValue()
							* ((ConstSTO) b).getIntValue();
					break;
				case "/":
					if (((ConstSTO) b).getIntValue() == 0) {
						return new ErrorSTO(ErrorMsg.error8_Arithmetic);
					}
					result = ((ConstSTO) a).getIntValue()
							/ ((ConstSTO) b).getIntValue();
					break;
				}
				return new ConstSTO("result", new IntType("int", 4),
						(double) result);
			}
			return new ExprSTO("result", new IntType("int", 4));
		} else {
			// return ExprSTO or ConstSTO of float type
			float result = 0;
			if ((a instanceof ConstSTO) && (b instanceof ConstSTO)) {
				switch (this.getName()) {
				case "+":
					result = ((ConstSTO) a).getFloatValue()
							+ ((ConstSTO) b).getFloatValue();
					break;
				case "-":
					result = ((ConstSTO) a).getFloatValue()
							- ((ConstSTO) b).getFloatValue();
					break;
				case "*":
					result = ((ConstSTO) a).getFloatValue()
							* ((ConstSTO) b).getFloatValue();
					break;
				case "/":
					if (((ConstSTO) b).getFloatValue() == 0.0) {
						return new ErrorSTO(ErrorMsg.error8_Arithmetic);
					}
					result = ((ConstSTO) a).getFloatValue()
							/ ((ConstSTO) b).getFloatValue();
					break;
				}
				return new ConstSTO("result", new IntType("float", 4),
						(double) result);
			}
			return new ExprSTO("result", new IntType("float", 4));
		}	//end of checking +-*/
		
		
		
		
	} // end of checkOperands
	
	

}//end of class
