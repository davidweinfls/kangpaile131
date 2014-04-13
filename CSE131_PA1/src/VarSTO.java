//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class VarSTO extends STO
{
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	VarSTO (String strName)
	{
		super (strName);
		// You may want to change the isModifiable and isAddressable 
		// fields as necessary
		setIsAddressable(true);
		setIsModifiable(true);
	}

	public 
	VarSTO (String strName, Type typ)
	{
		super (strName, typ);
		// You may want to change the isModifiable and isAddressable 
		// fields as necessary
		setIsAddressable(true);
		setIsModifiable(true);
	
	}

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public boolean   
	isVar () 
	{
		return true;
	}

}
