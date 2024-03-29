//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ErrorSTO extends STO
{
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	ErrorSTO (String strName)
	{
		super (strName);
	}

	//----------------------------------------------------------------
	//	There are times where it is an error if the STO is not a 
	//	constant or adddressable or something else. However, if
	//	the STO is already an error, nothing should be said. To
	//	supress that error, we would have to check if the STO is
	//	not an ErrorSTO as well as what we want it to be.  Rather
	//	than 2 checks we'll have the ErrorSTO always return true
	//	for every check.  (This is an example of where the
	//	instanceof operator would not have been appropriate.)
	//----------------------------------------------------------------
        public boolean  isVar ()              { return false; }
        public boolean  isConst ()            { return false; }
        public boolean  isExpr ()             { return false; }
        public boolean  isFunc ()             { return false; }
        public boolean  isTypedef ()          { return false; }
        public boolean  isError ()            { return true; }

	public boolean  getIsAddressable ()   { return true; }
	public boolean  getIsModLValue ()     { return true; }
}
