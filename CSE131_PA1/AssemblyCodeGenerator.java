
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * An Assembly Code Generator example emphasizing well thought design and
 * the use of java 1.5 constructs.
 * 
 * Disclaimer : This is not code meant for you to use directly but rather
 * an example from which I hope you can learn useful constructs and
 * conventions.
 * 
 * Topics of importance (Corrosponding to the inline comment numbers below)
 * 
 * 1) We will use this variable to denote the current level of indentation.
 *    For assembly there is usually only one or two levels of nesting.
 * 
 * 2) A collection of static final error messages.  This isn't so important in
 *    your project but is useful.  The static keyword means we only make 3 
 *    strings, shared across potentially multiple AssemblyCodeGenerator(s).
 *    It is Java convention to spell constant variables with upper casing.
 *    
 * 3) FileWriter is a basic IO class which can write basic types such as
 *    Strings to a file.  For performance you may want to look into the
 *    BufferedWriter class.
 *    
 * 4) This is a template for our file header.  It is very basic consisting only
 *    of a time stamp.
 *    
 * 5) This is the string we will use as an indentation seperator.  We are 
 *    encapsulating this seperator into one variable so we only need to change
 *    the initialization if we want to change our spacing to say 4 spaces for
 *    example.  Imagine if you simply used the literal "\t" in 500 places and
 *    then you decide you want to change it to 4 spaces!  Aside from regular 
 *    expressions you have a lot of work ahead of you.
 *    
 * 6) These are constant String templates that will be used for code
 *    generation.  It is nice to isolate these in one place or even in another
 *    file so that we can quickly make universal changes if needed.  You will
 *    notice that we could generate an entirely different language by simply
 *    changing the construct definitions.  I recommend defining all operations
 *    as well as formats.  Operations are things like add, mul, set, etc.
 *    Formats are like {OPERATION} {REG_1}, {REG_2}, {REG_3} etc.
 *    
 * 7) Here we are making a call to writeAssembly to write our header with the
 *    current time.  writeAssembly explained later.
 *    
 * 8) These methods are used to increase or decrease our current indentation
 *    level.  You might ask why make a method for a simple inc/dec?  We are
 *    encapsulating the notion of adjusting indentation.  It just so happens
 *    that this current implementation is just a variable increment or 
 *    decrement, but who is to say that the operation won't be more advanced
 *    in the future.  Maybe we want to log a message everytime we increment
 *    or decrement indentation.  We wouldn't want to add the logging code
 *    everywhere we were incrementing the variable (if we didn't have the
 *    methods).
 *    
 * 9) This signature may look foreign to you.  What is says is that we have 
 *    public method named writeAssembly which takes as parameters a String
 *    followed by 1 or more strings.  This construct is called "VarArgs" and
 *    is a Java 1.5 feature.  This allows you to write one method which can
 *    be applied to any number of parameters.  This method simply takes in 
 *    a template and all the strings that will be substituted into the 
 *    template.  When you are actually in the method, the parameter 
 *    String ... params will be an array of strings.
 *    
 * 10) This is where we use our indent_level.  We will indent indent_level levels
 *     of indentation.  That is an awkward sentence isn't it!  StringBuilder is
 *     an efficient class to build strings from concatentations.  If your 
 *     concatenations span multiple lines of code, using a StringBuilder can
 *     offer signifigant performance when compared to using the + operator.
 *     This topic can get fairly detailed, send me an email or come talk to me
 *     in the lab for more details.
 * 
 * 11) Here we are writing the message to file, notice we are using the 
 *     String.format method which takes a printf like format string followed
 *     by an array of Objects which are the parameters to the format string.
 *     
 * 12) Main is just a small demo that will create a tiny assembly file in the
 *     current directory called "output.s".  This file doesn't compile and is
 *     not meant to.
 * 
 * @author Evan Worley
 */

public class AssemblyCodeGenerator {
    // 1
    private int indent_level = 0;
    private final String COMPILER_IDENT = "KANGPAILE131";
    
    // 2
    private static final String ERROR_IO_CLOSE = 
        "Unable to close fileWriter";
    private static final String ERROR_IO_CONSTRUCT = 
        "Unable to construct FileWriter for file %s";
    private static final String ERROR_IO_WRITE = 
        "Unable to write to fileWriter";

    // 3
    private FileWriter fileWriter;
    
    // 4
    private static final String FILE_HEADER = 
        "/*\n" +
        " * Generated %s\n" + 
        " */\n\n";
        
    // 5
    private static final String SEPARATOR = "\t";
    
    // 6
    private static final String SET_OP = "set";
    private static final String TWO_PARAM = "%s" + SEPARATOR + "%s, %s\n";
    
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
    //Quoted fommat
    // "\ \"
    public String quoted(String str)
    {
        return "\"" + str + "\"";
    }
    //write Comment Function
    public void writeComment(String comment)
    {
        // ! Comment
        writeAssembly(Sparc.LINE, Sparc.COMMENT + " " + comment);
    }
    //Header
    public void writeCommentHeader(String comment)
    {
        writeAssembly(Sparc.BLANK_LINE);
        writeComment("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        writeComment("!!      " + comment);
        writeComment("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        writeAssembly(Sparc.BLANK_LINE);
    }
    //DoProgramStart
    public void DoProgramStart(String filename)
    {
        increaseIndent();
    
        writeCommentHeader("Starting Program");

        // .file "<filename>"
        writeAssembly(Sparc.ONE_PARAM, Sparc.FILE_DIR, quoted(filename));

        // .ident <COMPILER_IDENT
        writeAssembly(Sparc.ONE_PARAM, Sparc.IDENT_DIR, quoted(COMPILER_IDENT));

        writeAssembly(Sparc.BLANK_LINE);

        DoROPrintDefines();
        MakeGlobalInitGuard();

        //stackPointer.push(new Integer(0));
        //currentFunc.push(new FuncSTO("global", new FuncPtrType(new VoidType(), false)));

    }
    public void DoROPrintDefines()
    {
        // !----Default String Formatters----
        writeCommentHeader("Default String Formatters");

        // .section ".rodata"
        writeAssembly(Sparc.ONE_PARAM, Sparc.SECTION_DIR, Sparc.RODATA_SEC);

        // _endl: .asciz "\n"
        writeAssembly(Sparc.RO_DEFINE, Sparc.ENDL, Sparc.ASCIZ_DIR, quoted("\\n"));

        // _intFmt: .asciz "%d"
        writeAssembly(Sparc.RO_DEFINE, Sparc.INTFMT, Sparc.ASCIZ_DIR, quoted("%d"));

        // _boolFmt: .asciz "%s"
        writeAssembly(Sparc.RO_DEFINE, Sparc.BOOLFMT, Sparc.ASCIZ_DIR, quoted("%s"));

        // _boolT: .asciz "true"
        writeAssembly(Sparc.RO_DEFINE, Sparc.BOOLT, Sparc.ASCIZ_DIR, quoted("true"));

        // _boolF: .asciz "false"
        writeAssembly(Sparc.RO_DEFINE, Sparc.BOOLF, Sparc.ASCIZ_DIR, quoted("false"));

        // _strFmt: .asciz "%s"
        writeAssembly(Sparc.RO_DEFINE, Sparc.STRFMT, Sparc.ASCIZ_DIR, quoted("%s"));
        
        writeAssembly(Sparc.BLANK_LINE);
    }
    public void MakeGlobalInitGuard()
    {
        // !----Create .init for global init guard----
        writeComment("Create .init for global init guard");

        // .section ".bss"
        writeAssembly(Sparc.ONE_PARAM, Sparc.SECTION_DIR, Sparc.BSS_SEC);

        // .align 4
        writeAssembly(Sparc.ONE_PARAM, Sparc.ALIGN_DIR, String.valueOf(4));

        // .init: .skip 4
        decreaseIndent();
        writeAssembly(Sparc.GLOBAL_DEFINE, ".init", Sparc.SKIP_DIR, String.valueOf(4));
        increaseIndent();

        writeAssembly(Sparc.BLANK_LINE);
    }
    // 9
    public void writeAssembly(String template, String ... params) {
        StringBuilder asStmt = new StringBuilder();
        
        // 10
        for (int i=0; i < indent_level; i++) {
            asStmt.append(SEPARATOR);
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
    
    
    // 12
    public static void main(String args[]) {
        AssemblyCodeGenerator myAsWriter = new AssemblyCodeGenerator("rc.s");

        myAsWriter.increaseIndent();
        myAsWriter.writeAssembly(TWO_PARAM, SET_OP, String.valueOf(4095), "%l0");
        myAsWriter.increaseIndent();
        myAsWriter.writeAssembly(TWO_PARAM, SET_OP, String.valueOf(1024), "%l1");
        myAsWriter.decreaseIndent();
        
        myAsWriter.writeAssembly(TWO_PARAM, SET_OP, String.valueOf(512), "%l2");
        
        myAsWriter.decreaseIndent();
        myAsWriter.dispose();
    }
}