import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AssemblyCodeGenerator {

	private boolean debug = true;
	
	//useful instance vars
	private int indent_level = 0;
	private String currFuncName;
	private int num_of_temp = 0;
	private int num_of_bool = 0;
	private int num_of_if = 0;
	private int num_of_else = 0;
	private int num_of_and = 0;
	private int num_of_or = 0;
	private int num_of_comp = 0;
	
	// if localReg = 0, means %l1 is not taken, otherwise if localReg = 1, %l1 is taken, can be used
	private int localReg = 0;
	// if floatReg = 0, means %f0 is not taken, otherwise if floatReg = 1, %f0 is taken, can be used
	private int floatReg = 0;
	
	private Stack<String> ifStack = new Stack<String>();
	private Stack<String> andStack = new Stack<String>();
	private Stack<String> orStack = new Stack<String>();
	private Stack<String> compStack = new Stack<String>();
	
	// 2
    private static final String ERROR_IO_CLOSE = 
        "Unable to close fileWriter";
    private static final String ERROR_IO_CONSTRUCT = 
        "Unable to construct FileWriter for file %s";
    private static final String ERROR_IO_WRITE = 
        "Unable to write to fileWriter";
    
    // String builders
    public StringBuilder data_buffer;
    public StringBuilder rodata_buffer;
    public StringBuilder text_buffer;
    public StringBuilder bss_buffer;
    
    public boolean has_data 	= true;
    public boolean has_rodata = true;
    public boolean has_text 	= false;
    public boolean has_bss 	= false;

    // 3
    private FileWriter fileWriter;
    
    // 4
    private static final String FILE_HEADER = 
        "! \n" +
        "! Generated %s\n" + 
        "! \n\n";
    
    public AssemblyCodeGenerator(String fileToWrite) {
        try {
            fileWriter = new FileWriter(fileToWrite);
            
            // 7
            writeAssembly(FILE_HEADER, (new Date()).toString());
        } catch (IOException e) {
            System.err.printf(ERROR_IO_CONSTRUCT, fileToWrite);
            e.printStackTrace();
            System.exit(1);
        } 
        
        increaseIndent();
        
        //initialization
        data_buffer = new StringBuilder();
        rodata_buffer = new StringBuilder();
        text_buffer = new StringBuilder();
        bss_buffer = new StringBuilder();
        
        writeSectionHeader(data_buffer, Sparc.DATA_SEC);
        writeSectionHeader(rodata_buffer, Sparc.RODATA_SEC);
        writeSectionHeader(text_buffer, Sparc.TEXT_SEC);
        writeSectionHeader(bss_buffer, Sparc.BSS_SEC);
    }
    
    // 8
    public void decreaseIndent() {
        indent_level--;
    }
    
    public void dispose() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(ERROR_IO_CLOSE);
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void increaseIndent() {
        indent_level++;
    }
    
    public void resetReg()
    {
    	localReg = 0;
    	floatReg = 0;
    }
	
    // write timestamp
    public void writeAssembly(String template, String ... params) {
        StringBuilder asStmt = new StringBuilder();
        
        // 10
        for (int i=0; i < indent_level; i++) {
            asStmt.append(Sparc.SEPARATOR);
        }
        
        // 11
        asStmt.append(String.format(template, (Object[])params));
        
        try {
            fileWriter.write(asStmt.toString());
        } catch (IOException e) {
            System.err.println(ERROR_IO_WRITE);
            e.printStackTrace();
        }
    }
    
    // append string to a chosen buffer(section) 
    public void addToBuffer(StringBuilder sb, String template, String ... params)
    {
    	for (int i=0; i < indent_level; i++) {
            sb.append(Sparc.SEPARATOR);
        }
        sb.append(String.format(template, (Object[])params));
    }
    
    // write buffer to file
    public void writeBuffer(StringBuilder s)
    {
    	try {
            fileWriter.write(s.toString());
        } catch (IOException e) {
            System.err.println(ERROR_IO_WRITE);
            e.printStackTrace();
        }
    }
    
    //write section header
    public void writeSectionHeader(StringBuilder sb, String section)
    {
        addToBuffer(sb, section);
        addToBuffer(sb, Sparc.ALIGN_DIR, "4");

        decreaseIndent();
        addToBuffer(sb, Sparc.NEW_LINE);
        increaseIndent();
    }
    
	public void writeDebug (String s) {
        decreaseIndent();
        addToBuffer(text_buffer, Sparc.NEW_LINE);
        addToBuffer(text_buffer, "! " + s + "\n" );
        increaseIndent();
    }
    
    /*
     * P2: write global variable initialization
     * 
     */
    public void writeGlobalVariable(String id, boolean init, STO sto, Type t )
    {
    	if(debug) writeDebug("---------In writeGLobalVariable--------------");
    	
    	// add .global to file
    	if(init)
    	{
    		addToBuffer(data_buffer, Sparc.GLOBAL_VAR, id);
    		addToBuffer(data_buffer, Sparc.NEW_LINE);
    	}
    	else
    	{
    		addToBuffer(bss_buffer, Sparc.GLOBAL_VAR, id);
    		addToBuffer(bss_buffer, Sparc.NEW_LINE);
    	}
 
    	decreaseIndent();
    	//check if this var is initialized
    	if(init)
    	{
    		has_data = true;
    		if(t instanceof IntType || t instanceof BoolType)
                addToBuffer(data_buffer, Sparc.VAR_LABEL, id, "word", Integer.toString( ( (ConstSTO) sto ).getIntValue() ) );
            else if(t instanceof FloatType)
            	addToBuffer(data_buffer, Sparc.VAR_LABEL, id, "single", "0r" + Float.toString( ((ConstSTO) sto).getFloatValue()) );
    	}
    	else
    	{
    		addToBuffer(bss_buffer, Sparc.BSS_VAR, id, Integer.toString(t.getSize()) );
    		has_bss = true;
    	}
    	
    	increaseIndent();

        decreaseIndent();
        if(init)
        	addToBuffer(data_buffer, Sparc.NEW_LINE);
        else
        	addToBuffer(bss_buffer, Sparc.NEW_LINE);
        increaseIndent();
   	
    }
    
    public void writeStaticVariable(String id, boolean init, STO sto, Type t )
    {
    	if(debug) writeDebug("---------In writeStaticVariable--------------");
 
    	decreaseIndent();
    	//check if this var is initialized
    	if(init)
    	{
    		has_data = true;
    		if(t instanceof IntType || t instanceof BoolType)
                addToBuffer(data_buffer, Sparc.VAR_LABEL, id, "word", Integer.toString( ( (ConstSTO) sto ).getIntValue() ) );
            else if(t instanceof FloatType)
            	addToBuffer(data_buffer, Sparc.VAR_LABEL, id, "single", "0r" + Float.toString( ((ConstSTO) sto).getFloatValue()) );
    	}
    	else
    	{
    		addToBuffer(bss_buffer, Sparc.BSS_VAR, id, Integer.toString(t.getSize()) );
    		has_bss = true;
    	}
    	
    	increaseIndent();

        decreaseIndent();
        if(init)
        	addToBuffer(data_buffer, Sparc.NEW_LINE);
        else
        	addToBuffer(bss_buffer, Sparc.NEW_LINE);
        increaseIndent();
   	
    }
    
    // used in DoVarDecl. write assembly for local var with initialization
 	void writeLocalVariableWInit(STO var, STO expr)
 	{
 		if (debug)
 			writeDebug("---------in writeLocalVariableWInit:" + var.getName());
 		addToBuffer(text_buffer, var.getAddress());

 		Type varType = var.getType();
 		Type exprType = expr.getType();
 		if (varType instanceof FloatType && exprType instanceof FloatType)
 		{
 			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "["
 					+ Sparc.L0 + "]");
 		} else
 		{
 			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "["
 					+ Sparc.L0 + "]");
 		}

 		decreaseIndent();
 		addToBuffer(text_buffer, Sparc.NEW_LINE);
 		increaseIndent();
 		
 		localReg = 0;
 		floatReg = 0;
 	}
     
 	// used in DoVarDecl. write assembly for local var without initialization
 	void writeLocalVariableWOInit(STO var)
 	{
 		if (debug)
 			writeDebug("---------in writeLocalVariableWOInit:" + var.getName());

 		//not sure what to do
 	}
    
    void writeConstVar(String id, boolean is_static, boolean is_global, STO sto, Type t)
    {
    	if(debug) writeDebug("------------in writeConstVar: " + sto.getName());
        has_rodata = true;

        if(is_global && !is_static)
            addToBuffer(rodata_buffer, Sparc.GLOBAL_VAR, id);

        decreaseIndent();
        if(t instanceof IntType || t instanceof BoolType)
        	addToBuffer(rodata_buffer, Sparc.VAR_LABEL, id, "word",
                Integer.toString( ((ConstSTO) sto).getIntValue()) );
        else if(t instanceof FloatType)
        	addToBuffer(rodata_buffer, Sparc.VAR_LABEL, id, "single", "0r" +
                Float.toString( ((ConstSTO) sto).getFloatValue()) );
        else if (t instanceof PointerType || t instanceof NullPointerType)
        	addToBuffer(data_buffer, Sparc.VAR_LABEL, id, "word", "0");

        increaseIndent();

        decreaseIndent();
        addToBuffer(rodata_buffer, Sparc.NEW_LINE);
        increaseIndent();
    }
    
    void writeLocalConst()
    {
    	
    }
    
	public void writeConstValue(STO sto)
	{
		if (debug)
			writeDebug("------in writeConstantLiteral: " + sto.getName());
		Type t = sto.getType();
		
		if (t instanceof IntType || t instanceof BoolType)
		{
			// set 
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
					Integer.toString(((ConstSTO) sto).getIntValue()), Sparc.L1);
			addToBuffer(text_buffer, sto.getAddress());
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "["
					+ Sparc.L0 + "]");
		} 
		else if (t instanceof FloatType)
		{
			has_rodata = true;
			decreaseIndent();
			
			// temp0:	.single 0r3.5
			addToBuffer(rodata_buffer, Sparc.VAR_LABEL, "temp" + num_of_temp,
					"single",
					"0r" + Float.toString(((ConstSTO) sto).getFloatValue()));
			increaseIndent();
			// set	temp0, %l0
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "temp"
					+ num_of_temp, Sparc.L0);
			// ld	[%l0], %f0
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0
					+ "]", Sparc.F0);
			// set	-4, %l0
			// add	%fp, %l0, %l0
			addToBuffer(text_buffer, sto.getAddress());
			// st	%f0, [%l0]
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "["
					+ Sparc.L0 + "]");
			num_of_temp++;
		} 
		else if (t instanceof NullPointerType)
		{
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L1);
			addToBuffer(text_buffer, sto.getAddress());
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "["
					+ Sparc.L0 + "]");
		}
	}
    
    /*
     * write print statement, for cout
     */
    public void writePrint(STO sto)
    {
    	//normally we should just do this in write Func
    	//has_text = true;
    	localReg = 0;
    	floatReg = 0;
    	if(debug) writeDebug("------------in writePrint---------------" );
    	
    	if(sto == null)
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.ENDL, Sparc.O0 );
    		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
    		addToBuffer(text_buffer, Sparc.NOP);
    		return;
    	}
    	
    	Type t = sto.getType(); 
    	
    	if (t == null) {
            has_rodata = true;
            has_data = true;
            String id = sto.getName();
            decreaseIndent();
            addToBuffer(rodata_buffer, Sparc.STRING_TEMP, "temp" + num_of_temp, id);
            increaseIndent();
            addToBuffer(rodata_buffer, Sparc.ALIGN_DIR, "4");
            addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "temp" + num_of_temp, Sparc.O0);
            addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
            addToBuffer(text_buffer, Sparc.NOP);
            num_of_temp++;
            return;
        }
    	// set
    	// add
    	// ld
    	getValue(sto);
    	
	    if(t instanceof IntType)
	    {
	    	/*
	    	 * set _intFmt, %o0
			 * set 5, %o1
			 * call printf
			 * nop
	    	 */
	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.INTFMT, Sparc.O0 );
	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L1 , Sparc.O1);
	    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
	    	addToBuffer(text_buffer, Sparc.NOP);		
	    } 
	    else if(t instanceof FloatType)
	    {
	    	// call printFloat
	    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTFLOAT);
	    	addToBuffer(text_buffer, Sparc.NOP);
	    }
	    else if(t instanceof BoolType)
	    {
	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.BOOLF, Sparc.O0);
	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
	    	addToBuffer(text_buffer, Sparc.ONE_PARAM, "be", ".printBool"+num_of_bool);
	    	addToBuffer(text_buffer, Sparc.NOP);
	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.BOOLT, Sparc.O0);

            decreaseIndent();
            addToBuffer(text_buffer, Sparc.NEW_LINE);
            addToBuffer(text_buffer, ".printBool"+num_of_bool+":\n");
            increaseIndent();
            addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
            addToBuffer(text_buffer, Sparc.NOP);
            num_of_bool++;
	    }
	    localReg = 0;
    	floatReg = 0;
    	
    }
    
    // assembly template, get address and then load the value stored in that address to L1
    public void getValue(STO sto)
    {
    	if(debug) writeDebug("-------in getValue: " + sto.getName() + ": " + ( ( sto instanceof ConstSTO ) ?  ((ConstSTO)sto).getValue() : "null") );
    	
		if (sto instanceof FuncSTO)
		{
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, sto.getName(), Sparc.L1);
			return;
		}
		
		Type t = sto.getType();
		
		if(t instanceof FloatType)
		{
			// set	-4, %l0
			// add	%fp, %l0, %l0
			addToBuffer(text_buffer, sto.getAddress());
			if(floatReg == 0)
			{
				// ld	[%l0], %f0
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F0);
				floatReg = 1;
			}
			else
			{
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F1);
				floatReg = 0;
			}
		}
		else if(t instanceof IntType || t instanceof BoolType)
		{
			addToBuffer(text_buffer, sto.getAddress());
			if(localReg == 0)
			{
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
				localReg = 1;
			}
			else
			{
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L2);
				localReg = 0;
			}
		}
    }
    
    void intToFloat(STO sto)
    {
    	if(debug) writeDebug("---------intToFloat: " + sto.getName() + " " + ((sto instanceof ConstSTO) ? ((ConstSTO)sto).getIntValue() : null) );
    	Type t = new FloatType(sto.getName(), 4);
    	
    	//1. get address
    	if(debug) writeDebug("=======in intToFloat: getAddress of " + sto.getName());
    	addToBuffer(text_buffer, sto.getAddress());
    	
    	//2. load value to float register
    	if(debug) writeDebug("=======in intToFloat: load value of " + sto.getName());
    	if(floatReg == 0)
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F0);
    		
    		//3. call intofloat
    		if(debug) writeDebug("=======in intToFloat: call itos " + sto.getName());
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FITOS_OP, Sparc.F0, Sparc.F0);
    		floatReg = 1;
    	}
    	else
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F1);
    		
    		//3. call intofloat
    		if(debug) writeDebug("=======in intToFloat: call itos " + sto.getName());
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FITOS_OP, Sparc.F1, Sparc.F1);
    		floatReg = 0;
    	}
    }
    
    //used in DoBinaryExpr
    void writeBinaryExpr(STO a, Operator o, STO b, STO result)
    {
    	if(debug) writeDebug("--------in writeBinaryExpr-------");
    	writeDebug(a.getName() + " " + o.getName() + " " + b.getName());
    	Type aType = a.getType();
    	Type bType = b.getType();
    	Type rType = result.getType();
    	String opName = o.getName();
    	// if constant folding
    	if(result instanceof ConstSTO)
    	{
    		if(debug) writeDebug("=======in writeBinaryExpr: Const folding=======");
    		if(rType instanceof IntType)
    		{
    			int value = ((ConstSTO) result).getIntValue ();
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
                    Integer.toString(value), Sparc.L1);
    			addToBuffer(text_buffer, result.getAddress());
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    		else if (rType instanceof FloatType)
			{
				float value = ((ConstSTO) result).getFloatValue();
				has_rodata = true;
				decreaseIndent();
				addToBuffer(rodata_buffer, Sparc.VAR_LABEL, "temp" + num_of_temp, "single",
						"0r" + Float.toString(value));
				increaseIndent();
				//get float value
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "temp" + num_of_temp, Sparc.L0);

				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F0);
				//get var address
				addToBuffer(text_buffer, result.getAddress());
				//assign
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
				num_of_temp++;
			}
    		else if(rType instanceof BoolType)
    		{
    			int value = ((ConstSTO) result).getIntValue ();
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
                    Integer.toString(value), Sparc.L1);
    			addToBuffer(text_buffer, result.getAddress());
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    	}
    	// not constant folding
    	else
    	{
    		if(debug) writeDebug("=======in writeBinaryExpr: Not const folding=======");
    		
    		//get a and b's value
    		if(aType.isFloatType() || bType.isFloatType())
    		{
    			if(debug) writeDebug("=======in writeBinaryExpr: get " + a.getName() + "'s value and " + b.getName() + "'s value");
    			// int float conversion
    			// int, float
    			// float, int
    			// float, float
    			if(aType.isIntType() && bType.isFloatType())
    			{
    				//convert a to float
    				intToFloat(a);
    				getValue(b);
    			}
    			else if(aType.isFloatType() && bType.isIntType())
    			{
    				//convert b to float
    				getValue(a);
    				intToFloat(b);
    			}
    			else
    			{
    				getValue(a);
        			getValue(b);
    			}
    		}
    		else if(opName != "&&" && opName != "||")
    		{
    			if(debug) writeDebug("=======in writeBinaryExpr: get " + a.getName() + "'s value and " + b.getName() + "'s value");
    			getValue(a);
    			getValue(b);
    		}
    		//two parts. one for comparisonOp: > < >= <= == !=
    		//one for non comparisonOp: +-*/% &|^ && ||
    		if(!(o instanceof ComparisonOp))
    		{
    			if(debug) writeDebug("=======in writeBinaryExpr, do computation=========");
    			switch(opName)
    			{	
    				case "+":
    					if(aType.isFloatType() || bType.isFloatType())
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F0, Sparc.F1, Sparc.F0);
    					}
    					else
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L1, Sparc.L2, Sparc.L1);
    					}
    					break;
    				case "-":
    					if(aType.isFloatType() || bType.isFloatType())
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FSUBS_OP, Sparc.F0, Sparc.F1, Sparc.F0);
    					}
    					else
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L1, Sparc.L2, Sparc.L1);
    					}
    					break;
    				case "*":
    					if(aType.isFloatType() || bType.isFloatType())
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FMULS_OP, Sparc.F0, Sparc.F1, Sparc.F0);
    					}
    					else
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.MUL, Sparc.L1, Sparc.L2, Sparc.L1);
    					}
    					break;
    				case "/":
    					if(aType.isFloatType() || bType.isFloatType())
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FDIVS_OP, Sparc.F0, Sparc.F1, Sparc.F0);
    					}
    					else
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.DIV, Sparc.L1, Sparc.L2, Sparc.L1);
    					}
    					break;
    				case "%%":
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L1, Sparc.O0);
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L2, Sparc.O1);
    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.REM);
    					addToBuffer(text_buffer, Sparc.NOP);
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.O0, Sparc.L1);
    					break;
    				case "&":
    					addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.AND_OP, Sparc.L1, Sparc.L2, Sparc.L1);
    					break;
    				case "|":
    					addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.OR_OP, Sparc.L1, Sparc.L2, Sparc.L1);
    					break;
    				case "^":
    					addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.XOR_OP, Sparc.L1, Sparc.L2, Sparc.L1);
    					break;
    				case "&&":
    					//check first operand
    					//load a, if equals to 0, branch to endAND
    					if(debug) writeDebug("=======in writeBinaryExpr, &&, check first operand=========");
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
    					addToBuffer(text_buffer, a.getAddress());
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BE_OP, "endAND" + num_of_and);
    					addToBuffer(text_buffer, Sparc.NOP);
    					//push endAND to boolStack. will be used when checking b
    			        andStack.push("endAND" + num_of_and);
    			        num_of_and++;
    			        
    			        //check second operand
    					//load b, if equals to 0, branch to endAND
    			        if(debug) writeDebug("=======in writeBinaryExpr, &&, check second operand=========");
    			        String endAND = andStack.pop();
    			        addToBuffer(text_buffer, b.getAddress());
    			        //addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
    			        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    			        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    			        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BE_OP, endAND);
    			        addToBuffer(text_buffer, Sparc.NOP);
    			        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.L3);
                        decreaseIndent();
                        addToBuffer(text_buffer, endAND + ":\n");
                        increaseIndent();
                        
    					break;
    				case "||":
    					//check first operand
    					//load a, if equals to 0, branch to endAND
    					if(debug) writeDebug("=======in writeBinaryExpr, ||, check first operand=========");
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.L3);
    					addToBuffer(text_buffer, a.getAddress());
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BNE_OP, "endOR" + num_of_or);
    					addToBuffer(text_buffer, Sparc.NOP);
    					//push endAND to boolStack. will be used when checking b
    			        orStack.push("endOR" + num_of_or);
    			        num_of_or++;
    			        
    			        //check second operand
    					//load b, if equals to 0, branch to endAND
    			        if(debug) writeDebug("=======in writeBinaryExpr, ||, check second operand=========");
    			        String endOR = orStack.pop();
    			        addToBuffer(text_buffer, b.getAddress());
    			        //addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
    			        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    			        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    			        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BNE_OP, endOR);
    			        addToBuffer(text_buffer, Sparc.NOP);
    			        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
                        decreaseIndent();
                        addToBuffer(text_buffer, endOR + ":\n");
                        increaseIndent();
    					
    					break;
    				default:
    					break;
    			}
    			if(debug) writeDebug("=======in writeBinaryExpr, do store result=========");
    			// get result's address
    			addToBuffer(text_buffer, result.getAddress());
    			// store result to register
    			if(result.getType() instanceof FloatType)
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    			else if(opName == "&&" || opName == "||")
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L3, "[" + Sparc.L0 + "]");
    			else	
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    		//non comparisonOp
    		else
    		{
    			if(debug) writeDebug("=======in writeBinaryExpr, non comparsionOP=========");
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
    			if(aType instanceof FloatType || bType instanceof FloatType)
    			{
    				if(debug) writeDebug("=======in writeBinaryExpr, compare two operands=========");
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FCMPS, Sparc.F0, Sparc.F1);
    				switch (opName)
    				{
	    				case ">":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.FBLE_OP, "compOp" + num_of_comp);
	    					break;
	    				case ">=":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.FBL_OP, "compOp" + num_of_comp);
	    					break;
	    				case "<":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.FBGE_OP, "compOp" + num_of_comp);
	    					break;
	    				case "<=":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.FBG_OP, "compOp" + num_of_comp);
	    					break;
	    				case "==":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.FBNE_OP, "compOp" + num_of_comp);
	    					break;
	    				case "!=":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.FBE_OP, "compOp" + num_of_comp);
	    					break;
	    				default:
	    					break;
	    				}			
    			}
    			else
    			{
    				if(debug) writeDebug("=======in writeBinaryExpr, compare two operands=========");
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.L2);
    				switch (opName)
    				{
	    				case ">":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BLE_OP, "compOp" + num_of_comp);
	    					break;
	    				case ">=":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BL_OP, "compOp" + num_of_comp);
	    					break;
	    				case "<":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BGE_OP, "compOp" + num_of_comp);
	    					break;
	    				case "<=":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BG_OP, "compOp" + num_of_comp);
	    					break;
	    				case "==":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BNE_OP, "compOp" + num_of_comp);
	    					break;
	    				case "!=":
	    					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BE_OP, "compOp" + num_of_comp);
	    					break;
	    				default:
	    					break;
	    				}
    			}
    			//
    			addToBuffer(text_buffer, Sparc.NOP);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.L3);
                decreaseIndent();
                addToBuffer(text_buffer, "compOp" + num_of_comp +":\n");
                increaseIndent();
                addToBuffer(text_buffer, result.getAddress());
                addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L3, "[" + Sparc.L0 + "]");
                num_of_comp++;
    		}
    	}
    	localReg = 0;
    	floatReg = 0;
    }
    
    void writeUnaryExpr(STO sto, String o, STO result)
    {
    	if(debug) writeDebug("-------writeUnaryExpr: " + sto.getName() + " " + o);
    	Type stoType = sto.getType();
    	Type rType = result.getType();
    	
    	//const folding
    	if(result instanceof ConstSTO)
    	{
    		if(rType instanceof IntType)
    		{
    			int value = ((ConstSTO) result).getIntValue ();
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
                    Integer.toString(value), Sparc.L1);
    			localReg = 1;
    			addToBuffer(text_buffer, result.getAddress());
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    		else if (rType instanceof FloatType)
			{
				float value = ((ConstSTO) result).getFloatValue();
				has_rodata = true;
				decreaseIndent();
				addToBuffer(rodata_buffer, Sparc.VAR_LABEL, "temp" + num_of_temp, "single",
						"0r" + Float.toString(value));
				increaseIndent();
				//get float value
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "temp" + num_of_temp, Sparc.L0);
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F0);
				floatReg = 1;
				//get var address
				addToBuffer(text_buffer, result.getAddress());
				//assign
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
				num_of_temp++;
			}
    		else if(rType instanceof BoolType)
    		{
    			int value = ((ConstSTO) result).getIntValue ();
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
                    Integer.toString(value), Sparc.L1);
    			addToBuffer(text_buffer, result.getAddress());
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    	}
    	//not const folding
		else
		{
			
			// 1. computation
			if (debug)
				writeDebug("=======in writeUnaryExpr, non-const folding, computation=========");
			switch (o)
			{
				case "+":
					// 0. get sto's value
					getValue(sto);
					break;
				case "-":
					// 0. get sto's value
					getValue(sto);
					if (result.getType().isFloatType())
					{
						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FNEGS_OP,
								Sparc.F0, Sparc.F0);
					} else
						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.NEG_OP,
								Sparc.L1, Sparc.L1);
					break;
				case "!":
					// 0. get sto's value
					getValue(sto);
					addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.XOR_OP,
							Sparc.L1, "1", Sparc.L1);
					break;
				default:
					break;
			}
			// 2. get address
			if (debug)
				writeDebug("=======in writeUnaryExpr, non-const folding, computation=========");
			addToBuffer(text_buffer, result.getAddress());
			// 3. store value
			if (debug)
				writeDebug("=======in writeUnaryExpr, non-const folding, store value=========");
			if (result.getType().isFloatType())
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
			else
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
		}
    }
    
    void writeAssignExpr(STO var, STO expr)
    {
    	if(debug) writeDebug("----------in writeAssignExpr: " + var.getName() + "  =  " + expr.getName());
    	Type varType = var.getType();
    	Type exprType = expr.getType();
    	if(varType instanceof FloatType)
    	{
    		//load expr value
    		getValue(expr);
    		//get var address
    		addToBuffer(text_buffer, var.getAddress());
    		//store expr value (%f0) to var address [%l0]
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]" ); 
    	}
    	else
    	{
    		//load expr value
    		getValue(expr);
    		//get var address
    		addToBuffer(text_buffer, var.getAddress());
    		//store expr value (%f0) to var address [%l0]
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]" ); 
    	}
    }
    
    void writeIf(STO sto)
    {
    	if(debug) writeDebug("------------in writeIf------------");
    	
    	String endIfLabel = ".endIf" + num_of_if;
    	if(sto.isConst())
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(((ConstSTO)sto).getIntValue()), Sparc.L1 ); 
    		
    	}
    	else
    	{
    		addToBuffer(text_buffer, sto.getAddress());
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    	}
    	//compare %l0 with 0
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BE_OP, Sparc.ELSE + num_of_if);
    	addToBuffer(text_buffer, Sparc.NOP);
    	
    	//add label to ifStack
    	ifStack.push(endIfLabel);
    	ifStack.push(Sparc.ELSE + num_of_if);
    	num_of_if++;
    }
    
    void writeElse()
    {
    	if(debug) writeDebug("---------writeElse---------");
        String label = ifStack.pop();
        addToBuffer(text_buffer, Sparc.ONE_PARAM, "ba", ifStack.peek());
        addToBuffer(text_buffer, Sparc.NOP);
        decreaseIndent();
        addToBuffer(text_buffer, label+":\n");
        increaseIndent();
    }
    
    void writeWhile(STO sto)
    {
    	
    }
    
    STO writePre(STO sto, Operator o, STO result)
    {
    	if(debug) writeDebug("----------in writePre: " + sto.getName());
    	Type stoType = sto.getType();
    	//1. load value in sto to %l1 or %f1
    	if(debug) writeDebug("=======in writePre, step 1: load value to local1");
    	getValue(sto);
    	
    	if(debug) writeDebug("=======in writePre, step 2: computation ");
    	//2. computation, add one or sub one
    	if(stoType instanceof IntType)
    	{
    		if(o.getName() == "++")
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L2, "1", Sparc.L1);
    			}
    			else 
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L1, "1", Sparc.L1);
    			}
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L1, "1", Sparc.L1);
    		}
    		//3. store value in its address
    		if(debug) writeDebug("=======in writePre, step 3: store value ");
    		addToBuffer(text_buffer, sto.getAddress());
    		if(localReg == 0)
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L2, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    	}
    	else if(stoType instanceof FloatType)
    	{
    		//1. load float_one to %l0
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".float_one", Sparc.L0);
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F2);
    		//2. computation
    		if(o.getName() == "++")
    		{
    			if(floatReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F1, Sparc.F2, Sparc.F0);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F0, Sparc.F2, Sparc.F0);
    			}
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FSUBS_OP, Sparc.F0, Sparc.F2, Sparc.F0);
    		}
    		//3. store value in its address
    		addToBuffer(text_buffer, sto.getAddress());
    		if(floatReg == 0)
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    		}
    	}
    	return sto;
    }
    
    STO writePost(STO sto, Operator o, STO result)
    {
    	if(debug) writeDebug("----------writePost: " + sto.getName());
    	Type stoType = sto.getType();
    	//1. load value in sto to %l1 or %f1
    	if(debug) writeDebug("=======in writePost, step 1: load value to local1");
    	getValue(sto);
    	
    	//1.5 store original value to result
    	if(debug) writeDebug("=======in writePost, step 1.5: store original value ");
    	addToBuffer(text_buffer, result.getAddress());
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    	
    	if(debug) writeDebug("=======in writePost, step 2: computation ");
    	//2. computation, add one or sub one
    	if(stoType instanceof IntType)
    	{
    		if(o.getName() == "++")
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L2, "1", Sparc.L3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L1, "1", Sparc.L3);
    			}
    		}
    		else
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L2, "1", Sparc.L3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L1, "1", Sparc.L3);
    			}
    		}
    		//3. store value in its address
    		if(debug) writeDebug("=======in writePost, step 3: store value ");
    		addToBuffer(text_buffer, sto.getAddress());
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L3, "[" + Sparc.L0 + "]");
    	}
    	else if(stoType instanceof FloatType)
    	{
    		//1. load float_one to %f2
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".float_one", Sparc.L0);
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F2);
    		
    		//1.5 store original value to result
        	if(debug) writeDebug("=======in writePost, step 1.5: store original value ");
        	addToBuffer(text_buffer, result.getAddress());
        	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
        	
    		//2. computation
    		if(o.getName() == "++")
    		{
    			if(floatReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F1, Sparc.F2, Sparc.F3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F0, Sparc.F2, Sparc.F3);
    			}
    		}
    		else
    		{
    			if(floatReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F1, Sparc.F2, Sparc.F3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F0, Sparc.F2, Sparc.F3);
    			}
    		}
    		//3. store value in its address
    		addToBuffer(text_buffer, sto.getAddress());
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F3, "[" + Sparc.L0 + "]");
    	}
    	return result;
    }
    
    public void writeCloseBlock (boolean ifOrWhile)
    {
        if(debug) writeDebug("----------in writeCloseBlock-----------");
        decreaseIndent();
        if (ifOrWhile)
        	addToBuffer(text_buffer, ifStack.pop()+":\n");
        else
        {
            //addToBuffer(text_buffer, whileLabels.pop()+":\n");
        }
        increaseIndent();
    }
    
    public void writeFuncDec(String id) {
        if(debug) writeDebug("in writeFuncDec");
        has_text = true;

        // ! --main--
        decreaseIndent();
        addToBuffer(text_buffer, Sparc.FUNC_COMMENT, id);
        increaseIndent();

        // .align 4
        // .global main
        addToBuffer(text_buffer, Sparc.ALIGN_DIR, "4");
        addToBuffer(text_buffer, Sparc.GLOBAL_VAR, id);
        decreaseIndent();
        
        // main:
        addToBuffer(text_buffer, Sparc.FUNC_LABEL, id);
        increaseIndent();

        // set Save.main, %g1
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
            String.format(Sparc.SAVE_DOT_FUNCNAME, id), Sparc.G1);
        // save %sp, %g1, %sp
        addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SAVE_OP, Sparc.SP, Sparc.G1, Sparc.SP);

        decreaseIndent();
        addToBuffer(text_buffer, Sparc.NEW_LINE);
        increaseIndent();

        this.currFuncName = id;
    }
    
    // used in doReturnStmt. 
	public void writeReturnStmt(STO returnExpr, Type funcReturnType,
			boolean byRef)
	{
		if (debug)
			writeDebug("--------in writeReturnStmt---------");
		if (returnExpr != null)
		{
			if (returnExpr instanceof ConstSTO)
			{
				if (returnExpr.getType() instanceof FloatType)
				{
					has_rodata = true;
					float value = ((ConstSTO) returnExpr).getFloatValue();
					decreaseIndent();
					// set temp as rodata with value of return expr
					addToBuffer(rodata_buffer, Sparc.VAR_LABEL, "temp"
							+ num_of_temp, "single",
							"0r" + Float.toString(value));
					increaseIndent();
					// load return value to %f0
					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "temp"
							+ num_of_temp++, Sparc.L0);
					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "["
							+ Sparc.L0 + "]", Sparc.F0);
				} 
				// return expr is int or bool
				else
				{
					// set return value to %i0
					if (funcReturnType instanceof IntType
							|| funcReturnType instanceof FloatType)
						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
								Integer.toString(((ConstSTO) returnExpr)
										.getIntValue()), Sparc.I0);
					// load return value to %f0
					else if (funcReturnType instanceof FloatType)
					{
						float value = ((ConstSTO) returnExpr).getFloatValue();
						has_rodata = true;
						decreaseIndent();
						addToBuffer(rodata_buffer, Sparc.VAR_LABEL, "tmp"
								+ num_of_temp, "single",
								"0r" + Float.toString(value));
						increaseIndent();
						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
								"temp" + num_of_temp++, Sparc.L0);
						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "["
								+ Sparc.L0 + "]", Sparc.F0);
					}
				}

			} 
			//return expr not const
			else
			{

			}
		}
		//write ret and restore to assembly
		addToBuffer(text_buffer, Sparc.RET);
		addToBuffer(text_buffer, Sparc.RESTORE);
	}
	
	// in DoWriteExitStmt. 
	public void writeExitStmt(STO stmt)
	{
		if (debug)
			writeDebug("---------writeExitStmt------------");
		if (stmt instanceof ConstSTO)
		{
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
					Integer.toString(((ConstSTO) stmt).getIntValue()), Sparc.O0);
		}
		else
		{
			addToBuffer(text_buffer, stmt.getAddress());
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0
					+ "]", Sparc.O0);
		}
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, "exit");
		addToBuffer(text_buffer, Sparc.NOP);
	}
    
    public void writeFuncClose(String id, int offset, Type returnType) {
        if(debug) writeDebug("--------------in writeFuncClose--------------");
        if (returnType instanceof VoidType) {
	        addToBuffer(text_buffer, Sparc.RET);
	        addToBuffer(text_buffer, Sparc.RESTORE);
	    }

        decreaseIndent();
        addToBuffer(text_buffer, Sparc.NEW_LINE);
        addToBuffer(text_buffer, Sparc.SEPARATOR);
        // SAVE.main = -(92 + 8) & -8
        addToBuffer(text_buffer, String.format(Sparc.SAVE_DOT_FUNCNAME, currFuncName) + Sparc.SAVE_FUNC,
         Integer.toString(Math.abs(offset)));
        addToBuffer(text_buffer, Sparc.NEW_LINE);
        increaseIndent();
        localReg = 0;
        floatReg = 0;
    }
    
    void writeCin(STO sto)
    {
    	Type t = sto.getType();
    	if(debug) writeDebug("----------in writeCin-------------");
        if (t instanceof FloatType)
        {
        	// call inputFloat
        	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, "inputFloat");
        	addToBuffer(text_buffer, Sparc.NOP);
          	addToBuffer(text_buffer, sto.getAddress());

            addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
        }
        else if (t instanceof IntType)
        {
        	// call inputInt
        	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, "inputInt");
        	addToBuffer(text_buffer, Sparc.NOP);
           	addToBuffer(text_buffer, sto.getAddress());

            addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        }
    }
    
    
    
    
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		AssemblyCodeGenerator myAsWriter = new AssemblyCodeGenerator("C:\\Users\\David Wei\\git\\Project1\\CSE131_PA1\\src\\rc.s");

		System.out.println("here");
		
        myAsWriter.increaseIndent();
        myAsWriter.writeAssembly(Sparc.TWO_PARAM, Sparc.SET_OP, String.valueOf(4095), "%l0");
        myAsWriter.increaseIndent();
        myAsWriter.writeAssembly(Sparc.TWO_PARAM, Sparc.SET_OP, String.valueOf(1024), "%l1");
        myAsWriter.decreaseIndent();
        
        myAsWriter.writeAssembly(Sparc.TWO_PARAM, Sparc.SET_OP, String.valueOf(512), "%l2");
        
        myAsWriter.decreaseIndent();
        

	} //end of main
*/
	
	
	
	
	
	
	
} //end of code gen
