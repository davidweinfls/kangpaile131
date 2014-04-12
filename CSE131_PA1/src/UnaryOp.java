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
    	return a;
    }
    
    private boolean m_preInc;
}