class UnaryOp extends Operator
{
    public
    UnaryOp (String strName)
    {
        super (strName);
    }

    public
    UnaryOp (String strName, boolean preInc) {
        super (strName);
        m_preInc = preInc;
    }

    public STO checkOperands(STO a, STO b) 
    {
    	Type aType = a.getType();

        // for op: !
        if(this.getName() == "!") 
        {
            if(!(aType instanceof BoolType))
                return new ErrorSTO(Formatter.toString(ErrorMsg.error1u_Expr, 
                		aType.getName(), this.getName(), "bool"));
            else if (a.isConst()) 
            {
                if (((ConstSTO) a).getBoolValue ())
                    return new ConstSTO ("false", new BoolType("bool", 4));
                else
                    return new ConstSTO ("true", new BoolType ("bool", 4));
            }
            else
                return new ExprSTO("result", new BoolType("bool", 4));
        }
        return a;
    }
    
    private boolean m_preInc;
}