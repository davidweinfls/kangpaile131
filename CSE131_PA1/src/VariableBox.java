/**
 * 
 */

/**
 * @author David Wei
 *
 */
public class VariableBox<Variable, Expr> {

	public VariableBox()
	{
		var = null;
		expr = null;
	}
	
	public VariableBox(Variable a, Expr e)
	{
		var = a;
		expr = e;
	}
	
	public Variable getVariable()
	{
		return var;
	}
	
	public Expr getExpr()
	{
		return expr;
	}
	
	private Variable var;
	private Expr expr;
}
