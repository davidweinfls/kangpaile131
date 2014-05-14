import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AssemblyCodeGenerator {

	private boolean debug = true;
	
	//useful local vars
	private int indent_level = 0;
	
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
    public boolean has_rodata = false;
    public boolean has_text 	= false;
    public boolean has_bss 	= false;

    // 3
    private FileWriter fileWriter;
    
    // 4
    private static final String FILE_HEADER = 
        "/*\n" +
        " * Generated %s\n" + 
        " */\n\n";
    
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
                addToBuffer(data_buffer, Sparc.VAR_LABEL, id, "word", Integer.toString( ((ConstSTO) sto).getIntValue()) );
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
    
    /*
     * write print statement, for cout
     */
    public void writePrint(STO sto)
    {
    	
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
