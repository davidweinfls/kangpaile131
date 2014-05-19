! 
! Generated Mon May 19 00:50:59 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r3.5
temp1:	.asciz "result: "
	.align 4
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


! ------in writeConstantLiteral: 4
	set	4, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 4: 4.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: 3.5
	set	temp0, %l0
	ld	[%l0], %f0
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in getValue: 3.5: 3.5
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! ---------in writeLocalVariableWInit:y
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------in writeConstantLiteral: true
	set	1, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: true: 1.0
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:z
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! --------in writeBinaryExpr-------

! x > y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! ---------intToFloat: x null

! =======in intToFloat: getAddress of x
	set	-8, %l0
	add	%fp, %l0, %l0

! =======in intToFloat: load value of x
	ld	[%l0], %f0

! =======in intToFloat: call itos x
	fitos	%f0, %f0

! -------in getValue: y: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	fcmps	%f0, %f1
	fble	compOp0
	nop

	set	1, %l3
compOp0:
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null
	set	-28, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.boolF, %o0
	cmp	%l1, %g0
	be	.printBool0
	nop

	set	.boolT, %o0

.printBool0:
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 28) & -8

