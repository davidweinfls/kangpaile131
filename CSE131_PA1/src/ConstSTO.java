//---------------------------------------------------------------------
//
//---------------------------------------------------------------------
//zhu
class ConstSTO extends STO
{
	public int charArrayToInt(char []data, int start, int end) throws NumberFormatException
	{
	    int result = 0;
	    for (int i = start; i < end; i++)
	    {
	        int digit = (int)data[i] - (int)'0';
	        if ((digit < 0) || (digit > 9)) throw new NumberFormatException();
	        result *= 10;
	        result += digit;
	    }
	    return result;
	}
	
	public void assignValue(String strName)
	{
		char[] array = strName.toCharArray();
		if(array[0] == '0' && array.length > 1)
		{
			if(array[1] == 'x' || array[1] == 'X')
			{
				array[1] = '0';
				//if(isHex)
				//{
					String s = new String(array);
					m_value = (double) Long.parseLong(s, 16);
				//}
			}
			else
			{
				String s = new String(array);
				m_value = (double) Long.parseLong(s, 8);
			}
			
		}
		else
		{
			m_value = Double.parseDouble(strName);
		}
	}
	
	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public 
	ConstSTO (String strName)
	{
		super (strName);
		//this.assignValue(strName);
				// fix this
                // You may want to change the isModifiable and isAddressable                      
                // fields as necessary
		//System.out.println(strName + " " + m_value);
	}

	public 
	ConstSTO (String strName, Type typ)
	{
		super (strName, typ);
		
        if(strName.equals("true")) 
        	m_value = 1.0;
        else if (strName.equals("false")) 
        	m_value = 0.0;
        else if (typ instanceof IntType)
        	m_value = new Double(Long.decode(strName).intValue()); 
        else if (typ instanceof FloatType)
        	m_value = Double.valueOf(strName);
	}
	
	public
    ConstSTO (String strName, Type typ, Double value) {
        super (strName, typ, true, false);
        m_value = value;
    }

	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	public boolean
	isConst () 
	{
		return true;
	}


	//----------------------------------------------------------------
	//
	//----------------------------------------------------------------
	private void
	setValue (double val) 
	{
		m_value = new Double(val);
	}

	public Double
	getValue () 
	{
		return m_value;
	}

	public int
	getIntValue () 
	{
		return m_value.intValue();
	}

	public float
	getFloatValue () 
	{
		return m_value.floatValue();
	}

	public boolean
	getBoolValue () 
	{
		return (m_value.intValue() != 0);
	}


//----------------------------------------------------------------
//	Constants have a value, so you should store them here.
//	Note: We suggest using Java's Double class, which can hold
//	floats and ints. You can then do .floatValue() or 
//	.intValue() to get the corresponding value based on the
//	type. Booleans/Ptrs can easily be handled by ints.
//	Feel free to change this if you don't like it!
//----------------------------------------------------------------
        private Double		m_value;
}
