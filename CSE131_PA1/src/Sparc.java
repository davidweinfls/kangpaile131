
class Sparc
{
    //-----------------------------------------------------------------------------
    //      Directives
    //-----------------------------------------------------------------------------
    public static final String FILE_DIR    = ".file";
    public static final String IDENT_DIR   = ".ident";
    public static final String SECTION_DIR = ".section";
    public static final String GLOBAL_DIR  = ".global";
    public static final String ALIGN_DIR   = ".align %s\n";
    public static final String ASCIZ_DIR   = ".asciz";
    public static final String SKIP_DIR    = ".skip";
    public static final String WORD_DIR    = ".word";
    public static final String GLOBAL_VAR  = ".global %s\n";
    public static final String BSS_VAR 	   = "%s:\t.skip %s\n";
    public static final String STRING_TEMP    = "%s:\t.asciz \"%s\"\n";

    //-----------------------------------------------------------------------------
    //      Sections
    //-----------------------------------------------------------------------------
    public static final String TEXT_SEC   = ".section \".text\"\n";
    public static final String DATA_SEC   = ".section \".data\"\n"; //+ "._memleak:\t.word\t0\n"; 
    public static final String BSS_SEC    = ".section \".bss\"\n"; 
    public static final String RODATA_SEC = ".section \".rodata\"\n" +
            /*"._fincdec:\t.single 0r1\n" +*/
            ".endl:\t.asciz \"\\n\"\n" +
            ".intFmt:\t.asciz \"%%d\"\n" +
            ".boolT:\t.asciz \"true\"\n" +
            ".boolF:\t.asciz \"false\"\n" +
            ".float_one:\t.single 0r1\n"; 

    //-----------------------------------------------------------------------------
    //      Registers
    //-----------------------------------------------------------------------------

    public static final String FP = "%fp";
    public static final String SP = "%sp";

    // Global
    public static final String G0 = "%g0";
    public static final String G1 = "%g1";
    public static final String G2 = "%g2";
    public static final String G3 = "%g3";
    public static final String G4 = "%g4";
    public static final String G5 = "%g5";
    public static final String G6 = "%g6";
    public static final String G7 = "%g7";

    // Local
    public static final String L0 = "%l0";
    public static final String L1 = "%l1";
    public static final String L2 = "%l2";
    public static final String L3 = "%l3";
    public static final String L4 = "%l4";
    public static final String L5 = "%l5";
    public static final String L6 = "%l6";
    public static final String L7 = "%l7";

    // Input
    public static final String I0 = "%i0";
    public static final String I1 = "%i1";
    public static final String I2 = "%i2";
    public static final String I3 = "%i3";
    public static final String I4 = "%i4";
    public static final String I5 = "%i5";

    // Output
    public static final String O0 = "%o0";
    public static final String O1 = "%o1";
    public static final String O2 = "%o2";
    public static final String O3 = "%o3";
    public static final String O4 = "%o4";
    public static final String O5 = "%o5";

    // Float
    public static final String F0 = "%f0";
    public static final String F1 = "%f1";
    public static final String F2 = "%f2";
    public static final String F3 = "%f3";
    public static final String F4 = "%f4";
    public static final String F5 = "%f5";

    // Pseudonyms - For ease of programming
   /* public static final String REG_PARAM0 = REG_INPUT0;
    public static final String REG_PARAM1 = REG_INPUT1;
    public static final String REG_PARAM2 = REG_INPUT2;
    public static final String REG_PARAM3 = REG_INPUT3;
    public static final String REG_PARAM4 = REG_INPUT4;
    public static final String REG_PARAM5 = REG_INPUT5;
    public static final String[] PARAM_REGS = new String[]{REG_INPUT0, REG_INPUT1, REG_INPUT2, REG_INPUT3, REG_INPUT4, REG_INPUT5};

    public static final String REG_ARG0 = REG_OUTPUT0;
    public static final String REG_ARG1 = REG_OUTPUT1;
    public static final String REG_ARG2 = REG_OUTPUT2;
    public static final String REG_ARG3 = REG_OUTPUT3;
    public static final String REG_ARG4 = REG_OUTPUT4;
    public static final String REG_ARG5 = REG_OUTPUT5;
    public static final String[] ARG_REGS = new String[]{REG_OUTPUT0, REG_OUTPUT1, REG_OUTPUT2, REG_OUTPUT3, REG_OUTPUT4, REG_OUTPUT5};

    public static final String REG_SET_RETURN = REG_INPUT0;
    public static final String REG_GET_RETURN = REG_OUTPUT0;*/

    //-----------------------------------------------------------------------------
    //      Constants
    //-----------------------------------------------------------------------------
    public static final String ENDL    = ".endl";
    public static final String INTFMT  = ".intFmt";
    public static final String BOOLFMT = ".boolFmt";
    public static final String BOOLT   = ".boolT";
    public static final String BOOLF   = ".boolF";
    public static final String STRFMT  = ".strFmt";

    //-----------------------------------------------------------------------------
    //      Comment
    //-----------------------------------------------------------------------------
    public static final String COMMENT  = "!";

    //-----------------------------------------------------------------------------
    //      Separator
    //-----------------------------------------------------------------------------
    public static final String SEPARATOR = "\t";
    public static final String INDENTOR  = "\t";    // tabs might actually be better :(
    //public static final String INDENTOR  = "    ";
    
    //-----------------------------------------------------------------------------
    //      Save
    //-----------------------------------------------------------------------------
    public static final String SAVE_OP   = "save";
    public static final String SAVE_WORD = "SAVE";

    //-----------------------------------------------------------------------------
    //      Set
    //-----------------------------------------------------------------------------
    public static final String SET = "set";

    //-----------------------------------------------------------------------------
    //      Ret
    //-----------------------------------------------------------------------------
    public static final String RET = "ret\n";

    //-----------------------------------------------------------------------------
    //      Restore
    //-----------------------------------------------------------------------------
    public static final String RESTORE  = "restore\n";
    
    //-----------------------------------------------------------------------------
    //      Move
    //-----------------------------------------------------------------------------
    public static final String MOV = "mov";

    //-----------------------------------------------------------------------------
    //      Simple Arithmetic
    //-----------------------------------------------------------------------------
    public static final String ADD_OP = "add";
    public static final String ADDCC_OP = "addcc";
    public static final String SUB_OP = "sub";
    public static final String SUBCC_OP = "subcc";
    
    //-----------------------------------------------------------------------------
    //      Increment/Decrement
    //-----------------------------------------------------------------------------
    public static final String INC_OP  = "inc";
    public static final String INCCC_OP = "inccc";
    public static final String DEC_OP  = "dec";
    public static final String DECCC_OP = "deccc";
    
    //-----------------------------------------------------------------------------
    //      Float Ops
    //-----------------------------------------------------------------------------
    public static final String FADDS_OP = "fadds";
    public static final String FSUBS_OP = "fsubs";
    public static final String FMULS_OP = "fmuls";
    public static final String FDIVS_OP = "fdivs";

    //-----------------------------------------------------------------------------
    //      Fitos
    //-----------------------------------------------------------------------------
    public static final String FITOS_OP = "fitos";

    //-----------------------------------------------------------------------------
    //      Shifting
    //-----------------------------------------------------------------------------
    public static final String SLL_OP = "sll";
    public static final String SRL_OP = "srl";
    public static final String SRA_OP = "sra";

    //-----------------------------------------------------------------------------
    //      Load
    //-----------------------------------------------------------------------------
    public static final String LD = "ld";

    //-----------------------------------------------------------------------------
    //      Store
    //-----------------------------------------------------------------------------
    public static final String ST = "st";

    //-----------------------------------------------------------------------------
    //      Compare
    //-----------------------------------------------------------------------------
    public static final String CMP   = "cmp";
    public static final String FCMPS = "fcmps";

    //-----------------------------------------------------------------------------
    //      Branch
    //-----------------------------------------------------------------------------
    public static final String BE_OP   = "be";
    public static final String BGE_OP  = "bge";
    public static final String BG_OP   = "bg";
    public static final String BLE_OP  = "ble";
    public static final String BL_OP   = "bl";
    public static final String BNE_OP  = "bne";
    public static final String BA_OP   = "ba";
    public static final String BN_OP   = "bn";

    public static final String FBE_OP  = "fbe";
    public static final String FBGE_OP = "fbge";
    public static final String FBG_OP  = "fbg";
    public static final String FBLE_OP = "fble";
    public static final String FBL_OP  = "fbl";
    public static final String FBNE_OP = "fbne";
    
    //-----------------------------------------------------------------------------
    //      Call
    //-----------------------------------------------------------------------------
    public static final String CALL = "call";
    public static final String NOP  = "nop\n\n";
    
    //-----------------------------------------------------------------------------
    //      Multiplication/Division/Modulus Arithmetic
    //-----------------------------------------------------------------------------
    public static final String MUL = ".mul";
    public static final String DIV = ".div";
    public static final String REM = ".rem";

    //-----------------------------------------------------------------------------
    //      Negating/2's Complement
    //-----------------------------------------------------------------------------
    public static final String NEG_OP   = "neg";
    public static final String FNEGS_OP = "fnegs";

    //-----------------------------------------------------------------------------
    //      Clear Register
    //-----------------------------------------------------------------------------
    public static final String CLR_OP = "clr";

    //-----------------------------------------------------------------------------
    //      Bitwise Ops
    //-----------------------------------------------------------------------------
    public static final String AND_OP   = "and";
    public static final String ANDCC_OP = "andcc";
    public static final String OR_OP    = "or";
    public static final String ORCC     = "orcc";
    public static final String XOR_OP   = "xor";
    public static final String XORCC_OP = "xorcc";

    //-----------------------------------------------------------------------------
    //      Functions
    //-----------------------------------------------------------------------------
    public static final String PRINTF = "printf";
    public static final String PRINTFLOAT = "printFloat";
    public static final String EXIT = "exit";
    public static final String INPUTINT = "inputInt";
    public static final String INPUTFLOAT = "inputFloat";
    
    //-----------------------------------------------------------------------------
    //      Precisions
    //-----------------------------------------------------------------------------
    public static final String SINGLEP = ".single";
    
    //-----------------------------------------------------------------------------
    //      Templates
    //-----------------------------------------------------------------------------
    public static final String LINE          = "%s\n";
    public static final String LABEL         = "%s:\n";
    public static final String VAR_LABEL	 = "%s:\t.%s %s\n";
    public static final String NEW_LINE      = "\n";

    public static final String NO_PARAM      = "%s\n";
    public static final String ONE_PARAM     = "%s" + SEPARATOR + "%s\n";
    public static final String TWO_PARAM     = "%s" + SEPARATOR + "%s, %s\n";
    public static final String THREE_PARAM   = "%s" + SEPARATOR + "%s, %s, %s\n";

    public static final String NO_PARAM_COMM    = "%s" + SEPARATOR + SEPARATOR + "! %s\n";
    public static final String ONE_PARAM_COMM   = "%s" + SEPARATOR + "%s" + SEPARATOR + SEPARATOR + SEPARATOR + "! %s\n";
    public static final String TWO_PARAM_COMM   = "%s" + SEPARATOR + "%s, %s" + SEPARATOR + SEPARATOR + SEPARATOR + "! %s\n";
    public static final String THREE_PARAM_COMM = "%s" + SEPARATOR + "%s, %s, %s" + SEPARATOR + SEPARATOR + SEPARATOR + "! %s\n";

    public static final String SAVE_DOT_FUNCNAME     = SAVE_WORD + ".%s";
    public static final String SAVE_FUNC = " = -(92 + %s) & -8\n";
    public static final String RO_DEFINE     = "%s:" + SEPARATOR + "%s" + SEPARATOR + "%s\n";
    public static final String GLOBAL_DEFINE = "%s:" + SEPARATOR + "%s" + SEPARATOR + "%s\n";
    
    public static final String FUNC_COMMENT = "! --%s--\n";
    public static final String FUNC_LABEL = "%s:\n";
    
    public static final String ELSE = "else";
    


}
