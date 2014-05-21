! 
! Generated Wed May 21 02:20:51 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "x: "
	.align 4
temp1:	.asciz "here"
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


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 1: 1.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writeWhileStart---------
whileStart1:

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! x < 5

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and 5's value

! -------in getValue: x: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: 5: 5.0
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bge	compOp0
	nop

	set	1, %l3
compOp0:
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ---------in writeWhile: result
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	whileEnd1
	nop


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: x: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! -------writeUnaryExpr: x ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: x

! =======in writePost, step 1: load value to local1

! -------in getValue: x: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! =======in writePost, step 1.5: store original value 
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	add	%l1, 1, %l3

! =======in writePost, step 3: store value 
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ---------in writeContinue----------
	ba	whileStart1
	nop


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! -------in writeWhileEnd------------
	ba	whileStart1
	nop


! ----------in writeCloseBlock-----------
whileEnd1:

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 20) & -8

