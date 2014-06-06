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
	private int num_of_while = 0;
	private int num_of_ptr = 0;
	private int num_of_array = 0;
	private int num_of_deallocatedStack = 0;
	private int num_of_typecast = 0;
	private int num_of_memoryLeak = 0;
	
	// if localReg = 0, means %l1 is not taken, otherwise if localReg = 1, %l1 is taken, can be used
	private int localReg = 0;
	// if floatReg = 0, means %f0 is not taken, otherwise if floatReg = 1, %f0 is taken, can be used
	private int floatReg = 0;
	// if arrayReg = 0, means %l5 is not taken, otherwise if arrayReg = 1, %l5 is taken, can be used
	private int arrayReg = 0;
	
	//extra credit 1:
	private int lowestOffset = 0;
	
	private Stack<String> ifStack = new Stack<String>();
	private Stack<String> andStack = new Stack<String>();
	private Stack<String> orStack = new Stack<String>();
	private Stack<String> compStack = new Stack<String>();
	private Stack<String> whileStack = new Stack<String>();
	
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
    public void writeGlobalVariable(String id, boolean init, STO sto, Type t, boolean isStatic )
    {
    	if(debug) writeDebug("---------In writeGLobalVariable--------------");
    	
    	// add .global to file
    	if(!isStatic)
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
    
    //P2
	public void writeGlobalStruct(String id, boolean init, STO sto, Type t,
			boolean isStatic)
	{
		if (debug)
			writeDebug("--------in writeGlobalStruct--------");
		has_bss = true;
		if (!isStatic)
			addToBuffer(bss_buffer, Sparc.GLOBAL_VAR, id);
		decreaseIndent();
		if (!init)
		{
			addToBuffer(bss_buffer, Sparc.BSS_VAR, id,
					Integer.toString(t.getSize()));
		}
		increaseIndent();
		addToBuffer(bss_buffer, Sparc.NEW_LINE);
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
 		
 		//get left hand side variable address
 		addToBuffer(text_buffer, var.getAddress());

 		Type varType = var.getType();
 		Type exprType = expr.getType();
 		if (varType instanceof FloatType/* && exprType instanceof FloatType*/)
 		{
 			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "["
 					+ Sparc.L0 + "]");
 		} 
 		else if(varType instanceof StructType && exprType instanceof StructType)
    	{
    		if(debug) writeDebug("=======in writeLocalVariableWInit, struct assign=======");
    		
    		//1. get var address, store in out0
    		if(debug) writeDebug("=======in writeLocalVariableWInit, get var address, store in out0=======");
    		getAddressHelper(var);
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.O0);
    		
    		//2. get expr address, store in out1
    		if(debug) writeDebug("=======in writeLocalVariableWInit, get expr address, store in out1=======");
    		getAddressHelper(expr);
    		//if expr is pass-by-ref
    		if(expr.isVar() && ((VarSTO)expr).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.O1);
    		
    		//3. get exprType.size, store in out2
    		if(debug) writeDebug("=======in writeLocalVariableWInit, get exprType.size, store in out2=======");
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(exprType.getSize()), Sparc.O2);
    		
    		//4. call memmove
    		if(debug) writeDebug("=======in writeLocalVariableWInit, call memmove=======");
    		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, "memmove, 0");
    		addToBuffer(text_buffer, Sparc.NOP);
    	}
 		else
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
		
		if (debug)
			writeDebug("------end of writeConstantLiteral-------");
	}
	
	// used in DoVarDecl, array part
	public void writeGlobalArray(STO sto, Boolean isStatic)
	{
		Type t = sto.getType();
		int size = t.getSize();
		if(debug) writeDebug("----------in writeGlobalArray: " + sto.getName() + " is array of  " + t.getName());
		has_bss = true;
		if(isStatic)
			addToBuffer(bss_buffer, Sparc.GLOBAL_VAR, sto.getName());
    	decreaseIndent();
    	addToBuffer(bss_buffer, Sparc.BSS_VAR, sto.getName(), Integer.toString(size));
    	increaseIndent();
    	addToBuffer(bss_buffer, Sparc.NEW_LINE);
	}
	
	// used in DoVarDecl, array part
	public void writeStaticArray(STO sto)
	{
		Type t = sto.getType();
		int size = t.getSize();
		if(debug) writeDebug("----------in writeGlobalArray: " + sto.getName() + " is array of  " + t.getName());
		has_bss = true;
	   	decreaseIndent();
	   	addToBuffer(bss_buffer, Sparc.BSS_VAR, sto.getGlobalOffset(), Integer.toString(size));
	   	increaseIndent();
	   	addToBuffer(bss_buffer, Sparc.NEW_LINE);
	}
	
	//P2: phaseIII, do runtime array bounds check
    public void writeRunTimeArrayCheck(STO var, STO index)
    {
    	if(debug) writeDebug("--------in  writeRunTimeArrayCheck: " + var.getName() );
    	
    	int arraySize = ((ArrayType) var.getType()).getArraySize();
    	String arrayUpperBoundLabel = "arrayUpperBoundCheck" + num_of_array;
    	
    	//A: do upper bound check
    	getAddressHelper(index);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(arraySize), Sparc.L1);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L0, Sparc.L1);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BL_OP, arrayUpperBoundLabel);
    	addToBuffer(text_buffer, Sparc.NOP);
    	
    	//printout error message if upper bound is out of range
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".ArrayOutOfBounds", Sparc.O0);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.O1);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(arraySize), Sparc.O2);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
        addToBuffer(text_buffer, Sparc.NOP);
        
        //call exit(1)
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.EXIT);
        addToBuffer(text_buffer, Sparc.NOP);
        
        //printout arrayLabel to skip the print error message part and exit part
        decreaseIndent();
        addToBuffer(text_buffer, arrayUpperBoundLabel + ":\n" );
        increaseIndent();
    	
    	//B: do lower bound check
        String arrayLowerBoundLabel = "arrayLowerBoundCheck" + num_of_array++;
        
        getAddressHelper(index);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L1);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L0, Sparc.L1);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BGE_OP, arrayLowerBoundLabel);
    	addToBuffer(text_buffer, Sparc.NOP);
    	
    	//printout error message if lower bound is out of range
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".ArrayOutOfBounds", Sparc.O0);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.O1);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(arraySize), Sparc.O2);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
        addToBuffer(text_buffer, Sparc.NOP);
        
        //call exit(1)
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.EXIT);
        addToBuffer(text_buffer, Sparc.NOP);
        
        //printout arrayLabel to skip the print error message part and exit part
        decreaseIndent();
        addToBuffer(text_buffer, arrayLowerBoundLabel + ":\n" );
        increaseIndent();
    	
    	if(debug) writeDebug("--------end of writeRunTimeArrayCheck---------");
    }
	
	// used in a lot of place. Basically used when trying to access elements in array
	public void writeArrayAddress(STO sto)
	{
		if(debug) writeDebug("----------in writeArrayAddress: " + sto.getName());
		
		arrayReg++;
		
		//get var and its size
		VariableBox<STO, STO> box = sto.getArray();
		STO var = box.getVariable();
		STO expr = box.getExpr();
		Type stoType = sto.getType();
		//Type varType = var.getType();
		//int index = sto.getType().getSize();
		
		// THIS ORDER IS VERY IMPORTANTQ!!! NEED TO GET L5 FIRST, THEN L4. eg, i[j[0]]
		//2. get value of expr, %l5
		if(debug) writeDebug("=======in writeArrayAddress, get value of index: " + expr.getName());
		// optionA:
		getAddressHelper(expr);
		if(arrayReg%3 == 1)
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L5);
		else if(arrayReg%3 == 2)
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L6);
		else
		{
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L7);
		}
		
		//1. get address of var, store it in %l4
		//if recursive, need to check type of var and call corresponding write**Address methods.
		//so use getAddressHelper()
		if(debug) writeDebug("=======in writeArrayAddress, get address of var :" + var.getName() + 
				" and store in l4");
		getAddressHelper(var);
		if (var.getType().isPointerType() || (var.isVar() && ((VarSTO) var).isRef()))
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.L4);

		// optionB: actually sometimes it doesnt work. since a VarSTO expr doesn't have a value at compile time
		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(index), Sparc.L5);
		
		//3. if basicType, %l5 * 4 to get the scaled offset, %l5
		if(debug) writeDebug("=======in writeArrayAddress, scale the offset");
		
		if(stoType.isBasicType() || stoType.isPointerType())
		{
			if(arrayReg%3 == 1)
				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SLL_OP, Sparc.L5, "2", Sparc.L5);
			else if(arrayReg%3 == 2)
				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SLL_OP, Sparc.L6, "2", Sparc.L6);
			else
			{
				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SLL_OP, Sparc.L7, "2", Sparc.L7);
			}
		}
		//TODO:
		else
		{
			//if array elts are struct, or array or etc, need to do index * sizeof(object)
			if(arrayReg%3 == 1)
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L5, Sparc.O0);
			else if(arrayReg%3 == 2)
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L6, Sparc.O0);
			else
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L7, Sparc.O0);
			
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(stoType.getSize()), Sparc.O1);
			addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, ".mul");
			addToBuffer(text_buffer, Sparc.NOP);
			
			if(arrayReg%3 == 1)
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.O0, Sparc.L5);
			else if(arrayReg%3 == 2)
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.O0, Sparc.L6);
			else
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.O0, Sparc.L7);
		}
	
		//4. base + offset, add %l4 and %l5 to get the result address 
		if(debug) writeDebug("=======in writeArrayAddress, base + offset");
		
		if(arrayReg%3 == 1)
			addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L4, Sparc.L5, Sparc.L0);
		else if(arrayReg%3 == 2)
			addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L4, Sparc.L6, Sparc.L0);
		else
			addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L4, Sparc.L7, Sparc.L0);
		
		arrayReg--;
		
		if(debug) writeDebug("---------end of writeArrayAddress--------");
	}
	
	// used to get struct address
	void writeStructAddress(STO sto)
    {
    	if(debug) writeDebug("-------in writeStructAddress: " + sto.getName());
    	//need to do recursive check. since a struct field can be of type struct, array, pointer...
    	if(sto.getStruct().getIsArray())
    	{
    		writeArrayAddress(sto.getStruct());
    	}
    	else if(sto.getStruct().getIsStructField())
    	{
    		writeStructAddress(sto.getStruct());
    	}
    	else if(sto.getStruct().getIsDeref())
    	{
    		writeDerefAddress(sto.getStruct());
    	}
    	else if(sto.getStruct().getType().isPointerType() || (sto.getStruct().isVar() && ((VarSTO)sto.getStruct()).isRef() ))
    	{
    		if(debug) writeDebug("=======in writeStructAddress: " + sto.getStruct().getName() + " is a ptr or byRef========");

            if (sto.getStruct().getIsStructField())
            	writeStructAddress (sto.getStruct());
            else
    		  addToBuffer(text_buffer, sto.getStruct().getAddress());
            //if the param passed in is both a pointer and and by ref
            if(sto.getStruct().getType().isPointerType() && (sto.getStruct().isVar() && ((VarSTO)sto.getStruct()).isRef())) 
            {
            	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
            }
            		
            addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    	}
    	//basicType
    	else
    	{
    		//get struct's base offset
    		addToBuffer(text_buffer, sto.getStruct().getAddress());
    	}
    	//add offset to base offset
    	addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L0, Integer.toString(sto.getFieldOffset()), Sparc.L0 );
    }
	
	//used in getAddressHelper(). to get deref sto address
	void writeDerefAddress(STO sto)
	{
		if(debug) writeDebug("-------in writeDerefAddress: " + sto.getName());
		
		//get pointer stored in param
		STO ptr = sto.getPointer();
		
		//1. get address of this pointer 
		getAddressHelper(ptr);
		//check byRef
		if(ptr.isVar() && ((VarSTO)ptr).isRef())
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
		
		//2. load the address of the real var from [%l0] to %l0
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
		
		if(debug) writeDebug("======in writeDerefAddress, check nullPtrExcep=======");
		String ptrLabel = "ptrLabel" + num_of_ptr++;
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L4);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L0, Sparc.L4);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BNE_OP, ptrLabel);
		addToBuffer(text_buffer, Sparc.NOP);

		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".NullPtrException", Sparc.O0);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
		addToBuffer(text_buffer, Sparc.NOP);

		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.EXIT);
        addToBuffer(text_buffer, Sparc.NOP);

        decreaseIndent();
        addToBuffer(text_buffer, ptrLabel + ":\n");
        increaseIndent();
        if(debug) writeDebug("======end of check nullPtrExcep=======");
		
		if(debug) writeDebug("-------end of writeDerefAddress-------");
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
    	
    	if(sto == null )
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.ENDL, Sparc.O0 );
    		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
    		addToBuffer(text_buffer, Sparc.NOP);
    		return;
    	}
    	
    	// check for function return void
    	if( (sto.getType() != null) && sto.getType().isVoidType())
    	{
/*    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.ENDL, Sparc.O0 );
    		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
    		addToBuffer(text_buffer, Sparc.NOP);*/
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
    	
	    
	    if(t instanceof FloatType)
	    {
	    	// call printFloat
	    	//TODO: call fitos in case we did (float*)int
	    	//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FITOS_OP, Sparc.F0, Sparc.F0);
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
	    else
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
		
		//get basetype of sto
		Type t = sto.getType();
		
		if(t instanceof FloatType)
		{
			// set	-4, %l0
			// add	%fp, %l0, %l0
			// not sure the type of sto, use getAddressHelper to get address of sto
			getAddressHelper(sto);

			//if passed by ref, load value in address
			if(sto.isVar() && ((VarSTO)sto).isRef())
			{
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
			}
			
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
			// not sure the type of sto, use getAddressHelper to get address of sto
			getAddressHelper(sto);
			
			//if passed by ref, load value in address
			if(sto.isVar() && ((VarSTO)sto).isRef())
			{
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
			}
			
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
		// TODO: for array type, only get the address
		else if(t.isArrayType())
		{
			getAddressHelper(sto);
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.L1);
		}
		// for pointer
		else
		{
			getAddressHelper(sto);
			//if passed by ref, load value in address
			if(sto.isVar() && ((VarSTO)sto).isRef())
			{
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
			}
			
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
		if(debug) writeDebug("-------end of getValue------------");
    }
    
    //helper method. used in various methods.
    //get address of stos, when the sto is not only a basicType. 
    //needs to check the type of sto to call different write**Address method
    void getAddressHelper(STO sto)
    {
    	if(debug) writeDebug("--------in getAddressHelper: " + sto.getName());
    	//if array type, allocate space in stack for it
		if(sto.getIsArray())
		{
			writeArrayAddress(sto);
		}
		else if(sto.getIsDeref())
		{
			writeDerefAddress(sto);
		}
		else if(sto.getIsStructField())
		{
			writeStructAddress(sto);
		}
		else
		{
			addToBuffer(text_buffer, sto.getAddress());
		}
		if(debug) writeDebug("--------end of getAddressHelper------------ ");
    }
    
    //used when need to convert int to float value. value is stored in either F0 or F1
    void intToFloat(STO sto)
    {
    	if(debug) writeDebug("---------in intToFloat: " + sto.getName() + " " + ((sto instanceof ConstSTO) ? ((ConstSTO)sto).getIntValue() : null) );
    	Type t = new FloatType(sto.getName(), 4);
    	
    	//1. get address
    	if(debug) writeDebug("=======in intToFloat: getAddress of " + sto.getName());
    	getAddressHelper(sto);
    	
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
    	if(debug) writeDebug("---------end of intToFloat---------");
    }
    
  //used when need to convert int to float value. value is stored in either F0 or F1
    void FloatToInt(STO sto)
    {
    	if(debug) writeDebug("---------in FloatToInt: " + sto.getName() + " " + ((sto instanceof ConstSTO) ? ((ConstSTO)sto).getIntValue() : null) );
    	
    	//1. get address
    	if(debug) writeDebug("=======in FloatToInt: getAddress of " + sto.getName());
    	getAddressHelper(sto);
    	
    	//2. load value to float register
    	if(debug) writeDebug("=======in FloatToInt: load value of " + sto.getName());
    	if(floatReg == 0)
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F0);
    		
    		//3. call intofloat
    		if(debug) writeDebug("=======in FloatToInt: call itos " + sto.getName());
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FSTOI_OP, Sparc.F0, Sparc.F0);
    		floatReg = 1;
    	}
    	else
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F1);
    		
    		//3. call intofloat
    		if(debug) writeDebug("=======in FloatToInt: call itos " + sto.getName());
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FSTOI_OP, Sparc.F1, Sparc.F1);
    		floatReg = 0;
    	}
    	if(debug) writeDebug("---------end of FloatToInt---------");
    }
    
    void writeOr(STO a)
    {
    	//check first operand
		//load a, if equals to 0, branch to endAND
		if(debug) writeDebug("--------in writeOr, ||, check first operand--------");
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.L3);
		getAddressHelper(a);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BNE_OP, "endOR" + num_of_or);
		addToBuffer(text_buffer, Sparc.NOP);
		//push endAND to boolStack. will be used when checking b
        orStack.push("endOR" + num_of_or);
        num_of_or++;
    }
    
    void writeAnd(STO a)
    {
    	//check first operand
		//load a, if equals to 0, branch to endAND
		if(debug) writeDebug("--------in writeAnd, &&, check first operand--------");
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
		getAddressHelper(a);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BE_OP, "endAND" + num_of_and);
		addToBuffer(text_buffer, Sparc.NOP);
		//push endAND to boolStack. will be used when checking b
        andStack.push("endAND" + num_of_and);
        num_of_and++;
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
    			if(o.getName().equals("&&")) 
    			{
    				String endAND = andStack.pop();
    				decreaseIndent();
                    addToBuffer(text_buffer, endAND + ":\n");
                    increaseIndent();
    				
    			}
    			else if ( o.getName().equals("||"))
    			{
    				String endOR = orStack.pop();
    				decreaseIndent();
                    addToBuffer(text_buffer, endOR + ":\n");
                    increaseIndent();
    			}
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
    						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L1, Sparc.O0);
        					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L2, Sparc.O1);
        					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.MUL);
        					addToBuffer(text_buffer, Sparc.NOP);
        					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.O0, Sparc.L1);
    					}
    					break;
    				case "/":
    					if(aType.isFloatType() || bType.isFloatType())
    					{
    						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FDIVS_OP, Sparc.F0, Sparc.F1, Sparc.F0);
    					}
    					else
    					{
    						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L1, Sparc.O0);
        					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L2, Sparc.O1);
        					addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.DIV);
        					addToBuffer(text_buffer, Sparc.NOP);
        					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.O0, Sparc.L1);
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
    			        //check second operand
    					//load b, if equals to 0, branch to endAND
    			        if(debug) writeDebug("=======in writeBinaryExpr, ||, check second operand=========");
    			        String endOR = orStack.pop();
    			        addToBuffer(text_buffer, b.getAddress());
    			        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.L3);
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
					if(localReg == 1)
					{
						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.XOR_OP,
							Sparc.L1, "1", Sparc.L1);
					}
					else
					{
						addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.XOR_OP,
								Sparc.L2, "1", Sparc.L2);
					}
					break;
				case "++":
					if (debug)
						writeDebug("=======in writeUnaryExpr, non-const folding, op is ++, do nothing=========");
					return;
				case "--":
					if (debug)
						writeDebug("=======in writeUnaryExpr, non-const folding, op is --, do nothing=========");
					return;
				default:
					break;
			}
			// 2. get address
			if (debug) writeDebug("=======in writeUnaryExpr, result get address=========");
			addToBuffer(text_buffer, result.getAddress());
			if (result instanceof VarSTO && ((VarSTO)result).isRef())
    		{
    			//load value stored in its address, which is the addresss of the real variable
            	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
			// 3. store value
			if (debug)
				writeDebug("=======in writeUnaryExpr, non-const folding, store value=========");
			if (result.getType().isFloatType())
				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
			else
			{
				if(localReg == 1)
					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
				else
					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L2, "[" + Sparc.L0 + "]");
			}
		}
    	//resetReg();
    	if(debug) writeDebug("---------end of writeUnary----------");
    }
    
    void writeAssignExpr(STO var, STO expr)
    {
    	if(debug) writeDebug("----------in writeAssignExpr: " + var.getName() + "  =  " + expr.getName());
    	resetReg();
    	Type varType = var.getType();
    	Type exprType = expr.getType();
    	
    	//assign to float
    	if(varType instanceof FloatType)
    	{
    		if(debug) writeDebug("=======in writeAssignExpr, varType is float=======");
    		
    		
    		if(exprType.isIntType())
    		{
    			//need to convert expr register to f0 if trying to assign int to float
        		if(debug) writeDebug("=======in writeAssignExpr, convert right side int to float=======");
    			intToFloat(expr);
    		}
    		//load expr value
    		getValue(expr);
    		//get var address
    		getAddressHelper(var);
    		
    		//check if var is pass-by-reference
    		if (var instanceof VarSTO && ((VarSTO)var).isRef())
    		{
    			//load value stored in its address, which is the addresss of the real variable
            	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		//store expr value (%f0) to var address [%l0]
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]" ); 
    	}
    	//struct assignment
    	else if(varType instanceof StructType && exprType instanceof StructType)
    	{
    		if(debug) writeDebug("=======in writeAssignExpr, struct assign=======");
    		
    		//1. get var address, store in out0
    		if(debug) writeDebug("=======in writeAssignExpr, get var address, store in out0=======");
    		getAddressHelper(var);
    		//if var is pass-by-ref
    		if(var.isVar() && ((VarSTO)var).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		//IMPORTANT! because later we need %o0 to store right side computation result, we store to %o3 temporarily.
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.L6);
    		
    		//2. get expr address, store in out1
    		if(debug) writeDebug("=======in writeAssignExpr, get expr address, store in out1=======");
    		getAddressHelper(expr);
    		//if expr is pass-by-ref
    		if(expr.isVar() && ((VarSTO)expr).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		
    		//get addresss of var
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L6, Sparc.O0);
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.O1);
    		
    		//3. get exprType.size, store in out2
    		if(debug) writeDebug("=======in writeAssignExpr, get exprType.size, store in out2=======");
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(exprType.getSize()), Sparc.O2);
    		
    		//4. call memmove
    		if(debug) writeDebug("=======in writeAssignExpr, call memmove=======");
    		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, "memmove, 0");
    		addToBuffer(text_buffer, Sparc.NOP);
    	}
    	//int, bool assign
    	else
    	{
    		//1. load expr value
    		getValue(expr);
    		
    		//2. get var address
    		//if array type, allocate space in stack for it
    		getAddressHelper(var);
    		
    		//check if var is pass-by-reference
    		if (var instanceof VarSTO && ((VarSTO)var).isRef())
    		{
    			//load value stored in its address, which is the addresss of the real variable
            	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		//3. store expr value (%f0) to var address [%l0]
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]" ); 
    	}
    	if(debug) writeDebug("----------end of writeAssignExpr--------");
    }
    
    void writeIf(STO sto)
    {
    	if(debug) writeDebug("------------in writeIf: " + ((sto.getName() != null) ? sto.getName() : null) );
    	
    	String endIfLabel = ".endIf" + num_of_if;
    	if(sto.isConst())
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(((ConstSTO)sto).getIntValue()), Sparc.L1 ); 
    		
    	}
    	else
    	{
    		getAddressHelper(sto);
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    	}
    	//compare %l1 with 0
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
    	if(debug) writeDebug("---------in writeElse---------");
        String label = ifStack.pop();
        addToBuffer(text_buffer, Sparc.ONE_PARAM, "ba", ifStack.peek());
        addToBuffer(text_buffer, Sparc.NOP);
        decreaseIndent();
        addToBuffer(text_buffer, label+":\n");
        increaseIndent();
    }
    
    //called in IncWhile() in MyParser
    //insert whileStart label at the beginning of while loop
    //in case to get the latest while expr value
    void writeWhileStart()
    {
    	if(debug) writeDebug("-------in writeWhileStart---------");
    	decreaseIndent();
    	String whileLabel = "whileStart" + ++num_of_while + ":\n";
    	addToBuffer(text_buffer, whileLabel);
    	increaseIndent();
    	//num_of_while++;
    }
    
    //called in OutWhile() in MyParser
    //write whileEnd label at the end of while loop
    void writeWhileEnd()
    {
    	if(debug) writeDebug("-------in writeWhileEnd------------");
    	// branch always to whileStart
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BA_OP, whileStack.pop() );
    	addToBuffer(text_buffer, Sparc.NOP);
    }
    
    void writeWhile(STO sto)
    {
    	if(debug) writeDebug("---------in writeWhile: " + ((sto.getName() != null) ? sto.getName() : null) );
    	
    	if(sto.isConst())
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(((ConstSTO)sto).getIntValue()), Sparc.L1 ); 
    		
    	}
    	else
    	{
    		getAddressHelper(sto);
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    	}
    	//compare %l1 with 0
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BE_OP, "whileEnd" + num_of_while);
    	addToBuffer(text_buffer, Sparc.NOP);
    	
    	//add label to whileStack
    	whileStack.push("whileEnd" + num_of_while);
    	whileStack.push("whileStart" + num_of_while);
    	
    }
    
    public void writeCloseBlock (boolean ifOrWhile)
    {
        if(debug) writeDebug("----------in writeCloseBlock-----------");
        decreaseIndent();
        if (ifOrWhile)
        	addToBuffer(text_buffer, ifStack.pop()+":\n");
        else
        {
            addToBuffer(text_buffer, whileStack.pop()+":\n");
        }
        increaseIndent();
    }
    
    void writeBreak()
    {
    	if(debug) writeDebug("---------in writeBreak----------");
    	String temp = whileStack.pop();
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BA_OP, whileStack.peek());
    	addToBuffer(text_buffer, Sparc.NOP);
    	whileStack.push(temp);
    }
    
    void writeContinue()
    {
    	if(debug) writeDebug("---------in writeContinue----------");
    	//get the latest whileStart in whileStack
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BA_OP, whileStack.peek());
    	addToBuffer(text_buffer, Sparc.NOP);
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
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L2, "1", Sparc.L2);
    			}
    			else 
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L1, "1", Sparc.L1);
    			}
    		}
    		else
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L2, "1", Sparc.L2);
    			}
    			else 
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L1, "1", Sparc.L1);
    			}
    		}
    		//3. store value in its address
    		if(debug) writeDebug("=======in writePre, step 3: store value ");
    		getAddressHelper(sto);
    		if(sto.isVar() && ((VarSTO)sto).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
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
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F1, Sparc.F2, Sparc.F1);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FADDS_OP, Sparc.F0, Sparc.F2, Sparc.F0);
    			}
    		}
    		else
    		{
    			if(floatReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FSUBS_OP, Sparc.F1, Sparc.F2, Sparc.F1);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FSUBS_OP, Sparc.F0, Sparc.F2, Sparc.F0);
    			}
    		}
    		//3. store value in its address
    		getAddressHelper(sto);
    		if(sto.isVar() && ((VarSTO)sto).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		if(floatReg == 0)
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    		}
    	}
    	//phaseIII, ptr pre/post inc/dec
    	else if(stoType instanceof PointerType)
    	{
    		Type baseType = ((PointerType) stoType).getBaseType();
    		if(o.getName() == "++")
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L2, Integer.toString(baseType.getSize()), Sparc.L2);
    			}
    			else 
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L1, Integer.toString(baseType.getSize()), Sparc.L1);
    			}
    		}
    		else
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L2, Integer.toString(baseType.getSize()), Sparc.L2);
    			}
    			else 
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L1, Integer.toString(baseType.getSize()), Sparc.L1);
    			}
    		}
    		//3. store value in its address
    		if(debug) writeDebug("=======in writePre, step 3: store value ");
    		getAddressHelper(sto);
    		if(sto.isVar() && ((VarSTO)sto).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		if(localReg == 0)
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L2, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
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
    		
    	//2. computation, add one or sub one
    	if(stoType instanceof IntType)
    	{
    		//1.5 store original value to result
        	if(debug) writeDebug("=======in writePost, step 1.5: store original value ");
        	addToBuffer(text_buffer, result.getAddress());

    	    if(localReg == 0)
    		{
    	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L2, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    		
    	    if(debug) writeDebug("=======in writePost, step 2: computation ");
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
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L2, "1", Sparc.L3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L1, "1", Sparc.L3);
    			}
    		}
    		//3. store value in its address
    		if(debug) writeDebug("=======in writePost, step 3: store value ");
    		getAddressHelper(sto);
    		if(sto.isVar() && ((VarSTO)sto).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
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
        	if(floatReg == 0)
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
        	else
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
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FSUBS_OP, Sparc.F1, Sparc.F2, Sparc.F3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.FSUBS_OP, Sparc.F0, Sparc.F2, Sparc.F3);
    			}
    		}
    		//3. store value in its address
    		if(debug) writeDebug("=======in writePost, step 3: store value ");
    		getAddressHelper(sto);
    		if(sto.isVar() && ((VarSTO)sto).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F3, "[" + Sparc.L0 + "]");
    	}
    	//phaseIII
    	else if(stoType instanceof PointerType)
    	{
    		Type baseType = ((PointerType) stoType).getBaseType();
    		//1.5 store original value to result
        	if(debug) writeDebug("=======in writePost, step 1.5: store original value ");
        	addToBuffer(text_buffer, result.getAddress());

    	    if(localReg == 0)
    		{
    	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L2, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    		}
    		
    	    if(debug) writeDebug("=======in writePost, step 2: computation ");
    		if(o.getName() == "++")
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L2, Integer.toString(baseType.getSize()), Sparc.L3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.L1, Integer.toString(baseType.getSize()), Sparc.L3);
    			}
    		}
    		else
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L2, Integer.toString(baseType.getSize()), Sparc.L3);
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.SUB_OP, Sparc.L1, Integer.toString(baseType.getSize()), Sparc.L3);
    			}
    		}
    		//3. store value in its address
    		if(debug) writeDebug("=======in writePost, step 3: store value ");
    		getAddressHelper(sto);
    		if(sto.isVar() && ((VarSTO)sto).isRef())
    		{
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		}
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L3, "[" + Sparc.L0 + "]");
    	}
    	return result;
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
    
    //P2: used in DoFormalParam() in MyParser. 
    public void writeParameter(STO sto, int index)
    {
    	if(debug) writeDebug("-------in writeParameter: " + sto.getName() + " param num: " + index);
    	addToBuffer(text_buffer, sto.getAddress());
    	//within available local registers
    	if(index < 6)
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, "%i" + index, "[" + Sparc.L0 + "]");
    }
    
    //P2: used in DoFuncCall(). Pass in parameters into %o0 ~ %o5 before function call 
    public void writePassParameter(STO arg, STO param, boolean byRef, int index, STO temp)
    {
    	if(debug) writeDebug("-------in writePassParameter: " + param.getName());
    	
    	if(byRef)
    	{
    		if(debug) writeDebug("=====in writePassParameter, param " + arg.getName() + " is byRef=======");
    		//get sto address
    		getAddressHelper(arg);
    		if (arg.isVar() && ((VarSTO) arg).isRef() )// || arg.getType().isPointerType())
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    		//store it to %o0
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, "%o" + index);
    	}
    	else
    	{
    		if (param.getType().isFloatType())
    		{
    			//handle pass int to float param
    			if(arg.getType().isIntType())
    			{
    				//resetReg();
    				intToFloat(arg);
    				
    				addToBuffer(text_buffer, temp.getAddress());
    				if(floatReg == 1)
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    				else
    					addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", "%o" + index);
    				
    				
    				/*addToBuffer(text_buffer, arg.getAddress());
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    				resetReg();
    				getValue(arg);
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", "%o"+index);*/
    				return;
    			}
    			//get param value
    			getValue(arg);
    			
    			//attention: for float param, cannot just mov %f0 to %o0, need to st to %l0 and then ld from l0 to %o0
    			if(floatReg == 1)
    			{
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
    			}
    			//store it to %o0
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", "%o"+index);
            }
    		else if(param.getType().isPointerType())
    		{
    			getAddressHelper(arg);
    			if(!(param.isVar() && ((VarSTO)param).isRef()))
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, "%o" + index);
    		}
    		else
    		{
    			getAddressHelper(arg);
    			if (arg.isVar() && ((VarSTO) arg).isRef())
        			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L1, "%o" + index);
    		}
    	}
    	resetReg();
    	if(debug) writeDebug("-------end of writePassParameter--------");
    }
    
    public void writeFuncCall(STO sto, STO retSTO, boolean isRef)
    {
    	if(debug) writeDebug("----------writeFuncCall------------");
    	
    	Type retType = retSTO.getType();
        has_rodata = true;
        
        //call passed in function
        //if function ptr, call address
        if(sto.getType().isFunctionPointerType() && !(sto.isFunc()) )
        {
        	if(sto.isVar() && ((VarSTO)sto).isRef())
        	{
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L5 + "]", Sparc.L5);
        	}
        	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.L5);
        }
        //call label directly
        else
        {
        	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, sto.getName());
        }
        addToBuffer(text_buffer, Sparc.NOP);
        
        if(debug) writeDebug("========writeFuncCall: get address of retSTO, store retValue in it ==========");
        if (retType.isVoidType())
        {
            // Do Nothing
        }
        // if returnSTO is not void, store returned value ot retSTO's address
        else if (retType.isFloatType())
        {
        	addToBuffer(text_buffer, retSTO.getAddress());
        	if (isRef)
        	{
        		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.O0 + "]", Sparc.L1);
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        	}
        	else
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
        }
        else
        {
        	addToBuffer(text_buffer, retSTO.getAddress());
        	if (isRef)
        	{
        		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.O0 + "]", Sparc.L1);
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        	}
        	else
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        } 


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
							|| funcReturnType instanceof BoolType)
						addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
								Integer.toString(((ConstSTO) returnExpr)
										.getIntValue()), Sparc.I0);
					// load return value to %f0
					else if (funcReturnType instanceof FloatType)
					{
						float value = ((ConstSTO) returnExpr).getFloatValue();
						has_rodata = true;
						decreaseIndent();
						addToBuffer(rodata_buffer, Sparc.VAR_LABEL, "temp"
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
				if(funcReturnType instanceof FloatType) {
	                getAddressHelper(returnExpr);
	                //probably don't need this. if pass by ref, then just
                    if (returnExpr.isVar() && ((VarSTO) returnExpr).isRef())
                        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
                    if (!byRef)
                    {
                    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F0);
                    }
                    else
                    {
                    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.I0);
                    }
                    
	                if(returnExpr.getType().isIntType())
	                {
	                	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.FITOS_OP, Sparc.F0, Sparc.F0);
	                }
	            } else {
	            	getAddressHelper(returnExpr);
	            	//get valued stored in returnExpr
	            	if(returnExpr.isVar() && ((VarSTO)returnExpr).isRef())
	            		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
	            	if(!byRef)
	            	{
	            		//TODO
	            		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.I0);
	            	}
	            	else
	            	{
	            		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.I0);
	            	}
	            }
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
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.EXIT);
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
    
    //extra credit 4: overload function call
    void writeOverloadFuncCall(FunctionPointerType funcPtr, Vector args, STO retSTO, boolean isRef )
    {
    	if(debug) writeDebug("--------in writeOverloadFuncCall: " + funcPtr.getFuncName());
    	
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, funcPtr.getFuncName());
        addToBuffer(text_buffer, Sparc.NOP);
        
        Type retType = retSTO.getType();
        
        if (retType.isVoidType())
        {
            // Do Nothing
        }
        // if returnSTO is not void, store returned value ot retSTO's address
        else if (retType.isFloatType())
        {
        	addToBuffer(text_buffer, retSTO.getAddress());
        	if (isRef)
        	{
        		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.O0 + "]", Sparc.L1);
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        	}
        	else
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
        }
        else
        {
        	addToBuffer(text_buffer, retSTO.getAddress());
        	if (isRef)
        	{
        		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.O0 + "]", Sparc.L1);
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        	}
        	else
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        } 
    	
    	if(debug) writeDebug("--------end of writeOverloadFuncCall-------");
    }
    
    void writeOverloadFuncDec(String id)
    {
    	has_text = true;
    	
    	// main:
        decreaseIndent();
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
    
    void writeSizeof(STO sto, int size)
    {
    	if(debug) writeDebug("---------in writeSizeof--------");
    	addToBuffer(text_buffer, sto.getAddress());
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(size), Sparc.L1 );
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1,"[" + Sparc.L0 + "]" );
    	
    }
    
    void writeAddressOf(STO sto, STO s)
    {
    	if(debug) writeDebug("------in writeAddressOf: " + sto.getName());
    	//get original sto address
    	getAddressHelper(sto);
        if (sto.isVar() && ((VarSTO) sto).isRef())
        	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L0);
        //mov address to %l1
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.L1);
        //get retSTO address
        addToBuffer(text_buffer, s.getAddress());
        //store address to retSTO
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
        if(debug) writeDebug("-------end of writeAddressOf-------");
    }
    
    void writeNewStmt(STO sto)
    {
    	if(debug) writeDebug("---------in writeNewStmt------");
    	//call to calloc to allocate memory on disk
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.O0);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET,
            Integer.toString(((PointerType) sto.getType()).getBaseType().getSize()), Sparc.O1);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, "calloc");
    	addToBuffer(text_buffer, Sparc.NOP);

        getAddressHelper(sto);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.O0, "[" + Sparc.L0 + "]");
        
        //increment allocatedMemory value
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".allocatedMemory", Sparc.L0);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, "inc", Sparc.L1);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
        
        if(debug) writeDebug("---------end 0f writeNewStmt------");
    }
    
    void writeDeleteStmt(STO sto)
    {
    	if(debug) writeDebug("---------in writeDeleteStmt------");

    	has_rodata = true;
    	resetReg();
    	
    	//get value of this var
    	getValue(sto);
    	
    	//check nullPtr
    	if(debug) writeDebug("======in writeDeleteStmt, check nullPtrExcep=======");
		String ptrLabel = "ptrLabel" + num_of_ptr++;
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L4);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.L4);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BNE_OP, ptrLabel);
		addToBuffer(text_buffer, Sparc.NOP);

		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".NullPtrException", Sparc.O0);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
		addToBuffer(text_buffer, Sparc.NOP);

		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.EXIT);
        addToBuffer(text_buffer, Sparc.NOP);

        decreaseIndent();
        addToBuffer(text_buffer, ptrLabel + ":\n");
        increaseIndent();
        if(debug) writeDebug("======end of check nullPtrExcep=======");
        
        // free ptr, call free
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L1, Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, "free");
        addToBuffer(text_buffer, Sparc.NOP);

        //set 0 to sto
        getAddressHelper(sto);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L1);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
        
        //decrement allocatedMemory value
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".allocatedMemory", Sparc.L0);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, "dec", Sparc.L1);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
        
        if(debug) writeDebug("---------end 0f writeDeleteStmt------");
    }
    
    //extra credit 2
    void writeMemoryLeak()
    {
    	if(debug) writeDebug("-------in writeMemoryLeak-------");
    	
    	//check double deletion
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".doubleDeleteError", Sparc.O0);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".allocatedMemory", Sparc.L0);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.O1);
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.O1, Sparc.G0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BGE_OP, "._memleaklabel"+ num_of_memoryLeak);
    	addToBuffer(text_buffer, Sparc.NOP);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
        addToBuffer(text_buffer, Sparc.NOP);
        decreaseIndent();
        addToBuffer(text_buffer, "._memleaklabel" + num_of_memoryLeak + ":\n");
        increaseIndent();
        num_of_memoryLeak++;
        //if allocated memory >= 0, check memory leak
        
    	//check memory leak
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".memoryLeakError", Sparc.O0);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".allocatedMemory", Sparc.L0);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.O1);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.O1, Sparc.G0);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BE_OP, "._memleaklabel"+ num_of_memoryLeak);
    	addToBuffer(text_buffer, Sparc.NOP);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
        addToBuffer(text_buffer, Sparc.NOP);
        decreaseIndent();
        addToBuffer(text_buffer, "._memleaklabel" + num_of_memoryLeak + ":\n");
        increaseIndent();
        num_of_memoryLeak++;        
        
        if(debug) writeDebug("-------end of writeMemoryLeak--------");
    }
	
    void  writeFuncPtr(STO sto)
    {
    	if(debug) writeDebug("-------in writeFuncPtr-------");
    	
    	//get func address
    	getAddressHelper(sto);
    	//load value from this address to %l5
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L5);
    	
    	//check nullPtr
    	if(debug) writeDebug("======in writeFuncPtr, check nullPtrExcep=======");
		String ptrLabel = "ptrLabel" + num_of_ptr++;
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L4);
		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L5, Sparc.L4);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BNE_OP, ptrLabel);
		addToBuffer(text_buffer, Sparc.NOP);

		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".NullPtrException", Sparc.O0);
		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
		addToBuffer(text_buffer, Sparc.NOP);

		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.EXIT);
        addToBuffer(text_buffer, Sparc.NOP);

        decreaseIndent();
        addToBuffer(text_buffer, ptrLabel + ":\n");
        increaseIndent();
        if(debug) writeDebug("======end of check nullPtrExcep=======");
    	
    	if(debug) writeDebug("-------end of writeFuncPtr------");
    }
    
    void writeTypeCast(STO newSTO, STO oldSTO)
    {
    	if(debug) writeDebug("----------in writeTypeCast-----------");
    	Type newType = null;
    	Type oldType = null;
    	
    	newType = newSTO.getType();
    	oldType = oldSTO.getType();
    	
    	//handle int to float
    	if(newType.isFloatType() && (oldType.isIntType() || oldType.isBoolType()))
    	{
    		if(debug) writeDebug("========in writeTypeCast, do Int to Float=========");
    		//get oldSTO value, stored in either F1 or F0
    		intToFloat(oldSTO);
    		if(floatReg == 0)
    		{
    			//value is stored in F1
    			//get newSTO address
        		getAddressHelper(newSTO);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			//value is stored in F1
    			//get newSTO address
        		getAddressHelper(newSTO);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    		}
    	}
    	//handle float to int, 
    	else if((newType.isIntType()) && oldType.isFloatType() )
    	{
    		if(debug) writeDebug("========in writeTypeCast, do Float to Int=========");
    		//get oldSTO value
    		FloatToInt(oldSTO);
    		if(floatReg == 0)
    		{
    			//value is in F1
    			getAddressHelper(newSTO);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
    		}
    		else
    		{
    			//value is in F0
    			getAddressHelper(newSTO);
    			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    		}
    	}
    	//handle float/int to bool, need to store 1 or 0 to newSTO
    	else if(newType.isBoolType() && (oldType.isFloatType() || oldType.isIntType() ))
    	{
    		if(oldType.isFloatType())
    		{
    			if(debug) writeDebug("========in writeTypeCast, float to bool=========");
        		//get oldSTO value
        		addToBuffer(text_buffer, oldSTO.getAddress());
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
        		
        		getAddressHelper(newSTO);
        		
        		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
        		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
        		
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
        		//addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L3, "[" + Sparc.L0 + "]");
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    	    	addToBuffer(text_buffer, Sparc.ONE_PARAM, "be", ".typeCastBool" + num_of_typecast);
    	    	addToBuffer(text_buffer, Sparc.NOP);
    	    	
    	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.L3);
    	    	

                decreaseIndent();
                addToBuffer(text_buffer, Sparc.NEW_LINE);
                addToBuffer(text_buffer, ".typeCastBool" + num_of_typecast + ":\n");
                increaseIndent();
                num_of_typecast++;

        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L3, "[" + Sparc.L0 + "]");

    		}
    		else
    		{
    			if(debug) writeDebug("========in writeTypeCast, int to bool=========");
        		//get oldSTO value
        		getValue(oldSTO);
        		
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "0", Sparc.L3);
        		if(localReg == 0)
        			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L2, Sparc.G0);
        		else
        			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L1, Sparc.G0);
    	    	addToBuffer(text_buffer, Sparc.ONE_PARAM, "be", ".typeCastBool" + num_of_typecast);
    	    	addToBuffer(text_buffer, Sparc.NOP);
    	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.L3);

                decreaseIndent();
                addToBuffer(text_buffer, Sparc.NEW_LINE);
                addToBuffer(text_buffer, ".typeCastBool" + num_of_typecast + ":\n");
                increaseIndent();
                num_of_typecast++;
                
                getAddressHelper(newSTO);
                addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L3, "[" + Sparc.L0 + "]");
    		}
    	}
    	//handle all the other cases
    	else
    	{
    		if(debug) writeDebug("========in writeTypeCast, do regular case=========");
    		if(debug) writeDebug("========in writeTypeCast, get oldSTO value=========");
    		getValue(oldSTO);
    		if(debug) writeDebug("========in writeTypeCast, get newSTO address=========");
    		getAddressHelper(newSTO);
    		if(debug) writeDebug("========in writeTypeCast, store old value to new sto=========");
    		if(newType.isFloatType())
    		{
    			if(floatReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F1, "[" + Sparc.L0 + "]");
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
    			}
    		}
    		else
    		{
    			if(localReg == 0)
    			{
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L2, "[" + Sparc.L0 + "]");
    			}
    			else
    			{
    				addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]");
    			}
    		}
    	}
    		
    	
    	if(debug) writeDebug("----------end of writeTypeCast----------");
    }
    
    //get lowestOffset
    int getLowestOffset()
    {
    	return lowestOffset;
    }
    
    void setLowestOffset(int offset)
    {
    	lowestOffset = offset;
    }
    
    //extra credit 1
    void writeDeallocatedStack(STO sto)
    {
    	if(debug) writeDebug("-------in writeDeallocatedStack--------");
    	
    	writeDerefAddress(sto);
    	
    	String deallocatedLabel = "deallocatedStack" + num_of_deallocatedStack++;
    	
    	//compare sp address
    	addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.SP, Integer.toString(lowestOffset), Sparc.L4);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L0, Sparc.L4);
    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BLU_OP, deallocatedLabel);
    	addToBuffer(text_buffer, Sparc.NOP);
    	
    	addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.SP, "92", Sparc.L4);
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L0, Sparc.L4);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.BGU_OP, deallocatedLabel);
        addToBuffer(text_buffer, Sparc.NOP);
        
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, ".deallocatedStack", Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
        addToBuffer(text_buffer, Sparc.NOP);
        
        addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "1", Sparc.O0);
        addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.EXIT);
        addToBuffer(text_buffer, Sparc.NOP);
        
        decreaseIndent();
        addToBuffer(text_buffer, deallocatedLabel + ":\n");
        increaseIndent();
    }
    
    //extra credit 3
    void writeExtraArguments(Vector<VariableBox<STO, VarSTO>> extraArgs, int offset)
    {
    	if(debug) writeDebug("---------in writeExtraArguments--------");
    	int currOffset = 92;
    	//allocated stack space for arg7 & 8
        addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.SP, "-" + Integer.toString(offset) + " & -8", Sparc.SP);
        
        //loop through all extra args
        for(int i = 0; i < extraArgs.size(); i++)
        {
        	VariableBox<STO, VarSTO> vb = extraArgs.get(i);
        	resetReg();
        	STO arg = vb.getVariable();
        	VarSTO param = vb.getExpr();
        	if(debug) writeDebug("=======in writeExtraArguments, param is: " + param.getName());
        	//get param's value or address
        	if(param.isRef())
        	{
        		getAddressHelper(arg);
        		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.MOV, Sparc.L0, Sparc.L1);
        	}
        	else
        	{
        		if(param.getType().isFloatType() & arg.getType().isIntType())
        		{
        			intToFloat(arg);
        		}
        		else
        		{
        			getValue(arg);
        		}
        		
        	}
        	//get extra args' address
    		//%sp + offset
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(currOffset), Sparc.L0);
    		addToBuffer(text_buffer, Sparc.THREE_PARAM, Sparc.ADD_OP, Sparc.SP, Sparc.L0, Sparc.L0);
            if (param.getType().isFloatType() && !param.isRef()) 
            {
            	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]");
            }
            else
            {
            	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 +"]");
            }
            
        	currOffset += arg.getType().getSize();
        }
    	
    	if(debug) writeDebug("---------end of writeExtraArguments-------");
    }
		
	
} //end of code gen
