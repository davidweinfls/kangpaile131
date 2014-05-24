! 
! Generated Sat May 24 03:21:36 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "y's value: "
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


! ------in writeConstantLiteral: false
	set	0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: false: 0.0

! --------in getAddressHelper: false
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:y
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: true
	set	1, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! =======in writeOr, ||, check first operand=========
	set	1, %l3
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop


! ------in writeConstantLiteral: true
	set	1, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: y  =  true

! -------in getValue: true: 1.0

! --------in getAddressHelper: true
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: y
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! --------in writeBinaryExpr-------

! true || y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, ||, check second operand=========
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop

	set	0, %l3
endOR0:

! =======in writeBinaryExpr, do store result=========
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else0
	nop


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
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


! ---------in writeElse---------
	ba	.endIf0
	nop

else0:

! ----------in writeCloseBlock-----------
.endIf0:

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 20) & -8

