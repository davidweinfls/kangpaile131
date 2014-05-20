! 
! Generated Mon May 19 20:45:50 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "a: "
	.align 4
temp1:	.asciz "b: "
	.align 4
temp2:	.single 0r3.31
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------intToFloat: 1 1

! =======in intToFloat: getAddress of 1
	set	-4, %l0
	add	%fp, %l0, %l0

! =======in intToFloat: load value of 1
	ld	[%l0], %f0

! =======in intToFloat: call itos 1
	fitos	%f0, %f0

! -------in getValue: 1: 1.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:a
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ---------in writeLocalVariableWOInit:b

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
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------in writeConstantLiteral: 3.31
	set	temp2, %l0
	ld	[%l0], %f0
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! --------in writeBinaryExpr-------

! a + 3.31

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get a's value and 3.31's value

! -------in getValue: a: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! -------in getValue: 3.31: 3.31
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writeBinaryExpr, do computation=========
	fadds	%f0, %f1, %f0

! =======in writeBinaryExpr, do store result=========
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ----------in writeAssignExpr: b  =  result

! -------in getValue: result: null
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------------in writePrint---------------

! -------in getValue: b: null
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 20) & -8

