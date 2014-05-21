! 
! Generated Tue May 20 22:41:25 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
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


! ---------in writeLocalVariableWOInit:c

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: c  =  5

! =======in writeAssignExpr, varType is float=======

! =======in writeAssignExpr, convert right side int to float=======

! ---------intToFloat: 5 5

! =======in intToFloat: getAddress of 5
	set	-8, %l0
	add	%fp, %l0, %l0

! =======in intToFloat: load value of 5
	ld	[%l0], %f0

! =======in intToFloat: call itos 5
	fitos	%f0, %f0

! -------in getValue: 5: 5.0
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------------in writePrint---------------

! -------in getValue: c: null
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 8) & -8

