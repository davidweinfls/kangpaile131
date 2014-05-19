! 
! Generated Sun May 18 17:36:32 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r0.5
temp1:	.asciz "y: "
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


! ------in writeConstantLiteral: 10
	set	10, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 10: 10.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: .5
	set	temp0, %l0
	ld	[%l0], %f0
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in getValue: .5: 0.5
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! ---------in writeLocalVariableWInit:y
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! --------in writeBinaryExpr-------

! x * y

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

! =======in writeBinaryExpr, do computation=========
	fmuls	%f0, %f1, %f0

! =======in writeBinaryExpr, do store result=========
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! --------in writeBinaryExpr-------

! x + result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and result's value

! ---------intToFloat: x null

! =======in intToFloat: getAddress of x
	set	-8, %l0
	add	%fp, %l0, %l0

! =======in intToFloat: load value of x
	ld	[%l0], %f0

! =======in intToFloat: call itos x
	fitos	%f0, %f0

! -------in getValue: result: null
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f1

! =======in writeBinaryExpr, do computation=========
	fadds	%f0, %f1, %f0

! =======in writeBinaryExpr, do store result=========
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null
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

	SAVE.main = -(92 + 24) & -8

