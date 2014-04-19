/**
 * 
 */

/**
 * @author David Wei
 *
 */
public class VariableBox {

	public VariableBox()
	{
		name = null;
		sto = null;
	}
	
	public VariableBox(String id, STO s)
	{
		name = id;
		sto = s;
	}
	
	public String getName()
	{
		return name;
	}
	
	public STO getSTO()
	{
		return sto;
	}
	
	private String name;
	private STO sto;
}
