import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AssemblyCodeGenerator {

	private boolean debug = false;
	
	//useful local vars
	private int indent_level = 0;
	private String currFuncName;
	private int num_of_temp = 0;
	
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
    	//if(debug) writeDebug("In writeGLobalVariable");
    	
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
    
	public void writeConstValue(STO sto)
	{
		if (debug)
			writeDebug("------in writeConst--------");
		Type t = sto.getType();
		if (t instanceof IntType || t instanceof BoolType)
		{
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
			addToBuffer(rodata_buffer, Sparc.VAR_LABEL, "temp" + num_of_temp,
					"single",
					"0r" + Float.toString(((ConstSTO) sto).getFloatValue()));
			increaseIndent();
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, "temp"
					+ num_of_temp, Sparc.L0);
			addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.LD, "[" + Sparc.L0
					+ "]", Sparc.F0);

			addToBuffer(text_buffer, sto.getAddress());
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
    	
    	if(debug) writeDebug("------------in writePrint---------------");
    	/*
    	 * set _intFmt, %o0
		 * set 5, %o1
		 * call printf
		 * nop
    	 */
    	if(sto == null)
    	{
    		addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.ENDL, Sparc.O0 );
    		addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
    		addToBuffer(text_buffer, Sparc.NOP);
    		return;
    	}
    	
    	Type t = sto.getType();    	
	    if(t instanceof IntType)
	    {
	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Sparc.INTFMT, Sparc.O0 );
	    	addToBuffer(text_buffer, Sparc.TWO_PARAM, Sparc.SET, Integer.toString(( (ConstSTO)sto).getIntValue()) , Sparc.O1);
	    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTF);
	    	addToBuffer(text_buffer, Sparc.NOP);		
	    }
	    else if(t instanceof FloatType)
	    {
	    	addToBuffer(text_buffer, Sparc.ONE_PARAM, Sparc.CALL, Sparc.PRINTFLOAT);
	    	addToBuffer(text_buffer, Sparc.NOP);
	    }
    	
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
        if(debug) writeDebug("in writeFuncClose");
        if (returnType instanceof VoidType) {
	        addToBuffer(text_buffer, Sparc.RET);
	        addToBuffer(text_buffer, Sparc.RESTORE);
	    }

        decreaseIndent();
        addToBuffer(text_buffer, Sparc.NEW_LINE);
        addToBuffer(text_buffer, Sparc.SEPARATOR);
        addToBuffer(text_buffer, String.format(Sparc.SAVE_DOT_FUNCNAME, currFuncName) + Sparc.SAVE_FUNC,
         Integer.toString(Math.abs(offset)));
        addToBuffer(text_buffer, Sparc.NEW_LINE);
        increaseIndent();
/*        reg = 0;
        floatReg = 0;*/
    }
    
    public void writeExpr(STO sto, boolean isFloat)
    {
    	/*if (t.isFloat()) {
            if (floatReg == 0) {
                if (sto.isVar() && ((VarSTO) sto).isRef())
                    appendString(text_buffer, Sparc.TWO_PARAM, Sparc.LD_OP, "["+LOC0+"]", LOC0);
                appendString(text_buffer, Sparc.TWO_PARAM, Sparc.LD_OP, "["+LOC0+"]", FLT0);
                if(itof)
                    appendString(text_buffer, Sparc.TWO_PARAM, ITOF_OP, FLT0, FLT0);
                floatReg = 1;
            } else {
                if (sto.isVar() && ((VarSTO) sto).isRef())
                    appendString(text_buffer, Sparc.TWO_PARAM, LD_OP, "["+LOC0+"]", LOC0);
                appendString(text_buffer, Sparc.TWO_PARAM, LD_OP, "["+LOC0+"]", FLT1);
                if(isFloat)
                    appendString(text_buffer, Sparc.TWO_PARAM, ITOF_OP, FLT1, FLT1);
                floatReg = 0;
            }
        } else {
            if (reg == 0) {
                if (sto.isVar() && ((VarSTO) sto).isRef())
                    appendString(text_buffer, Sparc.TWO_PARAM, LD_OP, "["+LOC0+"]", LOC0);
                appendString(text_buffer, Sparc.TWO_PARAM, LD_OP, "["+LOC0+"]", LOC1);
                reg = 1;
            } else {
                if (sto.isVar() && ((VarSTO) sto).isRef())
                    appendString(text_buffer, Sparc.TWO_PARAM, LD_OP, "["+LOC0+"]", LOC0);
                appendString(text_buffer, Sparc.TWO_PARAM, LD_OP, "["+LOC0+"]", LOC2);
                reg = 0;
            }
        }*/
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
