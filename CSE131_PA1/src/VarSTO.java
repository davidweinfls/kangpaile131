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
		super (strName, null, true, true);
		// You may want to change the isModifiable and isAddressable 
		// fields as necessary
		//setIsAddressable(true);
		//setIsModifiable(true);
		ref = false;
	}

	public 
	VarSTO (String strName, Type typ)
	{
		super (strName, typ, true, true);
		// You may want to change the isModifiable and isAddressable 
		// fields as necessary
		//setIsAddressable(true);
		//setIsModifiable(true);
		ref = false;
	
	}
	
	public VarSTO (String strName, Type type, boolean isRef)
	{
		super (strName, type, true, true);
        this.ref = isRef;
	}
	
	public
    VarSTO (String strName, Type typ, boolean isAdd, boolean isMod) {
        super (strName, typ, isAdd, isMod);
        ref = false;
    }

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public boolean   
	isVar () 
	{
		return true;
	}
	
	public boolean
    isRef () 
	{
        return ref;
    }

    public void
    setRef () 
    {
        ref = true;
    }
	
	private boolean ref;

}
