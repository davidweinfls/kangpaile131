! 
! Generated Wed May 21 03:18:10 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "a is: "
	.align 4
temp1:	.asciz "b is: "
	.align 4
temp2:	.asciz "c is: "
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------intToFloat: 5 5

! =======in intToFloat: getAddress of 5
	set	-4, %l0
	add	%fp, %l0, %l0

! =======in intToFloat: load value of 5
	ld	[%l0], %f0

! =======in intToFloat: call itos 5
	fitos	%f0, %f0

! -------in getValue: 5: 5.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:a
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------in writeConstantLiteral: 6
	set	6, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------intToFloat: 6 6

! =======in intToFloat: getAddress of 6
	set	-12, %l0
	add	%fp, %l0, %l0

! =======in intToFloat: load value of 6
	ld	[%l0], %f0

! =======in intToFloat: call itos 6
	fitos	%f0, %f0

! -------in getValue: 6: 6.0
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:b
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------in writeConstantLiteral: 7
	set	7, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------intToFloat: 7 7

! =======in intToFloat: getAddress of 7
	set	-20, %l0
	add	%fp, %l0, %l0

! =======in intToFloat: load value of 7
	ld	[%l0], %f0

! =======in intToFloat: call itos 7
	fitos	%f0, %f0

! -------in getValue: 7: 7.0
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:c
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! -------writeUnaryExpr: b ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------in writePre: b

! =======in writePre, step 1: load value to local1

! -------in getValue: b: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! =======in writePre, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	fadds	%f0, %f2, %f0
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! --------in writeBinaryExpr-------

! b + c

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get b's value and c's value

! -------in getValue: b: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! -------in getValue: c: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writeBinaryExpr, do computation=========
	fadds	%f0, %f1, %f0

! =======in writeBinaryExpr, do store result=========
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ----------in writeAssignExpr: a  =  result

! =======in writeAssignExpr, varType is float=======

! =======in writeAssignExpr, convert right side int to float=======

! -------in getValue: result: null
	set	-28, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------writeUnaryExpr: a ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------in writePre: a

! =======in writePre, step 1: load value to local1

! -------in getValue: a: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writePre, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	fadds	%f1, %f2, %f1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f1, [%l0]

! -------writeUnaryExpr: c ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------in writePre: c

! =======in writePre, step 1: load value to local1

! -------in getValue: c: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! =======in writePre, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	fadds	%f0, %f2, %f0
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! --------in writeBinaryExpr-------

! a + c

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get a's value and c's value

! -------in getValue: a: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! -------in getValue: c: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writeBinaryExpr, do computation=========
	fadds	%f0, %f1, %f0

! =======in writeBinaryExpr, do store result=========
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ----------in writeAssignExpr: b  =  result

! =======in writeAssignExpr, varType is float=======

! =======in writeAssignExpr, convert right side int to float=======

! -------in getValue: result: null
	set	-32, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------writeUnaryExpr: a ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------in writePre: a

! =======in writePre, step 1: load value to local1

! -------in getValue: a: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writePre, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	fadds	%f1, %f2, %f1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f1, [%l0]

! -------writeUnaryExpr: b --

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is --, do nothing=========

! ----------in writePre: b

! =======in writePre, step 1: load value to local1

! -------in getValue: b: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! =======in writePre, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	fsubs	%f0, %f2, %f0
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! --------in writeBinaryExpr-------

! a - b

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get a's value and b's value

! -------in getValue: a: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! -------in getValue: b: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writeBinaryExpr, do computation=========
	fsubs	%f0, %f1, %f0

! =======in writeBinaryExpr, do store result=========
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ----------in writeAssignExpr: c  =  result

! =======in writeAssignExpr, varType is float=======

! =======in writeAssignExpr, convert right side int to float=======

! -------in getValue: result: null
	set	-36, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: a: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	temp2, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: c: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 36) & -8

