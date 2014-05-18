! 
! Generated Sat May 17 19:37:30 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r1.2
temp1:	.single 0r0.5
temp2:	.asciz "d is: "
	.align 4
temp3:	.asciz "b is: "
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


! ------in writeConstValeu: 1.2
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in writeExpr: 1.2: 1.2
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! ---------in writeLocalVariableWInit:b
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------in writeConstValeu: .5
	set	temp1, %l0
	ld	[%l0], %f0
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in writeExpr: .5: 0.5
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! ---------in writeLocalVariableWInit:d
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! -------writeUnaryExpr: b ++

! ----------writePost: b

! -----------in writePost, step 1: load value to local1

! -------in writeExpr: b: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0

! -----------in writePost, step 1.5: store original value 
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -----------in writePost, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2

! -----------in writePost, step 1.5: store original value 
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%f2, [%l0]
	fadds	%f0, %f2, %f3
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f3, [%l0]

! ----------in writeAssignExpr: d  =  result

! -------in writeExpr: result: null
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	set	temp2, %o0
	call	printf
	nop


! -------in writeExpr: d: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop

	set	.endl, %o0
	call	printf
	nop

	set	temp3, %o0
	call	printf
	nop


! -------in writeExpr: b: null
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

	SAVE.main = -(92 + 20) & -8

