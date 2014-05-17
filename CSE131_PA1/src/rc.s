! 
! Generated Sat May 17 16:08:25 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r1.5
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


! ------in writeConst: 1.5
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! ---------in writeLocalVariableWInit:a
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! -------writeUnaryExpr: a ++

! ----------writePost: a

! -----------in writePost, step 1: load value to local1
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! -----------in writePost, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	fadds	%f0, %f2, %f3
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f3, [%l0]

! -------writeUnaryExpr: a ++

! ----------writePost: a

! -----------in writePost, step 1: load value to local1
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! -----------in writePost, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	fadds	%f0, %f2, %f3
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f3, [%l0]
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop

	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 8) & -8

