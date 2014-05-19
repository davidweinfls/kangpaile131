! 
! Generated Mon May 19 00:15:54 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "result: "
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


! ------in writeConstantLiteral: true
	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: true: 1.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: false
	set	0, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: false: 0.0
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:y
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


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
	set	temp0, %o0
	call	printf
	nop


! --------in writeBinaryExpr-------

! x || y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, ||, check first operand=========
	set	1, %l3
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop


! =======in writeBinaryExpr, ||, check second operand=========
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop

	set	0, %l3
endOR0:

! =======in writeBinaryExpr, do store result=========
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! --------in writeBinaryExpr-------

! result && z

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, &&, check first operand=========
	set	0, %l3
	set	-28, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND0
	nop


! =======in writeBinaryExpr, &&, check second operand=========
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND0
	nop

	set	1, %l3
endAND0:

! =======in writeBinaryExpr, do store result=========
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null
	set	-32, %l0
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

	SAVE.main = -(92 + 32) & -8

