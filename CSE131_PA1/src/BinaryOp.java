abstract class BinaryOp extends Operator
{
    public BinaryOp (String strName)
    {
        super (strName);
    }

    public abstract STO checkOperands(STO a, STO b);
}