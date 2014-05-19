! 
! Generated Mon May 19 01:22:46 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "Please enter an integer value: "
	.align 4
temp1:	.asciz "The value you entered is "
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


! ---------in writeLocalVariableWOInit:i

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ----------in writeCin-------------
	call	inputInt
	nop

	set	-4, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: i: null
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeReturnStmt---------
	set	0, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.main = -(92 + 8) & -8

