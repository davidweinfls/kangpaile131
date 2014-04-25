JAVACUP_JAR = java-cup-v11a.jar

SOURCES = \
	ArithmeticOp.java \
	ArrayType.java \
	BasicType.java \
	BinaryOp.java \
	BitwiseOp.java \
	BooleanOp.java \
	BoolType.java \
	ComparisonOp.java \
	CompositeType.java \
	ConstSTO.java \
	ErrorMsg.java \
	ErrorPrinter.java \
	ErrorSTO.java \
	ExprSTO.java \
	Formatter.java \
	FuncSTO.java \
	FunctionPointerType.java \
	IntType.java \
	Lexer.java \
	LineNumberPushbackStream.java \
	MyParser.java \
	NullPointerType.java \
	NumericType.java \
	Operator.java \
	parser.java \
	PointerGroupType.java \
	RC.java \
	RCdbg.java \
	Scope.java \
	STO.java \
	StructType.java \
	sym.java \
	SymbolTable.java \
	Token.java \
	Type.java \
	TypedefSTO.java \
	UnaryOp.java \
	VariableBox.java \
	VarSTO.java \
	VoidType.java

new:
	make clean
	make rc

debug:
	make clean
	make rcdbg

rc: $(SOURCES) parser.java sym.java
	javac RC.java
	cp RC.sh RC
	chmod 755 RC

rcdbg: $(SOURCES) parser.java sym.java
	javac RCdbg.java
	cp RCdbg.sh RC
	chmod 755 RC

parser.java: rc.cup
	javacup < rc.cup

clean:
	rm -f *.class RC parser.java sym.java a.out core rc.s

tar:
	tar cvf rc.tar $(SOURCES) Makefile RC.sh

#       Makes a backup called backups/MMDDMM:SS.tar.Z
backup:
	-@make clean
	-@mkdir backups
	tar cvf - $(SOURCES) Makefile RC.sh | compress > backups/`date +%m%d%R`.tar.Z
