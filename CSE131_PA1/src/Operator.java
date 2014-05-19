//---------------------------------------------------------------------
// This is the top of the Operator hierarchy. You most likely will need to
// create sub-classes (since this one is abstract) that handle specific
// Operators, such as IntOperator, FloatOperator, ArrayOperator, etc.
//---------------------------------------------------------------------

abstract class Operator
{
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	Operator (String strName)
	{
		setName(strName);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public String
	getName ()
	{
		if(m_OperatorName == "%") return "%%";
		return m_OperatorName;
	}

	private void
	setName (String str)
	{
		m_OperatorName = str;
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public int
	getSize ()
	{
		return m_size;
	}

	private void
	setSize (int size)
	{
		m_size = size;
	}
	
	public abstract STO checkOperands(STO a, STO b);

	//----------------------------------------------------------------
	//	It will be helpful to ask a Operator what specific Operator it is.
	//----------------------------------------------------------------
	public boolean	isBinaryOp ()	{ return false; }
	public boolean	isUnaryOp ()	{ return false; }
	


	//----------------------------------------------------------------
	//	Name of the Operator (e.g., int, bool, or some Operatordef
	//----------------------------------------------------------------
	private String  	m_OperatorName;
	private int		m_size;
}
