//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

import java.util.Vector;


class Scope
{
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	Scope ()
	{
		m_lstLocals = new Vector<STO> ();
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public STO
	access (String strName)
	{
		return	accessLocal (strName);
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public STO
	accessLocal (String strName)
	{
		STO		sto = null;

		for (int i = 0; i < m_lstLocals.size (); i++)
		{
			sto = m_lstLocals.elementAt (i);

			if (sto.getName ().equals (strName))
				return (sto);
		}

		return (null);
	}
	
	public void markReturned()
	{
		m_isReturned = true;
	}

	public boolean getReturned()
	{
		return m_isReturned;
	}

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public void
	InsertLocal (STO sto)
	{
		m_lstLocals.addElement (sto);
	}


//----------------------------------------------------------------
//	Instance variables.
//----------------------------------------------------------------
	private Vector<STO>		m_lstLocals;
	private boolean 		m_isReturned;
}
