! 
! Generated Sat May 24 02:26:01 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r3.5
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


! ------in writeConstantLiteral: 3.5
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 3.5: 3.5

! --------in getAddressHelper: 3.5
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! -------writeUnaryExpr: x ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: x

! =======in writePost, step 1: load value to local1

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	set	.float_one, %l0
	ld	[%l0], %f2

! =======in writePost, step 1.5: store original value 
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	fadds	%f0, %f2, %f3

! =======in writePost, step 3: store value 

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%f3, [%l0]

! -------writeUnaryExpr: x --

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is --, do nothing=========

! ----------writePost: x

! =======in writePost, step 1: load value to local1

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f1

! -------end of getValue------------
	set	.float_one, %l0
	ld	[%l0], %f2

! =======in writePost, step 1.5: store original value 
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%f1, [%l0]
	fsubs	%f1, %f2, %f3

! =======in writePost, step 3: store value 

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%f3, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
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

