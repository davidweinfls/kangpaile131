! 
! Generated Sat May 17 15:09:36 PDT 2014
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
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConst: 1
	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:a
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------writeUnaryExpr: a ++

! ----------writePre: a
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	add	%l1, 1, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	-12, %l0
	add	%fp, %l0, %l0
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

	SAVE.main = -(92 + 12) & -8

