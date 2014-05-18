! 
! Generated Sun May 18 14:05:56 PDT 2014
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

	.section ".bss"
	.align 4

	.global x
	
x:	.skip 4

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 7
	set	7, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! x+7

! ----------in writeAssignExpr: x  =  result

! -------in writeExpr: result: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	x, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! -------in writeExpr: x: null
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 8) & -8

