import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AssemblyCodeGenerator {

	private boolean debug = true;
	
	//useful local vars
	private int indent_level = 0;
	private String currFuncName;
	private int num_of_temp = 0;
	private int num_of_bool = 0;
	private int num_of_if = 0;
	private int num_of_else = 0;
	
	private Stack<String> ifStack = new Stack<String>();
	
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
    
    void writeConstVar(String id, boolean is_static, boolean is_global, STO sto, Type t)
    {
    	if(debug) writeDebug("------------in writeConst: " + sto.getName());
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
			writeDebug("------in writeConst: " + sto.getName());
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
    	
    	//if(debug) writeDebug("------------in writePrint---------------");
    	
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
    	writeExpr(sto);
    	
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
    	
    }
    
    // assembly template, get address and then load the value stored in that address
    public void writeExpr(STO sto)
    {
    	//if(debug) writeDebug("-------in writeExpr------------");
    	
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
			// ld	[%l0], %f0
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.F0);
		}
		else if(t instanceof IntType || t instanceof BoolType)
		{
			addToBuffer(text_buffer, sto.getAddress());
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0 + "]", Sparc.L1);
		}
    }
    
    //used in DoBinaryExpr
    void writeBinaryExpr(STO a, Operator o, STO b, STO result)
    {
    	if(debug) writeDebug("--------in writeBinaryExpr-------");
    	writeDebug(a.getName() + o.getName() + b.getName());
    	Type aType = a.getType();
    	Type bType = b.getType();
    	Type rType = result.getType();
    	// if constant folding
    	if(result instanceof ConstSTO)
    	{
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
    		
    	}
    		
    	
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
    	//not const folding
    	else 
    	{
    		
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
    		writeExpr(expr);
    		//get var address
    		addToBuffer(text_buffer, var.getAddress());
    		//store expr value (%f0) to var address [%l0]
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.F0, "[" + Sparc.L0 + "]" ); 
    	}
    	else
    	{
    		//load expr value
    		writeExpr(expr);
    		//get var address
    		addToBuffer(text_buffer, var.getAddress());
    		//store expr value (%f0) to var address [%l0]
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.ST, Sparc.L1, "[" + Sparc.L0 + "]" ); 
    	}
    }
    
    void writeIf(STO sto)
    {
    	if(debug) writeDebug("------------in writeIf------------");
    	
    	String endIfLabel = ".endIf" + num_of_if++;
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
    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.CMP, Sparc.L0, Sparc.G0);
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
    
    public void writeCloseBlock (boolean ifOrWhile)
    {
        if(debug) writeDebug("----------in writeCloseBlock-----------");
        decreaseIndent();
        if (ifOrWhile)
        	addToBuffer(text_buffer, ifStack.pop()+":\n");
        else
            //addToBuffer(text_buffer, whileLabels.pop()+":\n");
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
/*        reg = 0;
        floatReg = 0;*/
    }
    
    // used in DoVarDecl. write assembly for local var with initialization
	void writeLocalVariableWInit(STO var, STO expr)
	{
		if (debug)
			writeDebug("---------in writeLocalVariableWInit:" + var.getName());
		addToBuffer(text_buffer, var.getAddress());

		Type t = var.getType();
		if (t instanceof FloatType)
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
	}
    
	// used in DoVarDecl. write assembly for local var without initialization
	void writeLocalVariableWOInit(STO var)
	{
		if (debug)
			writeDebug("---------in writeLocalVariableW)Init:" + var.getName());

		//not sure what to do
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
